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

package fr.mbh.bookeshop.controller;

import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.business.exception.StockUnavailableException;
import fr.mbh.bookeshop.dao.domain.Book;
//import fr.mbh.bookeshop.domain.Customer;
import fr.mbh.bookeshop.util.cart.ShoppingCart;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CheckoutServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext((getServletContext()));

        /* Mail sending bean to send confirmation email to customer */
//        MailSender mailSender = (MailSender)context.getBean("mailSender");
//        Customer loggedCustomer = (Customer) request.getSession().getAttribute("loggedCustomer");

        BookManager bookManager = (BookManager) context.getBean("bookManager");
        Map<Book, Integer> items = new HashMap<Book,Integer>();
        ShoppingCart theCart =  (ShoppingCart)request.getSession().getAttribute("theCart");
        String gift = request.getParameter("gift");
        double total = 0.0;
        String next = "/WEB-INF/jsp/confirm.jsp";
        try {
            for (String bookId : theCart.getItems().keySet()) {
                Book book = bookManager.getBookByIsbn(bookId);
                Integer quantity = theCart.getItems().get(bookId);
                total += book.getPrice() * quantity;
                bookManager.checkoutBook(book.getIsbn(),quantity);
                items.put(book, quantity);
            }
            request.setAttribute("items", items);
            request.setAttribute("total",total);
            theCart.clearCart();

            if (null != gift)
                request.setAttribute("gift","yes");

            /* Populate the order confirmation mail from velocity template and send it to the customer
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("customer@bookeshop.com");
            message.setTo(loggedCustomer.getEmail());
            message.setSubject("Book eShopping center : Order N° ");
            message.setText("content to populate from a velocity template");
            mailSender.send(message);*/

        } catch (StockUnavailableException e) {
            request.setAttribute("error",e.getMessage());
            next = "/WEB-INF/jsp/error.jsp";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(next);
        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Checkout Order Servlet";
    }
}
