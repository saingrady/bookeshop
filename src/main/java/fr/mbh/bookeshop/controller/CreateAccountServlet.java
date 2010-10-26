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

import fr.mbh.bookeshop.business.api.CustomerManager;
import fr.mbh.bookeshop.domain.Customer;
import fr.mbh.bookeshop.business.exception.CustomerExistantException;
import fr.mbh.bookeshop.util.cart.ShoppingCartImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CreateAccountServlet extends HttpServlet {
   
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
        
        Customer customer = new Customer();
        CustomerManager customerManager = (CustomerManager) context.getBean("customerManager");
//        Customer loggedCustomer = (Customer) context.getBean("loggedCustomer");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        // TODO check value

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPassword(password);

        String nextPage = "/catalog";

        try {
            customer = customerManager.createCustomer(customer);
//            loggedCustomer = customer;
            HttpSession session = request.getSession();
            session.setAttribute("loggedCustomer", customer);
            session.setAttribute("theCart", new ShoppingCartImpl());
        } catch (CustomerExistantException ex) {
            nextPage = "/WEB-INF/jsp/error.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        return "Short description";
    }// </editor-fold>

}
