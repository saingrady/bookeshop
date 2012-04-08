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
import  org.benassi.bookeshop.web.cart.ShoppingCart;
import org.benassi.bookeshop.web.util.BookComparator;
import org.benassi.bookeshop.web.util.BookUtil;
import org.benassi.bookeshop.web.util.BookeshopConstants;
import org.benassi.bookeshop.web.util.OrderUtil;
import org.springframework.context.MessageSource;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Action class to show shopping cart details
 * @author Mahmoud Ben Hassine
 */
public class CartDetailsAction implements SessionAware {

    private Map<String, Object> session;

    private BookManager bookManager;

    private BookUtil bookUtil;

    private OrderUtil orderUtil;

    private ShoppingCart theCart;

    private Map<Book,Integer> items;

    private float total;

    private String error;

    private MessageSource messageProvider;

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
           error = messageProvider.getMessage("web.error.purchase.login",null,null,null);
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

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void setOrderUtil(OrderUtil orderUtil) {
        this.orderUtil = orderUtil;
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
        return orderUtil.formatTotal(total);
    }

}
