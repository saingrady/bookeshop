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

import org.benassi.bookeshop.data.access.api.OrderStatusDAO;
import org.benassi.bookeshop.data.model.OrderStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class OrderStatusDAOImplTest {

    @Autowired
    private OrderStatusDAO orderStatusDAO;

    @Test
    public void testGetStatusList() {
        List<OrderStatus> statusList = orderStatusDAO.getStatusList();
        assertEquals(4, statusList.size());
    }

    @Test
    public void testGetStatusByIdOK() {
        OrderStatus orderStatus = orderStatusDAO.getStatusById(1);
        assertNotNull(orderStatus);
        assertEquals("confirmed",orderStatus.getLabel());
    }

    @Test
    public void testGetStatusByIdKO() {
        OrderStatus orderStatus = orderStatusDAO.getStatusById(5);
        assertNull(orderStatus);
    }

}
