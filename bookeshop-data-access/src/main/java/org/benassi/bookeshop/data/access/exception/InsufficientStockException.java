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

package org.benassi.bookeshop.data.access.exception;

public class InsufficientStockException extends Exception {

    private int requestedQuantity;
    
    private int currentStock;
    
    private String bookISBN;

    public InsufficientStockException(int requestedQuantity, int currentStock, String bookISBN) {
        this.requestedQuantity = requestedQuantity;
        this.currentStock = currentStock;
        this.bookISBN = bookISBN;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public String getBookISBN() {
        return bookISBN;
    }

}
