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

package org.benassi.bookeshop.web.actions.cart;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.data.model.Book;
import  org.benassi.bookeshop.web.cart.ShoppingCart;
import org.benassi.bookeshop.web.util.BookComparator;
import org.benassi.bookeshop.web.util.BookUtil;
import org.benassi.bookeshop.web.util.BookeshopConstants;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Action class to show shopping cart details
 */
public class CartDetailsAction implements SessionAware {

    private Map<String, Object> session;

    private BookManager bookManager;

    private BookUtil bookUtil;

    private ShoppingCart theCart;

    private Map<Book,Integer> items;

    private float total;

    private String error;

    public String execute() {

       if( theCart != null){
           total = 0;
           items = new TreeMap<Book,Integer>(new BookComparator());
           for (String bookId : theCart.getItems().keySet()) {
               Book book = bookManager.getBookByIsbn(bookId);
               book.setStockStatus(bookUtil.getStockStatus(book.getStock()));
               book.setFormattedPublishDate(bookUtil.formatPublishDate(book.getPublishDate()));
               Integer quantity = theCart.getItems().get(bookId);
               items.put(book, quantity);
               total += book.getDiscountPrice() * quantity;
           }
           return ActionSupport.SUCCESS;
       }else{
           error = "Please sign up or log in to buy from Book e-Shop";
           return ActionSupport.ERROR;
       }
    }
    /*
     * Setters for DI
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
        theCart = (ShoppingCart) session.get(BookeshopConstants.SESSION_CART);
    }

    public void setBookUtil(BookUtil bookUtil) {
        this.bookUtil = bookUtil;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    /*
     * Getters for model
     */
    public Map<Book, Integer> getItems() {
        return items;
    }

    public String getError() {
        return error;
    }

    public String getFormattedTotal() {
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(total);
    }

}
