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

package org.benassi.bookeshop.business.impl;


import org.benassi.bookeshop.business.api.CustomerManager;
import org.benassi.bookeshop.data.access.api.CustomerDAO;
import org.benassi.bookeshop.data.access.api.OrderDAO;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Customer Manager implementation
 */
public class CustomerManagerImpl implements CustomerManager {

    final Logger logger = LoggerFactory.getLogger(CustomerManagerImpl.class);

    private CustomerDAO customerDAO;

    private OrderDAO orderDAO;

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public Customer login(String email, String password) {
        if(customerDAO.checkLoginCredentials(email, password))
            return customerDAO.getCustomerByEmail(email);
        else{
            return null;
        }
    }

    public Customer updateCustomer(Customer customer) {
        customerDAO.update(customer);
        return customerDAO.getCustomerById(customer.getId());
    }

    public void removeCustomer(Customer customer) {
        Set<Order> orders = orderDAO.getOrdersByCustomer(customer.getId());
        for(Order order : orders){
            orderDAO.removeOrder(order);
        }
        customerDAO.delete(customer);
    }

    public Customer createCustomer(Customer customer) {
        customerDAO.save(customer);
        return customerDAO.getCustomerById(customer.getId());
    }

    public boolean isRegistered(String email){
        Customer c = customerDAO.getCustomerByEmail(email);
        return (c != null);
    }

}
