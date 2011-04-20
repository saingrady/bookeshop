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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility methods for org.benassi.bookeshop.data.model.Book
 */
public class BookUtil {

    public static float getDiscountPrice(float price, int offer){
        return price - price * offer / 100;
    }

    public static String getStockStatus(int stock) {
        if (stock <= 0)
            return "Out of stock"; //TODO externalize + I18N
        else if (stock <= 10)
            return "Last items";
        else return "In stock";
    }

    public static String getFormattedPublishDate(Date publishDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(publishDate);
    }
}