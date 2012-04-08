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

package org.benassi.bookeshop.web.actions.book;

import com.opensymphony.xwork2.ActionSupport;
import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.web.util.BookUtil;
import org.benassi.bookeshop.web.util.AjaxContentProvider;
import org.springframework.context.MessageSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Action class to get book details
 * @author Mahmoud Ben Hassine
 */
public class BookDetailsAction {

    private BookManager bookManager;

    private BookUtil bookUtil;

    private String bookId;

    private Book book;

    private String error;

    private InputStream inputStream;

    private AjaxContentProvider ajaxContentProvider;

    private MessageSource messageProvider;

    public String execute(){
        book = bookManager.getBookByIsbn(bookId);
        if (book != null){
            //prepare book for view
            book.setStockStatus(bookUtil.getStockStatus(book.getStock()));
            book.setFormattedPublishDate(bookUtil.formatPublishDate(book.getPublishDate()));
            String message = ajaxContentProvider.getBookDetailsAsHtml(book);
            inputStream = new ByteArrayInputStream(message.getBytes());
            return ActionSupport.SUCCESS;
        }
        else {
            error = messageProvider.getMessage("web.error.book.unknown",new Object [] {bookId},null,null);
            return ActionSupport.ERROR;
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

    public Book getBook() {
        return book;
    }

    public String getError() {
        return error;
    }
}
