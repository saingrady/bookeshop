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
import org.benassi.bookeshop.data.model.Customer;
import  org.benassi.bookeshop.web.cart.ShoppingCart;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.benassi.bookeshop.web.cart.ShoppingCartImpl;
import org.benassi.bookeshop.web.util.BookeshopConstants;

import java.util.Map;

/**
 * Action class to manage customer account
 */
public class AccountAction extends ActionSupport implements SessionAware {

    private Map<String,Object> session;

    private CustomerManager customerManager;

    private Customer loggedCustomer;

    private String error;

    public String create() {

        if (!customerManager.isRegistered(email)){

            Customer customer = new Customer(firstName,lastName,address,email,password);
            customer = customerManager.createCustomer(customer);
            session.put(BookeshopConstants.SESSION_USER,customer);
            session.put(BookeshopConstants.SESSION_CART,new ShoppingCartImpl());
            return ActionSupport.SUCCESS;

        }  else {
            error = "We have already an account for email ' " + email + " '!";
            return ActionSupport.ERROR;
        }
    }

    public String update() {

        if (loggedCustomer.getEmail() != email) { //user modified his email
            if (customerManager.isRegistered(email)) { //check if new email is already registered
                error = "We have already an account for email ' " + email + " '!";
                return ActionSupport.ERROR;
            } else {
                loggedCustomer.setEmail(email);
            }
        }

        loggedCustomer.setFirstName(firstName);
        loggedCustomer.setLastName(lastName);
        loggedCustomer.setAddress(address);
        loggedCustomer.setPassword(password);
        customerManager.updateCustomer(loggedCustomer);
        session.put(BookeshopConstants.SESSION_USER, loggedCustomer);
        return ActionSupport.SUCCESS;
    }

    @SkipValidation
    public String remove() {
        customerManager.removeCustomer(loggedCustomer);
        session.clear();
        return ActionSupport.SUCCESS;
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

    public void setSession(Map<String, Object> session) {
        this.session = session;
        loggedCustomer = (Customer)session.get(BookeshopConstants.SESSION_USER);
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