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
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.web.cart.ShoppingCart;
import org.benassi.bookeshop.web.util.AjaxContentProvider;
import org.benassi.bookeshop.web.util.BookeshopConstants;
import org.springframework.context.MessageSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Action class to update cart content
 */
public class UpdateCartAction implements SessionAware {

    private Map<String, Object> session;

    private BookManager bookManager;

    private ShoppingCart theCart;

     private Customer loggedCustomer;

    private String bookId;

    private String error;

    private InputStream inputStream;

    private AjaxContentProvider ajaxContentProvider;

    private MessageSource messageProvider;

    public String addItem(){

        Book book = bookManager.getBookByIsbn(bookId);

        if(loggedCustomer.getId()!= 0 && theCart != null){
            if(book != null){
                theCart.addItem(book.getIsbn());
            }
            return ActionSupport.SUCCESS;
        }
        else {
            error = messageProvider.getMessage("web.error.purchase.login",null,null,null);
            return ActionSupport.ERROR;
        }
    }

    public String addItemWithAjax(){

        Book book = bookManager.getBookByIsbn(bookId);

        if(loggedCustomer != null && theCart != null){
            if(book != null){
                theCart.addItem(book.getIsbn());
                String message = ajaxContentProvider.getCartUpdateAsJson("ok",book.getTitle(),theCart.getCount(),null);
                inputStream = new ByteArrayInputStream(message.getBytes());
                return ActionSupport.SUCCESS;
            }else{
                error = ajaxContentProvider.getCartUpdateAsJson("ko",null,null,messageProvider.getMessage("web.error.book.unknown",new Object [] {bookId},null,null));
                inputStream = new ByteArrayInputStream(error.getBytes());
                return ActionSupport.ERROR;
            }
        }
        else {
            error = ajaxContentProvider.getCartUpdateAsJson("ko",null,null,messageProvider.getMessage("web.error.purchase.login",null,null,null));
            inputStream = new ByteArrayInputStream(error.getBytes());
            return ActionSupport.ERROR;
        }
    }

    public String removeItem(){

        Book book = bookManager.getBookByIsbn(bookId);

        if(loggedCustomer != null && theCart != null){
            if(book != null){
                theCart.removeItem(book.getIsbn());
            }
            return ActionSupport.SUCCESS;
        }
        else {
            error = messageProvider.getMessage("web.error.purchase.login",null,null,null);
            return ActionSupport.ERROR;
        }
    }

    public String clearCart(){

        if (loggedCustomer != null && theCart != null){
            theCart.clearCart();
            return ActionSupport.SUCCESS;
        }else{
            error = messageProvider.getMessage("web.error.purchase.login",null,null,null);
            return ActionSupport.ERROR;
        }
    }

    /*
    * Setters for DI
    */
    public void setSession(Map<String, Object> session) {
        this.session = session;
        this.loggedCustomer = (Customer)session.get(BookeshopConstants.SESSION_USER);
        this.theCart = (ShoppingCart)session.get(BookeshopConstants.SESSION_CART);
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public void setAjaxContentProvider(AjaxContentProvider ajaxContentProvider) {
        this.ajaxContentProvider = ajaxContentProvider;
    }

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
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
    public InputStream getInputStream() {
        return inputStream;
    }

    public String getError() {
        return error;
    }

}