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

package org.benassi.bookeshop.web.actions.book;

import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.web.util.BookUtil;
import org.benassi.bookeshop.web.util.AjaxContentProvider;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * BookDetailsAction : action class to get book details
 */
public class BookDetailsAction {

    private BookManager bookManager;

    private BookUtil bookUtil;

    private String bookId;

    private Book book;

    private String error;

    private InputStream inputStream;

    private AjaxContentProvider ajaxContentProvider;

    public String execute(){
        book = bookManager.getBookByIsbn(bookId);
        if (book != null){
            //prepare book for view
            book.setStockStatus(bookUtil.getStockStatus(book.getStock()));
            book.setFormattedPublishDate(bookUtil.formatPublishDate(book.getPublishDate()));
            String message = ajaxContentProvider.getBookDetailsAsHtml(book);
            inputStream = new ByteArrayInputStream(message.getBytes());
            return "success";
        }
        else {
            error = "No such book with ISBN = " + bookId;
            inputStream = new ByteArrayInputStream(error.getBytes());
            return "error";
        }

    }

    /*
     * Setters for DI
     */

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public void setBookUtil(BookUtil bookUtil) {
        this.bookUtil = bookUtil;
    }

    public void setAjaxContentProvider(AjaxContentProvider ajaxContentProvider) {
        this.ajaxContentProvider = ajaxContentProvider;
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

    public Book getBook() {
        return book;
    }

    public String getError() {
        return error;
    }
}
