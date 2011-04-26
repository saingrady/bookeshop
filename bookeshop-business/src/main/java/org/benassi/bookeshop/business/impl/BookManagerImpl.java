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

package org.benassi.bookeshop.business.impl;

import org.benassi.bookeshop.business.api.BookManager;
import org.benassi.bookeshop.business.exception.OutOfStockException;
import org.benassi.bookeshop.data.access.api.BookDAO;
import org.benassi.bookeshop.data.access.exception.InsufficientStockException;
import org.benassi.bookeshop.data.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Book Manager implementation
 */
public class BookManagerImpl implements BookManager{

    final Logger logger = LoggerFactory.getLogger(BookManagerImpl.class);

    private BookDAO bookDAO;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getDiscountBooks() {
        //Business rule : if no available offers, display some books from the catalogue
        List<Book> books = bookDAO.getOffers();
        return books.size() != 0 ? books : bookDAO.getCatalogue();
    }

    public int getBookOffer(String isbn) {
        return bookDAO.getBookByIsbn(isbn).getOffer();
    }

    public List<Book> getBooksByCategory(int categoryId) {
        return bookDAO.getBooksByCategory(categoryId);
    }

    public List<Book> getBooksByTitleAuthor(String keyword) {
        return bookDAO.getBooksByTitleAuthor(keyword);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    public void checkoutBook(String isbn,int quantity) throws OutOfStockException {
        try {
            bookDAO.updateStock(isbn, quantity);
        } catch (InsufficientStockException ex) {
            logger.error(ex.getMessage());
            throw new OutOfStockException(ex.getMessage());
        }
    }

}
