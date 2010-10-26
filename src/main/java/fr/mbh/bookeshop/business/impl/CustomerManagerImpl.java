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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.business.impl;

import fr.mbh.bookeshop.business.api.CustomerManager;
import fr.mbh.bookeshop.business.exception.CustomerExistantException;
import fr.mbh.bookeshop.business.exception.LoginException;
import fr.mbh.bookeshop.dao.api.CustomerDAO;
import fr.mbh.bookeshop.domain.Customer;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mahmoud
 */
public class CustomerManagerImpl implements CustomerManager {

    private CustomerDAO customerDAO;

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer Login(String email, String password) throws LoginException {
        if(customerDAO.checkLogin(email, password))
            return customerDAO.findByEmail(email);
        else throw new LoginException("Invalid login credentials : email='"+email+"'/password='"+password+"'");
    }

    public Customer updateCustomer(Customer customer) throws CustomerExistantException{
        try {
            customerDAO.update(customer);
            return customerDAO.findByEmail(customer.getEmail());
        } catch (Exception e) {
            throw new CustomerExistantException();
        }
    }

    public void removeCustomer(Customer customer) {
            customerDAO.delete(customer);
    }

    public Customer createCustomer(Customer customer) throws CustomerExistantException {
        try {
            customerDAO.save(customer);
            return customerDAO.findByEmail(customer.getEmail());
        } catch (Exception e) {
            throw new CustomerExistantException();
        }
    }

}
