/*
 * The MIT License
 *
 *   Copyright (c) 2012, Mahmoud Ben Hassine (md.benhassine@gmail.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
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
 * @author Mahmoud Ben Hassine
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