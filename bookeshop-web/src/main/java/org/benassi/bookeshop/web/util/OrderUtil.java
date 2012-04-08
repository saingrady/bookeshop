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

import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.data.model.OrderItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Utility class for {@link org.benassi.bookeshop.data.model.Order}
 */
public class OrderUtil {

    private String dateFormat;

    private String totalFormat;

    /*
     * public utility methods
     */

    public void prepareOrdersForView(Set<Order> orders) {
        for(Order order : orders){
            order.setFormattedDate(formatDate(order.getDate()));
            order.setFormattedTotal(formatTotal(order.getTotal()));
        }
    }

    public String formatDate(final Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public String formatTotal(float total) {
        DecimalFormat df = new DecimalFormat(totalFormat);
        return df.format(total);
    }

     /*
     * Setters for DI
     */

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setTotalFormat(String totalFormat) {
        this.totalFormat = totalFormat;
    }


}
