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

package fr.mbh.bookeshop.dao;

import fr.mbh.bookeshop.dao.api.CustomerDAO;
import fr.mbh.bookeshop.dao.domain.Customer;
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
    }

    @Test
    public void testGetCustomerByEmailKO(){
        String email = "foo@bar.org";
        Customer customer = customerDAO.getCustomerByEmail(email);
        assertNull(customer);
    }

    @Test
    public void testSaveOK(){

        Customer newCustomer = new Customer();
        newCustomer.setFirstName("toto");
        newCustomer.setLastName("ben toto");
        newCustomer.setAddress("adr");
        newCustomer.setEmail("toto@gmail.org");
        newCustomer.setPassword("totopwd");

        customerDAO.save(newCustomer);

        Customer customer = customerDAO.getCustomerByEmail(newCustomer.getEmail());
        assertNotNull(customer);
    }


    /**
     * try to save a customer without id (id = email)
     */
    @Test
    @ExpectedException(DataAccessException.class)
    public void testSaveKO(){

        Customer newCustomer = new Customer();
        newCustomer.setFirstName("toto");
        newCustomer.setLastName("ben toto");
        newCustomer.setAddress("adr");
        newCustomer.setPassword("totopwd");

        customerDAO.save(newCustomer);

    }

    /**
     * try to save a customer with an existent customer email
     */
    @Test
    @ExpectedException(DataAccessException.class)
    public void testSaveExistentCustomer(){

        Customer newCustomer = new Customer();
        newCustomer.setFirstName("toto");
        newCustomer.setLastName("ben toto");
        newCustomer.setAddress("adr");
        newCustomer.setEmail("md.benhassine@gmail.com");
        newCustomer.setPassword("totopwd");

        customerDAO.save(newCustomer);

    }

    /**
     * test to delete a loaded customer
     */
    @Test
    public void testDeleteLoadedCustomer() {

        String email = "md.benhassine@gmail.com";

        Customer customer = customerDAO.getCustomerByEmail(email);

        customerDAO.delete(customer);

        customer = customerDAO.getCustomerByEmail(email);

        assertNull(customerDAO.getCustomerByEmail(email));

    }

    /**
     * test to delete a manually created customer
     */
    @Test
    public void testDeleteManuallyCreatedCustomer() {

        String email = "md.benhassine@gmail.com";

        Customer customer = new Customer();

        customer.setEmail(email);

        customerDAO.delete(customer);

        customer = customerDAO.getCustomerByEmail(email);

        assertNull(customerDAO.getCustomerByEmail(email));

    }

    /**
     * test to update a customer
     */
    @Test
    public void testUpdateOK() {

        String email = "mahmoud@server.com";

        Customer customer = customerDAO.getCustomerByEmail(email);
        customer.setFirstName("toto");
        customer.setLastName("ben toto");
        customer.setAddress("adr");
        customer.setPassword("totopwd");

        customerDAO.update(customer);

        Customer c = customerDAO.getCustomerByEmail(email);

        assertEquals("toto", c.getFirstName());
        assertEquals("ben toto", c.getLastName());
        assertEquals("adr", c.getAddress());
        assertEquals("totopwd", c.getPassword());

    }

    /**
     * try to update a customer id (email) //should be replaced, ie TODOs
     */
    @Test
    @ExpectedException(DataAccessException.class)
    public void testUpdateCustomerId() {

        String email = "mahmoud@server.com";
        String newEmail = "toto@gmail.com";

        Customer customer = customerDAO.getCustomerByEmail(email);
        customer.setEmail(newEmail);

        customerDAO.update(customer);

    }

    /**
     * try to update a non existent customer
     */
    @Test
    @ExpectedException(DataAccessException.class)
    public void testUpdateNonExistentCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("titi");
        customer.setLastName("ben titi");
        customer.setAddress("titadr");
        customer.setEmail("titi@gmail.com");
        customer.setPassword("titipwd");

        customerDAO.update(customer);

    }


}
