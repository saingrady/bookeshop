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

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import org.benassi.bookeshop.business.api.CustomerManager;
import org.benassi.bookeshop.business.exception.CustomerExistentException;
import org.benassi.bookeshop.data.model.Customer;
import  org.benassi.bookeshop.web.cart.ShoppingCart;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.Map;

/**
 * Action class to manage customer account
 */
public class AccountAction extends ActionSupport implements SessionAware {

    private Map<String,Object> session;

    private CustomerManager customerManager;

    private Customer loggedCustomer;

    private ShoppingCart theCart;

    private String error;

    public String create() {

        Customer customer = new Customer(firstName,lastName,address,email,password);

        try {
            customerManager.createCustomer(customer);
            session.put("loggedCustomer",customer);
            session.put("theCart",theCart);
            return "success";
        } catch (CustomerExistentException e) {
            error = "We have already an account for email '" + email + "'!\n" +
                    "If you have already signed up with this email and forgot your password,\n" +
                    "you can request to reset your password on the right-side bar";
            return "error";
        }
    }

    public String update() {

        loggedCustomer.setFirstName(firstName);
        loggedCustomer.setLastName(lastName);
        loggedCustomer.setEmail(email);
        loggedCustomer.setAddress(address);
        loggedCustomer.setPassword(password);
        try {
            customerManager.updateCustomer(loggedCustomer);
            session.put("loggedCustomer",loggedCustomer);
            return "success";
        } catch (CustomerExistentException e) {
            error = "We have already an account for email '" + email + "'!\n" +
                    "If you ave already signed up with this email and forgot your password,\n" +
                    "you can request to reset your password on the right-side bar";
            return "error";
        }
    }

    @SkipValidation
    public String remove() {
        customerManager.removeCustomer(loggedCustomer);
        session.clear();
        return "success";
    }

    @Override
    public void validate() {

        if ( email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
             password.isEmpty() || address.isEmpty() || passwordConfirm.isEmpty())
            addActionError("All fields are required"); //TODO i18n

        if (!password.equals(passwordConfirm))
            addFieldError("passwordConfirm", "Password confirmation does not match password");//TODO i18n
    }

    /*
     * Setters for DI
     */
    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setTheCart(ShoppingCart theCart) {
        this.theCart = theCart;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setLoggedCustomer(Customer loggedCustomer) {
        this.loggedCustomer = loggedCustomer;
    }

    /*
     * Form fields
     */
    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String password;

    private String passwordConfirm;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @EmailValidator(fieldName = "email", message = "invalid email format") //TODO use key attribute for i18n
    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /*
     * getters to populate creation/update forms if there are errors 
     */
    public int getId() {
        return loggedCustomer.getId();
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getError() {
        return error;
    }
}