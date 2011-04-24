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

import java.util.List;

/**
 * BookCatalogueAction : loads discount books and makes them available to the view
 */
public class BookCatalogueAction {

    private BookManager bookManager;

    private List<Book> books;

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String execute() throws Exception {
        books =  bookManager.getDiscountBooks();
        return "success";
    }

}
