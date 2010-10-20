/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.controller;

import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.domain.Book;
import fr.mbh.bookeshop.util.cart.ShoppingCart;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Mahmoud
 */
public class UpdateCartServlet extends HttpServlet {
   
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

        BookManager bookManager = (BookManager) context.getBean("bookManager");
        String selectedBookIsbn = request.getParameter("id");
        String categoryId = request.getParameter("categoryId");
        String action = request.getParameter("action");
        String from = request.getParameter("from");
        // TODO security : check id
        Book book = bookManager.getBookByIsbn(selectedBookIsbn);

//        ShoppingCart cart = (ShoppingCart)context.getBean("theCart");
        ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("theCart");
        String nextPage = from;
        if(cart != null){
            if(book != null)
                if ("add".equals(action))
                    cart.addItem(book.getIsbn());
                else cart.removeItem(book.getIsbn());
        }else{
            nextPage = "/WEB-INF/jsp/error.jsp";
            request.setAttribute("error","Please sign up or log in to buy from Book e-Shop");
        }

        request.setAttribute("categoryId", categoryId);
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
