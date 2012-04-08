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

package org.benassi.bookeshop.business.impl;


import org.benassi.bookeshop.business.api.CustomerManager;
import org.benassi.bookeshop.data.access.api.CustomerDAO;
import org.benassi.bookeshop.data.access.api.OrderDAO;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;

import java.util.Set;

/**
 * Customer Manager implementation
 * @author Mahmoud Ben Hassine
 */
public class CustomerManagerImpl implements CustomerManager {

    private CustomerDAO customerDAO;

    private OrderDAO orderDAO;

    /** {@inheritDoc} */
    public Customer login(String email, String password) {
        if(customerDAO.checkLoginCredentials(email, password))
            return customerDAO.getCustomerByEmail(email);
        else{
            return null;
        }
    }

    /** {@inheritDoc} */
    public Customer updateCustomer(Customer customer) {
        customerDAO.update(customer);
        return customerDAO.getCustomerById(customer.getId());
    }

    /** {@inheritDoc} */
    public void removeCustomer(Customer customer) {
        Set<Order> orders = orderDAO.getOrdersByCustomer(customer.getId());
        for(Order order : orders){
            orderDAO.removeOrder(order);
        }
        customerDAO.delete(customer);
    }

    /** {@inheritDoc} */
    public Customer createCustomer(Customer customer) {
        customerDAO.save(customer);
        return customerDAO.getCustomerById(customer.getId());
    }

    /** {@inheritDoc} */
    public boolean isRegistered(String email){
        Customer c = customerDAO.getCustomerByEmail(email);
        return (c != null);
    }

    /*
     * Setters for DI
     */
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

}
