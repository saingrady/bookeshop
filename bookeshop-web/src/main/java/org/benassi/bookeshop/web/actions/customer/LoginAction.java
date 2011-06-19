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

import org.benassi.bookeshop.business.api.CustomerManager;
import org.benassi.bookeshop.business.exception.LoginException;
import org.benassi.bookeshop.data.model.Customer;
import  org.benassi.bookeshop.web.cart.ShoppingCart;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Action class for customer login
 */
public class LoginAction implements SessionAware{

    final Logger logger = LoggerFactory.getLogger(LogoutAction.class);

    private CustomerManager customerManager;

    private ShoppingCart theCart;

    private String loginEmail;

    private String loginPassword;

    private Map<String, Object> session;

    private Customer loggedCustomer;

    private String error;

    public String execute(){
        Customer c = customerManager.login(loginEmail, loginPassword);
        if (c != null){
            loggedCustomer = c;
            session.put("loggedCustomer",loggedCustomer);
            session.put("theCart",theCart);
            return "success";
        }   else {
            error = "Invalid credentials";
            logger.error(error);
            return "error";
        }
    }

    /*
     * Setters for DI
     */

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setTheCart(ShoppingCart theCart) {
        this.theCart = theCart;
    }

    /*
     * Getters for model
     */

    public String getError() {
        return error;
    }

    /*
     * Form fields
     */

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

}
