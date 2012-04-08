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

package org.benassi.bookeshop.business.api;

import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;

import java.util.Map;
import java.util.Set;

/**
 * Order manager business interface
 * @author Mahmoud Ben Hassine
 */
public interface OrderManager {

    /**
     * get order by id
     * @param orderId the order id
     * @return the order
     */
    public Order getOrderById(final int orderId);

    /**
     * get orders by customers
     * @param customerId customer Id
     * @return the customer orders
     */
    public Set<Order> getOrdersByCustomers(final int customerId);

    /**
     * create order from a validated shopping cart
     * @param customer the customer for which the order will be created
     * @param items the shopping cart items
     * @return the created order
     */
    public Order createOrder(final Customer customer, final Map<String, Integer> items);

}
