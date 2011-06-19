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

package org.benassi.bookeshop.data.access.test;

import org.benassi.bookeshop.data.access.api.*;
import org.benassi.bookeshop.data.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class OrderDAOImplTest {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private OrderStatusDAO orderStatusDAO;

    @Autowired
    private BookDAO bookManager;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Test
    public void testGetOrderById() {
        Order order = orderDAO.getOrderById(22135);
        assertNotNull(order);
        assertEquals(1, order.getItems().size());
        assertEquals(56325,order.getCustomerId());
    }

    @Test
    public void testGetOrdersByCustomer() {
        Set<Order> orders = orderDAO.getOrdersByCustomer(56325);
        assertNotNull(orders);
        assertEquals(2, orders.size());
    }

    @Test
    public void testCreateOrder(){

        Order order = new Order();
        order.setCustomerId(56325);
        order.setDate(new Date());
        OrderStatus orderStatus = orderStatusDAO.getStatusById(1);
        order.setStatus(orderStatus);
        //persist order
        orderDAO.createOrder(order);

        //persist order item
        String bookId = "9781430218418";
        Book book = bookManager.getBookByIsbn(bookId);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setBookId(book.getIsbn());
        orderItem.setQuantity(2);
        orderItem.setPurchasePrice(book.getPrice());
        orderItemDAO.createOrderItem(orderItem);

        Order createdOrder = orderDAO.getOrderById(order.getOrderId());
        assertNotNull(createdOrder);
        assertEquals(1,createdOrder.getItems().size());

    }

}
