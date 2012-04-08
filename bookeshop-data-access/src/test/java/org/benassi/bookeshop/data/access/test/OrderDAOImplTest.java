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
