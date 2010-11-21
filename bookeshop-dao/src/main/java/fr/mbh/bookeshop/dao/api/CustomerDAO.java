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

package fr.mbh.bookeshop.dao.api;

import fr.mbh.bookeshop.dao.domain.Customer;
import org.springframework.dao.DataAccessException;

/**
 * Interface for customer data access object
 */
public interface CustomerDAO {

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
    public boolean checkLoginCredentials(String email,String password);

    /**
     * update a customer
     * @param customer the customer to update
     * @throws DataAccessException thrown if the customer cannot be updated
     */
    public void update(Customer customer) throws DataAccessException;

    /**
     * delete a customer
     * @param customer the customer to delete
     * @throws DataAccessException thrown if the customer cannot be deleted
     */
    public void delete(Customer customer) throws DataAccessException;

    /**
     * save a new customer
     * @param customer the customer to save
     * @throws DataAccessException thrown if the customer cannot be saved
     */
    public void save(Customer customer) throws DataAccessException;

}
