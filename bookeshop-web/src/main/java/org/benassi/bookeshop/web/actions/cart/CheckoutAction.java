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
import fr.mbh.bookeshop.business.exception.StockUnavailableException;
import fr.mbh.bookeshop.dao.domain.Book;
import fr.mbh.bookeshop.dao.domain.Customer;
import fr.mbh.bookeshop.util.cart.ShoppingCart;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
//import org.springframework.mail.MailSender;

import java.util.HashMap;
import java.util.Map;

/**
 * Action class to checkout an order
 */
public class CheckoutAction implements SessionAware {

    private Map<String, Object> session;

    private BookManager bookManager;

    private ShoppingCart theCart;

    private Map<Book, Integer> items;

    private Double total;

    private String error;

    public Map<Book, Integer> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

    public String getError() {
        return error;
    }

    /*private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }*/

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public String execute() {
        // logged customer to get email
        //Customer loggedCustomer = (Customer) session.get("loggedCustomer");

        items = new HashMap<Book,Integer>();
        theCart = (ShoppingCart) session.get("theCart");
        total = 0.0;
        try {
            for (String bookId : theCart.getItems().keySet()) {
                Book book = bookManager.getBookByIsbn(bookId);
                Integer quantity = theCart.getItems().get(bookId);
                total += book.getPrice() * quantity;
                bookManager.checkoutBook(book.getIsbn(),quantity);
                items.put(book, quantity);
            }
            theCart.clearCart();

            /* Populate the order confirmation mail from velocity template and send it to the customer
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("customer@bookeshop.com");
            message.setTo(loggedCustomer.getEmail());
            message.setSubject("Book eShopping center : Order N° ");
            message.setText("content to populate from a velocity template");
            mailSender.send(message);*/

            return "success";

        } catch (StockUnavailableException e) {
            error = e.getMessage();
            return "error";
        }
    }
}