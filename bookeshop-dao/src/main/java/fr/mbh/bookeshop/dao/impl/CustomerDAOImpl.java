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

package fr.mbh.bookeshop.dao.impl;

import fr.mbh.bookeshop.dao.api.CustomerDAO;
import fr.mbh.bookeshop.dao.domain.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Hibernate implementation of customer DAO interface
 */
public class CustomerDAOImpl extends HibernateDaoSupport implements CustomerDAO{

    public Customer getCustomerByEmail(String email) {
      return getHibernateTemplate().get(Customer.class,email);
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
