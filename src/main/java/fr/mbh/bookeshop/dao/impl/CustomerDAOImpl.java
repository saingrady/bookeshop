/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.dao.impl;

import fr.mbh.bookeshop.dao.api.CustomerDAO;
import fr.mbh.bookeshop.domain.Customer;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Mahmoud
 */

public class CustomerDAOImpl extends HibernateDaoSupport implements CustomerDAO{


    public Customer findByEmail(String email) {
      return getHibernateTemplate().get(Customer.class,email);
    }


    public List<Customer> findByLastName(String lastName) {
        return getHibernateTemplate().find("from Customer c where c.lastName = ? ",lastName);
    }

    public void update(Customer customer){
        getHibernateTemplate().update(customer);
    }

    public void delete(Customer customer){
        getHibernateTemplate().delete(customer);
    }

    public void save(Customer customer){
        getHibernateTemplate().save(customer);
    }

    public boolean checkLogin(String email, String password) {
        final String[] params = new String[]{"email", "password"};
        final Object[] values = new Object[]{email, password};
        List<Customer> customers=
                getHibernateTemplate().findByNamedParam("from Customer c where c.email=:email and c.password=:password", params, values);
        return (customers != null && customers.size() > 0) ? true : false ;
    }

}
