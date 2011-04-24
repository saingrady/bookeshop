/*
 * This file is part of the Book-eShop project.
 *
 *    Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 *    Author :
 *   	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package org.benassi.bookeshop.business.api;

import org.benassi.bookeshop.business.exception.OutOfStockException;
import org.benassi.bookeshop.data.model.Book;

import java.util.List;

/**
 *
 * Book Manager interface
 * 
 */
public interface BookManager {

    /**
     * lists discount books
     * @return the list of discount books
     */
    public List<Book> getDiscountBooks();

    /**
     * look for books for a category
     * @param categoryId the category ID
     * @return the list of books belonging to the given category
     */
    public List<Book> getBooksByCategory(int categoryId);

    /**
     * look for books by title/author
     * @param keyword the keyword
     * @return  the list of found books for given keyword
     */
    public List<Book> getBooksByTitleAuthor(String keyword);

    /**
     * look for a book by its ISBN
     * @param isbn the book ISBN to look for
     * @return the book
     */
    public Book getBookByIsbn(String isbn);

    /**
     * checkout a book from the stock
     * @param isbn the book ISBN
     * @param quantity the quantity to checkout
     * @throws org.benassi.bookeshop.business.exception.OutOfStockException thrown if no stock is available for the given quantity
     */
    public void checkoutBook(String isbn, int quantity) throws OutOfStockException;

}