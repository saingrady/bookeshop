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

package org.benassi.bookeshop.business.impl;


import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.business.exception.OutOfStockException;
import org.benassi.bookeshop.data.access.api.OrderDAO;
import org.benassi.bookeshop.data.access.api.OrderItemDAO;
import org.benassi.bookeshop.data.access.api.OrderStatusDAO;
import org.benassi.bookeshop.data.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Order manager implementation
 * @author Mahmoud Ben Hassine
 */
public class OrderManagerImpl implements OrderManager {

    private OrderDAO orderDAO;

    private OrderItemDAO orderItemDAO;

    private OrderStatusDAO orderStatusDAO;

    private BookManager bookManager;

    /** {@inheritDoc} */
    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    /** {@inheritDoc} */
    public Set<Order> getOrdersByCustomers(final int customerId) {
        return orderDAO.getOrdersByCustomer(customerId);
    }

    /** {@inheritDoc} */
    public Order createOrder(final Customer customer,final Map<String,Integer> items) throws OutOfStockException {

        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setDate(new Date());
        OrderStatus orderStatus = orderStatusDAO.getStatusById(1); //status confirmed
        order.setStatus(orderStatus);
        order.setItems(new HashSet<OrderItem>());

        orderDAO.createOrder(order);

        for (String bookId : items.keySet()) {
            Book book = bookManager.getBookByIsbn(bookId);
            bookManager.checkoutBook(bookId,items.get(bookId));
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setBookId(bookId);
            orderItem.setQuantity(items.get(bookId));
            orderItem.setPurchasePrice(book.getDiscountPrice());
            order.getItems().add(orderItem);
            orderItemDAO.createOrderItem(orderItem);
        }

        return order;
    }

    /*
     * Setters for DI
     */
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void setOrderItemDAO(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public void setOrderStatusDAO(OrderStatusDAO orderStatusDAO) {
        this.orderStatusDAO = orderStatusDAO;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }
}
