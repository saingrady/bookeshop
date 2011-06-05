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
import com.opensymphony.xwork2.Preparable;
import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.web.util.BookUtil;

import java.util.List;

/**
 * BookCatalogueAction : loads discount books and makes them available to the view
 */
public class BookCatalogueAction extends ActionSupport implements Preparable{

    private BookManager bookManager;

    private BookUtil bookUtil;

    private List<Book> books;

    @Override
    public void prepare() throws Exception {
        books =  bookManager.getDiscountBooks();
        bookUtil.prepareBooksForView(books);
    }

    /*
     * Getters for model
     */

    public List<Book> getBooks() {
        return books;
    }

    /*
     * Setters for DI
     */

    public void setBookUtil(BookUtil bookUtil) {
        this.bookUtil = bookUtil;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }
}
