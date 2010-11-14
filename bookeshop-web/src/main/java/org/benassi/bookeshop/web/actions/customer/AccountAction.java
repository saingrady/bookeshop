/*
 * This file is part of the Book-eShop project.
 *
 *  Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 * Author :
 * 	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package org.benassi.bookeshop.web.actions.customer;

import fr.mbh.bookeshop.business.api.CustomerManager;
import fr.mbh.bookeshop.business.exception.CustomerExistentException;
import fr.mbh.bookeshop.dao.domain.Customer;
import fr.mbh.bookeshop.util.cart.ShoppingCart;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Action class to manage customer account
 */
public class AccountAction implements SessionAware {

    private Map<String,Object> session;

    private CustomerManager customerManager;

    private Customer loggedCustomer;

    private ShoppingCart theCart;

    private String error;

    public String getError() {
        return error;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setTheCart(ShoppingCart theCart) {
        this.theCart = theCart;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
        loggedCustomer = (Customer)session.get("loggedCustomer");
    }

    public String create() {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPassword(password);

        try {
            customerManager.createCustomer(customer);
            session.put("loggedCustomer",customer);
            session.put("theCart",theCart);
            return "success";
        } catch (CustomerExistentException e) {
            error = "We have already an account for email '" + email + "'!\n" +
                    "If you are have already signed up with this email and forgot your password,\n" +
                    "you can request to reset your password on the right-side bar";
            return "error";
        }
    }

    public String update() {

        loggedCustomer.setFirstName(firstName);
        loggedCustomer.setLastName(lastName);
        //loggedCustomer.setEmail(email); email disabled in jsp : very poorly designed as said : should use/generate customer ID and not email as ID
        loggedCustomer.setAddress(address);
        loggedCustomer.setPassword(password);
        customerManager.updateCustomer(loggedCustomer);
        session.put("loggedCustomer",loggedCustomer);
        return "success";

    }

    public String remove() {
        customerManager.removeCustomer(loggedCustomer);
        session.clear();
        return "success";
    }

    /*
     * Form fields
     */
    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String password;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}