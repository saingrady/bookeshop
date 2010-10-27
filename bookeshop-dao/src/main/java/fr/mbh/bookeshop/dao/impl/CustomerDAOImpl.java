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
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CustomerDAOImpl extends HibernateDaoSupport implements CustomerDAO{


    public Customer findByEmail(String email) {
      return getHibernateTemplate().get(Customer.class,email);
    }


    public List<Customer> findByLastName(String lastName) {
        return getHibernateTemplate().find("from Customer c where c.lastName = ? ",lastName);
    }

    public void update(Customer customer){
        getHibernateTemplate().update(customer);
    }

    public void delete(Customer customer){
        getHibernateTemplate().delete(customer);
    }

    public void save(Customer customer){
        getHibernateTemplate().save(customer);
    }

    public boolean checkLogin(String email, String password) {
        final String[] params = new String[]{"email", "password"};
        final Object[] values = new Object[]{email, password};
        List<Customer> customers=
                getHibernateTemplate().findByNamedParam("from Customer c where c.email=:email and c.password=:password", params, values);
        return (customers != null && customers.size() > 0) ? true : false ;
    }

}
