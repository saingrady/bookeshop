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

import com.opensymphony.xwork2.ActionSupport;
import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.business.api.CategoryManager;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.data.model.Category;
import org.benassi.bookeshop.web.util.BookUtil;
import org.springframework.context.MessageSource;

import java.util.List;

/**
 * BookCategoryAction : action class to get books by category
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
