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
import org.apache.struts2.interceptor.SessionAware;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.web.util.BookeshopConstants;

import java.util.Map;

/**
 * Action class to redirect update account requests to update form page
 * @author Mahmoud Ben Hassine
 */
public class UpdateAction extends ActionSupport implements SessionAware {

    private Customer loggedCustomer;

    private Map<String, Object> session;

    /*
     * Setters for DI
     */

    public void setSession(Map<String, Object> session) {
        this.session = session;
        this.loggedCustomer = (Customer)session.get(BookeshopConstants.SESSION_USER);
    }

    /*
     * Getters to populate the form with logged customer data
     */
    public int getId() {
        return loggedCustomer.getId();
    }

    public String getFirstName() {
        return loggedCustomer.getFirstName();
    }


    public String getLastName() {
        return loggedCustomer.getLastName();
    }

    public String getEmail() {
        return loggedCustomer.getEmail();
    }


    public String getAddress() {
        return loggedCustomer.getAddress();
    }

    public String getPassword() {
        return loggedCustomer.getPassword();
    }

    public String getPasswordConfirm() {
        return loggedCustomer.getPassword();
    }

}
