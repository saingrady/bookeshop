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
