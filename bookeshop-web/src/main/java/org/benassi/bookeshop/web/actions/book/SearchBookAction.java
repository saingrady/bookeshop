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
 * Action class to look for books by Title/Author
 */
public class SearchBookAction{

    private String keyword;

    private BookManager bookManager;

    private List<Book> foundItems;

    private String error;

    public List<Book> getFoundItems() {
        return foundItems;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getError() {
        return error;
    }

    public String execute(){
        foundItems = bookManager.getBooksByTitleAuthor(keyword);
        if (keyword.isEmpty()){
            error = "Please specify a keyword for search";
            return "error";
        }
        else
        return "success";
    }

}
