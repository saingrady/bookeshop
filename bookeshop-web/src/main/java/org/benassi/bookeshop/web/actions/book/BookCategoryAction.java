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
import org.benassi.bookeshop.business.api.CategoryManager;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.data.model.Category;
import org.benassi.bookeshop.web.util.BookUtil;
import org.springframework.context.MessageSource;

import java.util.List;

/**
 * Action class to get books by category
 * @author Mahmoud Ben Hassine
 */
public class BookCategoryAction {

    private CategoryManager categoryManager;

    private BookManager bookManager;

    private BookUtil bookUtil;

    private int categoryId;

    private Category category;

    private String error;

    private List<Book> categoryItems;

    private MessageSource messageProvider;

    public String execute(){
        category = categoryManager.getCategoryById(categoryId);
        if (category != null){
            categoryItems = bookManager.getBooksByCategory(categoryId);
            bookUtil.prepareBooksForView(categoryItems);
            return ActionSupport.SUCCESS;
        }
        else {
            error = messageProvider.getMessage("web.error.category.unknown",new Object [] {categoryId},null,null);
            return ActionSupport.ERROR;
        }
    }

    /*
     * Setters for DI
     */

    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

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

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /*
     * Getters for model
     */

    public Category getCategory() {
        return category;
    }

    public String getError() {
        return error;
    }

    public List<Book> getCategoryItems() {
        return categoryItems;
    }

}
