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
