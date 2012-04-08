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

package org.benassi.bookeshop.web.actions.customer;

import com.opensymphony.xwork2.ActionSupport;
import org.benassi.bookeshop.business.api.CustomerManager;
import org.benassi.bookeshop.data.model.Customer;
import org.apache.struts2.interceptor.SessionAware;
import org.benassi.bookeshop.web.cart.ShoppingCart;
import org.benassi.bookeshop.web.util.BookeshopConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.util.Map;

/**
 * Action class for customer login
 * @author Mahmoud Ben Hassine
 */
public class LoginAction implements SessionAware{

    final Logger logger = LoggerFactory.getLogger(LogoutAction.class);

    private CustomerManager customerManager;

    private String loginEmail;

    private String loginPassword;

    private Map<String, Object> session;

    private String error;

    private MessageSource messageProvider;

    public String execute(){
        Customer customer = customerManager.login(loginEmail, loginPassword);
        if (customer != null){
            session.put(BookeshopConstants.SESSION_USER,customer);
            session.put(BookeshopConstants.SESSION_CART,new ShoppingCart());
            return ActionSupport.SUCCESS;
        }   else {
            error = messageProvider.getMessage("web.error.account.login.fail",null,null,null);
            logger.error(error + "[" + loginEmail + "," + loginPassword + "]");
            return ActionSupport.ERROR;
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

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
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
