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

package org.benassi.bookeshop.web.actions;

import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.dao.domain.Book;
import fr.mbh.bookeshop.util.cart.ShoppingCart;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Action class to update cart content
 */
public class UpdateCartAction implements SessionAware {

    private String error;

    private Map<String, Object> session;

    private BookManager bookManager;

    private ShoppingCart theCart;

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String id;
    private int categoryId;
    private String action;
    private String from;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String execute() {

        //TODO : create private method and split ugly test

        Book book = bookManager.getBookByIsbn(id);

        theCart = (ShoppingCart)session.get("theCart");

        if(theCart != null){
            if(book != null)
                if ("add".equals(action))
                    theCart.addItem(book.getIsbn());
                else theCart.removeItem(book.getIsbn());
        }else{
            error = "Please sign up or log in to buy from Book e-Shop";
            return "error";
        }

        // TODO : fix redirect to category : categoryId is not forwarded in request
        //request.setAttribute("categoryId", categoryId);
        return from;
    }
}