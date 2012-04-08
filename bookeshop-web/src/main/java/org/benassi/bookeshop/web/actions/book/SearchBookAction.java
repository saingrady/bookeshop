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
import org.springframework.context.MessageSource;

import java.util.List;

/**
 * Action class to look for books by Title/Author
 * @author Mahmoud Ben Hassine
 */
public class SearchBookAction{

    private String keyword;

    private BookManager bookManager;

    private BookUtil bookUtil;

    private List<Book> foundItems;

    private MessageSource messageProvider;

    public String execute(){
        foundItems = bookManager.getBooksByTitleAuthor(keyword);//empty value validated in client side with javascript
        bookUtil.prepareBooksForView(foundItems);
        return ActionSupport.SUCCESS;
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

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
    }

    /*
     * Setters for request parameters
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /*
     * Getters for model
     */
    public String getKeyword() {
        return keyword;
    }

    public List<Book> getFoundItems() {
        return foundItems;
    }

    public String getProcessingMessage() {
        return messageProvider.getMessage("web.search.wait",new Object[]{keyword},null,null);
    }

}
