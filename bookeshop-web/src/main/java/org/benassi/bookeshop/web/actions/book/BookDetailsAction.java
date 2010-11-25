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

import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.dao.domain.Book;
import org.benassi.bookeshop.web.beans.ItemBean;

/**
 * BookDetailsAction : action class to get book details
 */
public class BookDetailsAction {

    private BookManager bookManager;

    private String bookId;

    private ItemBean item;

    private String error;

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getError() {
        return error;
    }

    public String execute(){
        Book book = bookManager.getBookByIsbn(bookId);
        if (book != null) {
            String stock = bookManager.getBookStockStatus(bookId);
            int offer = bookManager.getBookOffer(bookId);
            item = new ItemBean();
            item.setBook(book);
            item.setStockStatus(stock);
            if (offer>0)
                item.setOffer(offer);
            return "success";
        }
        else {
            error = "No such book with ISBN = " + bookId;
            return "error";
        }

    }
}
