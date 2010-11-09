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

package org.benassi.bookeshop.web.actions;

import fr.mbh.bookeshop.business.api.CustomerManager;
import fr.mbh.bookeshop.business.exception.LoginException;
import fr.mbh.bookeshop.dao.domain.Customer;
import fr.mbh.bookeshop.util.cart.ShoppingCart;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Action class for customer login
 */
public class LoginAction implements SessionAware{

    private CustomerManager customerManager;

    private ShoppingCart theCart;

    private String email;

    private String password;

    private Map<String, Object> session;

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

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setLoggedCustomer(Customer loggedCustomer) {
        this.loggedCustomer = loggedCustomer;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String execute(){
        try {
            loggedCustomer = customerManager.Login(email,password);
            session.put("loggedCustomer",loggedCustomer);
            session.put("theCart",theCart);
            return "success";
        } catch (LoginException e) {
            // TODO probable bug : if login fails, client details should not be shown in jsp right-sidebar.jsp
//            loggedCustomer = null;// may fix the bug
            error = e.getMessage();
            return "error";
        }
    }

}
