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

package org.benassi.bookeshop.web.actions.customer;

import com.opensymphony.xwork2.ActionSupport;
import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.data.model.OrderItem;
import org.benassi.bookeshop.web.util.OrderUtil;
import org.springframework.context.MessageSource;

import java.util.Set;

/**
 * Action class to get order details
 * @author Mahmoud Ben Hassine
 */
public class OrderDetailsAction {

    private OrderManager orderManager;

    private OrderUtil orderUtil;

    private int orderId;

    private Order order;

    private Set<OrderItem> items;

    private String error;

    private MessageSource messageProvider;

    public String execute(){
        order = orderManager.getOrderById(orderId);
        if (order != null) {
            order.setFormattedDate(orderUtil.formatDate(order.getDate()));
            order.setFormattedTotal(orderUtil.formatTotal(order.getTotal()));
            items = order.getItems();
            return ActionSupport.SUCCESS;
        }
        else {
            error = messageProvider.getMessage("web.error.order.unknown",new Object [] {orderId},null,null);
            return ActionSupport.ERROR;
        }

    }

    /*
     * Setters for DI
     */

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public void setOrderUtil(OrderUtil orderUtil) {
        this.orderUtil = orderUtil;
    }

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
    }

    /*
    * Setters for request parameters
    */

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /*
     * Getters for model
     */

    public Order getOrder() {
        return order;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public String getError() {
        return error;
    }
}
