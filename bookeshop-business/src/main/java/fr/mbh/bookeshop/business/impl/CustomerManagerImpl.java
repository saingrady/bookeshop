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

package fr.mbh.bookeshop.business.impl;

import fr.mbh.bookeshop.business.api.CustomerManager;
import fr.mbh.bookeshop.business.exception.CustomerExistentException;
import fr.mbh.bookeshop.business.exception.LoginException;
import fr.mbh.bookeshop.dao.api.CustomerDAO;
import org.benassi.bookeshop.data.model.Customer;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Customer Manager implementation
 */
public class CustomerManagerImpl implements CustomerManager {

    final Logger logger = LoggerFactory.getLogger(CustomerManagerImpl.class);

    private CustomerDAO customerDAO;

    private MessageSource messages;

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }

    public Customer login(String email, String password) throws LoginException {
        if(customerDAO.checkLoginCredentials(email, password))
            return customerDAO.getCustomerByEmail(email);
        else{
            String error = messages.getMessage("login.invalid", null, "Invalid login credentials!", null);
            logger.error(error);
            throw new LoginException(error);
        }
    }

    public Customer updateCustomer(Customer customer) throws CustomerExistentException {
        try {
            customerDAO.update(customer);
            return customerDAO.getCustomerById(customer.getId());
        } catch (DataAccessException e) {
            logger.error("Error updating customer [" + customer + "]",e);
            throw new CustomerExistentException();
        }
    }

    public void removeCustomer(Customer customer) {
            customerDAO.delete(customer);
    }

    public Customer createCustomer(Customer customer) throws CustomerExistentException {
        try {
            customerDAO.save(customer);
            return customerDAO.getCustomerById(customer.getId());
        } catch (DataAccessException e) {
            logger.error("Error updating customer [" + customer + "]",e);
            throw new CustomerExistentException();
        }
    }

}
