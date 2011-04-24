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

package org.benassi.bookeshop.business.impl;


import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.data.access.api.OrderDAO;
import org.benassi.bookeshop.data.access.api.OrderItemDAO;
import org.benassi.bookeshop.data.access.api.OrderStatusDAO;
import org.benassi.bookeshop.data.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderManagerImpl implements OrderManager {

    private OrderDAO orderDAO;

    private OrderItemDAO orderItemDAO;

    private OrderStatusDAO orderStatusDAO;

    private BookManager bookManager;

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

    @Override
    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    @Override
    public Set<Order> getOrdersByCustomers(final int customerId) {
        return orderDAO.getOrdersByCustomer(customerId);
    }

    @Override
    public Order createOrder(final Customer customer,final Map<String,Integer> items) {

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(new Date());
        OrderStatus orderStatus = orderStatusDAO.getStatusById(1);
        order.setStatus(orderStatus);
        order.setItems(new HashSet<OrderItem>());

        orderDAO.createOrder(order);

        for (String bookId : items.keySet()) {
            Book book = bookManager.getBookByIsbn(bookId);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setBookId(book.getIsbn());
            orderItem.setQuantity(items.get(bookId));
            orderItem.setPurchasePrice(book.getPrice());
            order.getItems().add(orderItem);
            orderItemDAO.createOrderItem(orderItem);
        }

        return order;
    }
}
