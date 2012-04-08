/*
 * The MIT License
 *
 *   Copyright (c) 2012, Mahmoud Ben Hassine (md.benhassine@gmail.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
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
 * @author Mahmoud Ben Hassine
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
