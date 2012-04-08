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

package org.benassi.bookeshop.data.access.impl;

import org.benassi.bookeshop.data.access.api.CustomerDAO;
import org.benassi.bookeshop.data.model.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Hibernate implementation of customer DAO interface
 */
public class CustomerDAOImpl extends HibernateDaoSupport implements CustomerDAO{

    public Customer getCustomerById(int id) {
        return getHibernateTemplate().get(Customer.class,id);
    }

    public Customer getCustomerByEmail(String email) {
        List<Customer> customers= getHibernateTemplate().find("from Customer where email = ? ", email);
        return (customers != null && customers.size() > 0) ? customers.get(0) : null ;
    }

    public void update(Customer customer) throws DataAccessException {
        getHibernateTemplate().update(customer);
    }

    public void delete(Customer customer) throws DataAccessException{
        getHibernateTemplate().delete(customer);
    }

    public void save(Customer customer) throws DataAccessException{
        getHibernateTemplate().save(customer);
    }

    public boolean checkLoginCredentials(String email, String password) {
        List<Customer> customers= getHibernateTemplate().find("from Customer where email = ? and password = ?", email,password);
        return (customers != null && customers.size() > 0) ? true : false ;
    }

}
