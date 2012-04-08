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
