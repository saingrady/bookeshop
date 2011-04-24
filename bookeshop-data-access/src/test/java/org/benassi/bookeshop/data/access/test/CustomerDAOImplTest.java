/*
 * This file is part of the Book-eShop project.
 *
 *    Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 *    Author :
 *   	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package org.benassi.bookeshop.data.access.test;

import org.benassi.bookeshop.data.access.api.CustomerDAO;
import org.benassi.bookeshop.data.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.*;

/**
 * Unit tests for customer DAO implementation
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class CustomerDAOImplTest {

    @Autowired
    private CustomerDAO customerDAO;

    private Customer toto;

    private Customer titi;

    @Before
    public void setup() {
        toto = new Customer( "toto", "ben toto", "totoadr","toto@gmail.com","totopwd");
        titi = new Customer( "titi", "ben titi", "titiadr","titi@gmail.com","titipwd");
    }

    @Test
    public void testCheckLoginCredentialsOK(){
        String email = "md.benhassine@gmail.com";
        String password = "mahmoud";
        assertTrue(customerDAO.checkLoginCredentials(email,password));
    }

    @Test
    public void testCheckLoginCredentialsKO(){
        String email = "md.benhassine@gmail.com";
        String password = "blabla";
        assertFalse(customerDAO.checkLoginCredentials(email,password));
    }

    @Test
    public void testGetCustomerByEmailOK(){
        String email = "md.benhassine@gmail.com";
        Customer customer = customerDAO.getCustomerByEmail(email);
        assertNotNull(customer);
        assertEquals(56325,customer.getId());
    }

    @Test
    public void testGetCustomerByEmailKO(){
        String email = "foo@bar.org";
        Customer customer = customerDAO.getCustomerByEmail(email);
        assertNull(customer);
    }

    @Test
    public void testSaveOK(){

        customerDAO.save(toto);

        Customer customer = customerDAO.getCustomerById(toto.getId());
        assertNotNull(customer);
    }

    /**
     * try to save a customer with an existent email
     */
    @Test
    @ExpectedException(DataAccessException.class)
    public void testSaveExistentCustomer(){
        customerDAO.save(toto);
    }

    /**
     * test to update a customer
     */
    @Test
    public void testUpdateOK() {

        int id = 56326;

        Customer customer = customerDAO.getCustomerById(id);
        customer.setAddress("new adr");
        customer.setEmail("mahmoud@yahoo.com");
        customer.setPassword("new pwd");

        customerDAO.update(customer);

        Customer c = customerDAO.getCustomerById(id);

        assertEquals("new adr", c.getAddress());
        assertEquals("mahmoud@yahoo.com", c.getEmail());
        assertEquals("new pwd", c.getPassword());

    }

    /**
     * try to update email with an existent email of another customer
     */
    @Test
    @ExpectedException(DataAccessException.class) // constraint unique_email on table customer violated
    public void testUpdateCustomerEmailKO() {

        int id = 56326;

        String newEmail = "md.benhassine@gmail.com";

        Customer customer = customerDAO.getCustomerById(id);
        
        customer.setEmail(newEmail);

        customerDAO.update(customer);

    }

    /**
     * try to update a non existent customer
     */
    @Test
    @ExpectedException(DataAccessException.class)
    public void testUpdateNonExistentCustomer() {
        customerDAO.update(titi);
    }

    /**
     * test to delete a loaded customer

    //@Ignore
    //TODO hsqldb : java.sql.BatchUpdateException: failed batch
    // pass with mysql, bug hsqldb
    @Test
    public void testDeleteLoadedCustomer() {

        int id = 56325;

        Customer customer = customerDAO.getCustomerById(id);

        customerDAO.delete(customer);

        assertNull( customerDAO.getCustomerById(id) );
    }*/

    @After
    public void teardown(){
        toto =null;
        titi = null;
        customerDAO = null;
        System.gc();
    }

}
