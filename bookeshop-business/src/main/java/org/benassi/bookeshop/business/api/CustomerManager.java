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

package org.benassi.bookeshop.business.api;

import org.benassi.bookeshop.data.model.Customer;

/**
 * Customer Manager business interface
 * @author Mahmoud Ben Hassine
 */
public interface CustomerManager {

    /**
     * check if there is a registered customer with the given email
     * @param email the customer email to check
     * @return true if a customer is registered with the given email
     */
    public boolean isRegistered(String email);

    /**
     * login a customer
     * @param email the customer email
     * @param password the customer password
     * @return the logged customer
     */
    public Customer login(String email, String password);

    /**
     * update an existent customer
     * @param customer the customer to update
     * @return the updated customer
     */
    public Customer updateCustomer(Customer customer);

    /**
     * remove a customer
     * @param customer the customer to remove
     */
    public void removeCustomer(Customer customer);

    /**
     * Create a customer
     * @param customer the customer to create
     * @return the created customer
     */
    public Customer createCustomer(Customer customer);

}
