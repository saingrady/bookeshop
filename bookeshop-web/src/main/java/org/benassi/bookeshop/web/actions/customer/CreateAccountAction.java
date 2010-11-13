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
import fr.mbh.bookeshop.util.cart.ShoppingCartImpl;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Action class to create a new customer account
 */
public class CreateAccountAction implements SessionAware {

    private Map<String,Object> session;

    private ShoppingCart theCart;

    private CustomerManager customerManager;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String password;

    private Customer loggedCustomer;

    private String error;

    public void setTheCart(ShoppingCart theCart) {
        this.theCart = theCart;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Customer getLoggedCustomer() {
        return loggedCustomer;
    }

    public void setLoggedCustomer(Customer loggedCustomer) {
        this.loggedCustomer = loggedCustomer;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String execute(){
        
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPassword(password);
        try {
            customer = customerManager.createCustomer(customer);
            session.put("loggedCustomer", customer);
            session.put("theCart", theCart  );
            return "success";
        } catch (CustomerExistentException ex) {
            error = ex.getMessage();
            return "error";
        }
    }

}
