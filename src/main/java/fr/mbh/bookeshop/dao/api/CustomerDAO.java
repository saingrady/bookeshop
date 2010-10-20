/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.dao.api;

import fr.mbh.bookeshop.domain.Customer;
import java.util.List;

/**
 *
 * @author Mahmoud
 */
public interface CustomerDAO {

    public Customer findByEmail(String email);

    public List<Customer> findByLastName(String lastName);

    public boolean checkLogin(String email,String password);

    public void update(Customer customer);

    public void delete(Customer customer);

    public void save(Customer customer);

}
