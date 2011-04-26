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

package org.benassi.bookeshop.data.access.api;


import org.benassi.bookeshop.data.access.exception.InsufficientStockException;
import org.benassi.bookeshop.data.model.Book;

import java.util.List;

/**
 * Interface for book data access object
 * (may be split in BookDAO and BookStockDAO)
 */
public interface BookDAO {

    /**
     * get a book by id
     * @param isbn the book id = isbn
     * @return the found book or null if no book with such isbn
     */
    public Book getBookByIsbn(String isbn);

    /**
     * get discount books
     * @return discount books list
     */
    public List<Book> getOffers();

    /**
     * get books in catalogue
     * @return catalogue books list
     */
    public List<Book> getCatalogue();

    /**
     * get book by category
     * @param categoryId the category id
     * @return the list of found books in the for the given category
     */
    public List<Book> getBooksByCategory(int categoryId);

    /**
     * look for book by Title or Author
     * @param keyword the keyword
     * @return the list of found books for the given keyword
     */
    public List<Book> getBooksByTitleAuthor(String keyword);

    /**
     * update the book stock
     * @param isbn the book id
     * @param quantity the quantity to checkout
     * @throws org.benassi.bookeshop.data.access.exception.InsufficientStockException if no stock available for the given quantity
     */
    public void updateStock(String isbn, int quantity) throws InsufficientStockException;
    
}
