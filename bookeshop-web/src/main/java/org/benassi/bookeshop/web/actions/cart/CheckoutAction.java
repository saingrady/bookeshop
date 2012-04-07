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
import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.business.exception.OutOfStockException;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.web.cart.ShoppingCart;
import org.benassi.bookeshop.web.util.BookUtil;
import org.benassi.bookeshop.web.util.BookeshopConstants;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class to checkout an order
 */
public class CheckoutAction implements SessionAware{

    private Map<String, Object> session;

    private BookManager bookManager;

    private BookUtil bookUtil;

    private OrderManager orderManager;

    private Customer loggedCustomer;

    private ShoppingCart theCart;

    private Map<Book, Integer> items;

    private float total;

    private String error;

    private Order order;

    public String execute() {

        items = new HashMap<Book,Integer>();
        total = 0;
        try {
            for (String bookId : theCart.getItems().keySet()) {
                Book book = bookManager.getBookByIsbn(bookId);
                book.setStockStatus(bookUtil.getStockStatus(book.getStock()));
                book.setFormattedPublishDate(bookUtil.formatPublishDate(book.getPublishDate()));
                Integer quantity = theCart.getItems().get(bookId);
                bookManager.checkoutBook(book.getIsbn(),quantity);
                total += book.getDiscountPrice() * quantity;
                items.put(book, quantity);
            }
            order = orderManager.createOrder(loggedCustomer,theCart.getItems());
            theCart.clearCart();
            return ActionSupport.SUCCESS;

        } catch (OutOfStockException e) {
            error = e.getMessage();
            return ActionSupport.ERROR;
        }
    }
    /*
     * Setters for DI
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
        loggedCustomer = (Customer)session.get(BookeshopConstants.SESSION_USER);
        theCart = (ShoppingCart) session.get(BookeshopConstants.SESSION_CART);
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public void setBookUtil(BookUtil bookUtil) {
        this.bookUtil = bookUtil;
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /*
     * Getters for model
     */
    public String getProcessingMessage() {
        return "Processing your order.. Please wait.";//TODO i18n
    }

    public Order getOrder() {
        return order;
    }

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