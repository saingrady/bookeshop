/*
 * This file is part of the Book-eShop project.
 *
 *    Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 *    Author :
 *   	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package org.benassi.bookeshop.web.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.web.actions.cart.CheckoutAction;
import org.benassi.bookeshop.web.util.BookeshopConstants;
import org.benassi.bookeshop.web.util.OrderUtil;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Confirmation email interceptor
 */
public class ConfirmationEmailInterceptor extends AbstractInterceptor {

    final Logger logger = LoggerFactory.getLogger(ConfirmationEmailInterceptor.class);

    private Customer loggedCustomer;

    private VelocityEngine velocityEngine;

    private MailSender mailSender;

    private MessageSource messageProvider;

    private OrderUtil orderUtil;

    public void setOrderUtil(OrderUtil orderUtil) {
        this.orderUtil = orderUtil;
    }

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        invocation.invoke();

        Map<String, Object> session = ActionContext.getContext().getSession();
        loggedCustomer = (Customer)session.get(BookeshopConstants.SESSION_USER);

        Order order = ((CheckoutAction)invocation.getAction()).getOrder();
        order.setFormattedDate(orderUtil.formatDate(order.getDate()));
        order.setFormattedTotal(orderUtil.formatTotal(order.getTotal()));
        Map model = new HashMap();
        model.put("customer", loggedCustomer);
        model.put("order", order);

        String result = null;
        try {
            result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"velocity/orderConfirmation.vm", model);
        } catch (VelocityException e) {
            logger.error("Error in generating confirmation email from velocity template.",e);
            return Action.ERROR;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(messageProvider.getMessage("web.checkout.mail.from",null,null,null));
        message.setSentDate(new Date());
        message.setTo(loggedCustomer.getEmail());
        message.setSubject(messageProvider.getMessage("web.checkout.mail.object", new Object[]{order.getOrderId()}, null, null));
        message.setText(result);
        mailSender.send(message);
        return Action.SUCCESS;
    }

}
