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

package org.benassi.bookeshop.data.access.api;

import org.benassi.bookeshop.data.model.Order;

import java.util.Set;

/**
 * Interface for order data access object
 */
public interface OrderDAO {

    /**
     * get an order by its id
     * @param orderId the order's id
     * @return the order or null if no order with the given id
     */
    public Order getOrderById(final int orderId);

    /**
     * get orders for a customer
     * @param customerId the customer's id
     * @return the set of orders for the given customer
     */
    public Set<Order> getOrdersByCustomer(final int customerId);

    /**
     * create an order
     * @param order the order to persist
     */
    public void createOrder(final Order order);

    /**
     * remove an order
     * @param order the order to remove
     */
    public void removeOrder(final Order order);

}
