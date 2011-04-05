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
import org.benassi.bookeshop.data.model.Book;
import org.benassi.bookeshop.web.beans.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * BookCatalogueAction : loads discount books and makes them available to the view
 */
public class BookCatalogueAction {

    private BookManager bookManager;

    private List<ItemBean> items;

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public List<ItemBean> getItems() {
        return items;
    }

    public String execute() throws Exception {
        items = new ArrayList<ItemBean>();
        List<Book> books = bookManager.getDiscountBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book =  books.get(i);
            ItemBean itemBean = new ItemBean(
                    book,
                    bookManager.getBookStockStatus(book.getIsbn()),
                    bookManager.getBookOffer(book.getIsbn())
            );
            items.add(itemBean);
        }
        return "success";
    }

}
