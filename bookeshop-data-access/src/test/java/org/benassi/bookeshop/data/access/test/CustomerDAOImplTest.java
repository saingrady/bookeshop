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

package org.benassi.bookeshop.data.access.test;

import org.benassi.bookeshop.data.access.api.CustomerDAO;
import org.benassi.bookeshop.data.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

    @Before
    public void setup() {
        toto = new Customer( "toto", "ben toto", "totoadr","toto@gmail.com","totopwd");
    }

    @Test
    public void testCheckLoginCredentialsOK(){
        String email = "guest@server.com";
        String password = "guest";
        assertTrue(customerDAO.checkLoginCredentials(email,password));
    }

    @Test
    public void testCheckLoginCredentialsKO(){
        String email = "guest@server.com";
        String password = "blah";
        assertFalse(customerDAO.checkLoginCredentials(email,password));
    }

    @Test
    public void testGetCustomerByEmailOK(){
        String email = "guest@server.com";
        Customer customer = customerDAO.getCustomerByEmail(email);
        assertNotNull(customer);
        assertEquals(56325,customer.getId());
    }

    @Test
    public void testGetCustomerByEmailKO(){
        String email = "blah@server.org";
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
        customer.setEmail("guest@server.org");
        customer.setPassword("new pwd");

        customerDAO.update(customer);

        Customer c = customerDAO.getCustomerById(id);

        assertEquals("new adr", c.getAddress());
        assertEquals("guest@server.org", c.getEmail());
        assertEquals("new pwd", c.getPassword());

    }

    /**
     * try to update email with an existent email of another customer
     */
    @Test
    @ExpectedException(DataAccessException.class) // constraint unique_email on table customer violated
    public void testUpdateCustomerEmailKO() {

        int id = 56326;

        String newEmail = "guest@server.com";

        Customer customer = customerDAO.getCustomerById(id);
        
        customer.setEmail(newEmail);

        customerDAO.update(customer);

    }

    /**
     * test to delete a loaded customer
     */
    @Test
    public void testDeleteLoadedCustomer() {

        int id = 56325;

        Customer customer = customerDAO.getCustomerById(id);

        customerDAO.delete(customer);

        assertNull( customerDAO.getCustomerById(id) );
    }

    @After
    public void teardown(){
        toto =null;
        customerDAO = null;
        System.gc();
    }

}
