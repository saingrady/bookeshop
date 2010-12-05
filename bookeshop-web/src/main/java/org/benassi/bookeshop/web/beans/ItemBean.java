/*
 * This file is part of the Book-eShop project.
 *
 *  Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 * Author :
 * 	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package org.benassi.bookeshop.web.beans;

import fr.mbh.bookeshop.dao.domain.Book;

import java.text.DecimalFormat;

/**
 * Bean encapsulating a book with its stock status and offer
 */
public class ItemBean {

    private Book book;

    private String stockStatus;

    private int offer;

    private double discountPrice;

    public ItemBean() {
    }

    public ItemBean(Book book, String stockStatus, int offer) {
        this.book = book;
        this.stockStatus = stockStatus;
        this.offer = offer;
    }

    public Book getBook() {
        return book;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public int getOffer() {
        return offer;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public double getDiscountPrice(){
        DecimalFormat df = new DecimalFormat("###.##");
        return Double.valueOf(df.format(book.getPrice() - book.getPrice() * offer/100));
    }
    
}
