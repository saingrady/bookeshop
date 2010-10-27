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

package fr.mbh.bookeshop.business.impl;

import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.business.exception.StockUnavailableException;
import fr.mbh.bookeshop.dao.api.BookDAO;
import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import fr.mbh.bookeshop.domain.Book;
import java.util.List;

/**
 * Book Manager implementation
 */
public class BookManagerImpl implements BookManager{

    private BookDAO bookDAO;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getBooks() {
        return bookDAO.getBooks();
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

    public void checkoutBook(String isbn,int quantity) throws StockUnavailableException{
        try {
            bookDAO.updateQuantity(isbn, quantity);
        } catch (InsufficientStockException ex) {
            throw new StockUnavailableException(ex.getMessage());
        }
    }

}