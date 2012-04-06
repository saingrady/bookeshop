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
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.SessionAware;
import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.web.util.BookeshopConstants;
import org.benassi.bookeshop.web.util.OrderUtil;

import java.util.Map;
import java.util.Set;

/**
 * Action class to get customer orders
 */
public class OrderAction extends ActionSupport implements SessionAware, Preparable{

    private Map<String,Object> session;

    private OrderManager orderManager;

    private OrderUtil orderUtil;

    private Customer loggedCustomer;

    private Set<Order> orders;

    @Override
    public void prepare() throws Exception {
        orders = orderManager.getOrdersByCustomers(loggedCustomer.getId());
        orderUtil.prepareOrdersForView(orders);
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

    public void setSession(Map<String, Object> session) {
        this.session = session;
        loggedCustomer = (Customer)session.get(BookeshopConstants.SESSION_USER);
    }

    /*
    * Getters for model
    */

    public Set<Order> getOrders() {
        return orders;
    }


}