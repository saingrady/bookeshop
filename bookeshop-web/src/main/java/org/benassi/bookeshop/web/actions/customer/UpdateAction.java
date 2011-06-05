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

package org.benassi.bookeshop.web.actions.customer;

import com.opensymphony.xwork2.ActionSupport;
import org.benassi.bookeshop.data.model.Customer;

/**
 * Action class to redirect update account requests to update form page
 */
public class UpdateAction extends ActionSupport {

    private Customer loggedCustomer;

    /*
     * Setters for DI
     */

    public void setLoggedCustomer(Customer loggedCustomer) {
        this.loggedCustomer = loggedCustomer;
    }

    /*
     * Getters to populate the form with logged customer data
     */
    public int getId() {
        return loggedCustomer.getId();
    }

    public String getFirstName() {
        return loggedCustomer.getFirstName();
    }


    public String getLastName() {
        return loggedCustomer.getLastName();
    }

    public String getEmail() {
        return loggedCustomer.getEmail();
    }


    public String getAddress() {
        return loggedCustomer.getAddress();
    }

    /*
     * Warning : even with password getters, password fields are not populated in the form when using <s:password> tag 
     * (but they are populated when using <s:textfield>)
     */
    public String getPassword() {
        return loggedCustomer.getPassword();
    }

    public String getPasswordConfirm() {
        return loggedCustomer.getPassword();
    }

}
