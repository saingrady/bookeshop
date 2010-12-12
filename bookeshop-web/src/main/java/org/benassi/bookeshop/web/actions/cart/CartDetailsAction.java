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

import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.dao.domain.Book;
import fr.mbh.bookeshop.util.cart.ShoppingCart;
import org.apache.struts2.interceptor.SessionAware;
import org.benassi.bookeshop.web.beans.ItemBean;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class to show shopping cart details
 */
public class CartDetailsAction implements SessionAware {

    private Map<String, Object> session;

    private BookManager bookManager;

    private ShoppingCart theCart;

    private Map<ItemBean,Integer> items;

    private Double total;

    private String error;

    public Map<ItemBean, Integer> getItems() {
        return items;
    }

    public Double getTotal() {
        DecimalFormat df = new DecimalFormat("###.##");
        return Double.valueOf(df.format(total));
    }

    public String getError() {
        return error;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
        theCart = (ShoppingCart) session.get("theCart");
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public String execute() {

       if( theCart != null){
           total = 0.0;
           items = new HashMap<ItemBean,Integer>();
           for (String bookId : theCart.getItems().keySet()) {
               Book book = bookManager.getBookByIsbn(bookId);
               ItemBean itemBean = new ItemBean(
                       book,
                       bookManager.getBookStockStatus(book.getIsbn()),
                       bookManager.getBookOffer(book.getIsbn())
               );
               Integer quantity = theCart.getItems().get(bookId);
               items.put(itemBean, quantity);
               total += itemBean.getDiscountPrice() * quantity;
           }
           return "success";
       }else
           error = "Please sign up or log in to buy from Book e-Shop";
           return "error";
    }

}
