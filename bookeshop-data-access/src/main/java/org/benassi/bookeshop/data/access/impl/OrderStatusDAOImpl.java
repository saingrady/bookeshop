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

package org.benassi.bookeshop.data.access.impl;

import org.benassi.bookeshop.data.access.api.OrderStatusDAO;
import org.benassi.bookeshop.data.model.OrderStatus;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Hibernate implementation of status DAO interface
 */
public class OrderStatusDAOImpl extends HibernateDaoSupport implements OrderStatusDAO {

    @Override
    public List<OrderStatus> getStatusList() {
       return  this.getHibernateTemplate().find("from OrderStatus");
    }

    @Override
    public OrderStatus getStatusById(int statusId) {
        return this.getHibernateTemplate().get(OrderStatus.class,statusId);
    }
}