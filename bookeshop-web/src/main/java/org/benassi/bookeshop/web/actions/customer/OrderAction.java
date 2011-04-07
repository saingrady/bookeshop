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
import fr.mbh.bookeshop.business.api.CustomerManager;
import fr.mbh.bookeshop.business.api.OrderManager;
import org.apache.struts2.interceptor.SessionAware;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;

import java.util.Map;
import java.util.Set;

/**
 * Action class to manage customer account
 */
public class OrderAction extends ActionSupport implements SessionAware {

    private Map<String,Object> session;

    private CustomerManager customerManager;

    private OrderManager orderManager;

    private Customer loggedCustomer;

    private Set<Order> orders;

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
        loggedCustomer = (Customer)session.get("loggedCustomer");
    }

    public String execute() {
        orders = orderManager.getOrdersByCustomers(loggedCustomer.getId());
        return "success";
    }

}