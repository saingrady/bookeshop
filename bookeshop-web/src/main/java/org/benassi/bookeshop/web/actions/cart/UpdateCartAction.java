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

import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.web.cart.ShoppingCart;

/**
 * Action class to update cart content
 */
public class UpdateCartAction {

    private BookManager bookManager;

    private ShoppingCart theCart;

     private Customer loggedCustomer;

    private String bookId;

    private String error;

    public String addItem(){

        Book book = bookManager.getBookByIsbn(bookId);

        if(loggedCustomer.getId()!= 0 && theCart != null){
            if(book != null){
                theCart.addItem(book.getIsbn());
            }
            return "success";
        }
        else {
            error = "Please sign up or log in to buy from Book e-Shop";
            return "error";
        }
    }

    public String removeItem(){

        Book book = bookManager.getBookByIsbn(bookId);

        if(theCart != null){
            if(book != null){
                theCart.removeItem(book.getIsbn());
            }
            return "success";
        }
        else {
            error = "Please sign up or log in to buy from Book e-Shop";
            return "error";
        }
    }

    public String clearCart(){

        if (theCart != null){
            theCart.clearCart();
            return "success";
        }else{
            error = "Please sign up or log in to buy from Book e-Shop";
            return "error";
        }
    }

    /*
    * Setters for DI
    */
    public void setTheCart(ShoppingCart theCart) {
        this.theCart = theCart;
    }

    public void setLoggedCustomer(Customer loggedCustomer) {
        this.loggedCustomer = loggedCustomer;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

     /*
     * Setters for request parameters
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /*
     * Getters for model
     */
    public String getError() {
        return error;
    }

}