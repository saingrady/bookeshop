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

package org.benassi.bookeshop.web.actions.customer;

import com.opensymphony.xwork2.ActionSupport;
import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.data.model.OrderItem;
import org.benassi.bookeshop.web.util.OrderUtil;

import java.util.Set;

/**
 * Action class to get order details
 */
public class OrderDetailsAction {

    private OrderManager orderManager;

    private OrderUtil orderUtil;

    private int orderId;

    private Order order;

    private Set<OrderItem> items;

    private String error;

    public String execute(){
        order = orderManager.getOrderById(orderId);
        if (order != null) {
            order.setFormattedDate(orderUtil.formatDate(order.getDate()));
            order.setFormattedTotal(orderUtil.formatTotal(order.getTotal()));
            items = order.getItems();
            return ActionSupport.SUCCESS;
        }
        else {
            error = "No such order with ID = " + orderId;
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
