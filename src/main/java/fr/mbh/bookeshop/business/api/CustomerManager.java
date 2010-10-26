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

package fr.mbh.bookeshop.business.api;

import fr.mbh.bookeshop.business.exception.CustomerExistantException;
import fr.mbh.bookeshop.business.exception.LoginException;
import fr.mbh.bookeshop.domain.Customer;

/**
 *
 * @author Mahmoud
 */
public interface CustomerManager {

    /**
     * login a customer
     * @param email the customer email
     * @param password the customer password
     * @return the logged customer or null if login error
     * @throws LoginException if the login fails
     */
    public Customer Login(String email,String password) throws LoginException;

    /**
     * update an existant customer
     * @param customer the customer to update
     * @return the updated customer
     * @throws CustomerExistantException if the customer updates his identifier (email) with a value of an already existant customer
     */
    public Customer updateCustomer(Customer customer) throws CustomerExistantException;

    /**
     * update an existant customer
     * @param customer the customer to remove
     */
    public void removeCustomer(Customer customer);

    /**
     * update an existant customer
     * @param customer the customer to update
     * @return the updated customer
     * @throws CustomerExistantException if a customer with the given email already exists
     */
    public Customer createCustomer(Customer customer) throws CustomerExistantException;

}
