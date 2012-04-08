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

package org.benassi.bookeshop.data.access.api;


import org.benassi.bookeshop.data.model.Customer;
import org.springframework.dao.DataAccessException;

/**
 * Interface for customer data access object
 */
public interface CustomerDAO {

    /**
     * get a customer by id
     * @param id the customer id
     * @return the found customer or null if no customer found for the given id
     */
    public Customer getCustomerById(int id);

    /**
     * get a customer by email
     * @param email the customer email
     * @return the found customer or null if no customer found for the given email
     */
    public Customer getCustomerByEmail(String email);

    /**
     * check login credentials
     * @param email the customer email
     * @param password the customer password
     * @return true if login/password matches a valid entry in the DB
     */
    public boolean checkLoginCredentials(String email, String password);

    /**
     * update a customer
     * @param customer the customer to update
     * @throws org.springframework.dao.DataAccessException thrown if the customer cannot be updated
     */
    public void update(Customer customer) throws DataAccessException;

    /**
     * delete a customer
     * @param customer the customer to delete
     * @throws org.springframework.dao.DataAccessException thrown if the customer cannot be deleted
     */
    public void delete(Customer customer) throws DataAccessException;

    /**
     * save a new customer
     * @param customer the customer to save
     * @throws org.springframework.dao.DataAccessException thrown if the customer cannot be saved
     */
    public void save(Customer customer) throws DataAccessException;

}
