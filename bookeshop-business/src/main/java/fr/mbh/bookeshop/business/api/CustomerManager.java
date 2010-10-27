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

package fr.mbh.bookeshop.business.api;

import fr.mbh.bookeshop.business.exception.CustomerExistentException;
import fr.mbh.bookeshop.business.exception.LoginException;
import fr.mbh.bookeshop.domain.Customer;

/**
 * Customer Manager interface
 */
public interface CustomerManager {

    /**
     * login a customer
     * @param email the customer email
     * @param password the customer password
     * @return the logged customer
     * @throws LoginException if the login fails
     */
    public Customer Login(String email,String password) throws LoginException;

    /**
     * update an existent customer
     * @param customer the customer to update
     * @return the updated customer
     * @throws CustomerExistentException if the customer updates his identifier (email) with a value of an already existent customer
     */
    public Customer updateCustomer(Customer customer) throws CustomerExistentException;

    /**
     * remove a customer
     * @param customer the customer to remove
     */
    public void removeCustomer(Customer customer);

    /**
     * Create a customer
     * @param customer the customer to create
     * @return the created customer
     * @throws CustomerExistentException if a customer with the given email already exists
     */
    public Customer createCustomer(Customer customer) throws CustomerExistentException;

}
