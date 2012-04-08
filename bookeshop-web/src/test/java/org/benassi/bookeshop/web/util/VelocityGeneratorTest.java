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

package org.benassi.bookeshop.web.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.benassi.bookeshop.data.access.api.CustomerDAO;
import org.benassi.bookeshop.data.access.api.OrderDAO;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;

/**
 * Confirmation mail generation from velocity template test
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-test.xml"})
public class VelocityGeneratorTest {

    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private OrderUtil orderUtil;

    @Test
    public void testGetPdfCatalogueStream() {

        Customer customer = customerDao.getCustomerByEmail("guest@server.com");
        Order order = orderDAO.getOrderById(22136);
        order.setFormattedDate(orderUtil.formatDate(order.getDate()));
        order.setFormattedTotal(orderUtil.formatTotal(order.getTotal()));
        assertNotNull(customer);
        assertNotNull(order);

        Map model = new HashMap();
        model.put("customer", customer);
        model.put("order", order);

        String result = null;
        try {
            result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/orderConfirmation.vm", model);
        } catch (VelocityException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        System.out.println("result = " + result);

    }
}
