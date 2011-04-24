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

import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.data.model.OrderItem;

import java.util.Set;

/**
 * BookDetailsAction : action class to get book details
 */
public class OrderDetailsAction {

    private OrderManager orderManager;

    private int orderId;

    private Order order;

    private Set<OrderItem> items;

    private String error;

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Order getOrder() {
        return order;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getError() {
        return error;
    }

    public String execute(){
        order = orderManager.getOrderById(orderId);
        items = order.getItems();
        if (order != null)
            return "success";
        else {
            error = "No such order with ID = " + orderId;
            return "error";
        }

    }
}
