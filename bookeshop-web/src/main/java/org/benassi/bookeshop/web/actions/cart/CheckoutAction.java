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
import org.benassi.bookeshop.business.api.OrderManager;
import org.benassi.bookeshop.business.exception.OutOfStockException;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.data.model.Customer;
import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.web.cart.ShoppingCart;
import org.benassi.bookeshop.web.util.BookUtil;
import org.benassi.bookeshop.web.util.BookeshopConstants;
import org.benassi.bookeshop.web.util.OrderUtil;
import org.springframework.context.MessageSource;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class to checkout orders
 * @author Mahmoud Ben Hassine
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

    private MessageSource messageProvider;

    private OrderUtil orderUtil;

    public String execute() {

        items = new HashMap<Book,Integer>();
        total = 0;
        try {

            //create order (transactional)
            order = orderManager.createOrder(loggedCustomer,theCart.getItems());
            order.setFormattedDate(orderUtil.formatDate(order.getDate()));
            order.setFormattedTotal(orderUtil.formatTotal(order.getTotal()));

            //prepare books for confirmation page
            for (String bookId : theCart.getItems().keySet()) {
                Book book = bookManager.getBookByIsbn(bookId);
                book.setStockStatus(bookUtil.getStockStatus(book.getStock()));
                book.setFormattedPublishDate(bookUtil.formatPublishDate(book.getPublishDate()));
                Integer quantity = theCart.getItems().get(bookId);
                total += book.getDiscountPrice() * quantity;
                items.put(book, quantity);
            }

            //clear the cart
            theCart.clearCart();

            return ActionSupport.SUCCESS;

        } catch (OutOfStockException oose) {
            int requestedQuantity = oose.getRequestedQuantity();
            int currentStock = oose.getCurrentStock();
            String title = bookManager.getBookByIsbn(oose.getBookISBN()).getTitle();
            if(currentStock > 0)
                error = messageProvider.getMessage("web.error.stock.insufficient", new Object[]{requestedQuantity,title, currentStock}, null, null);
            else
                error = messageProvider.getMessage("web.error.stock.outOfStockItem", new Object[]{title}, null, null);
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

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void setOrderUtil(OrderUtil orderUtil) {
        this.orderUtil = orderUtil;
    }

    /*
     * Getters for model
     */
    public String getProcessingMessage() {
        return messageProvider.getMessage("web.checkout.wait",null,null,null);
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

}