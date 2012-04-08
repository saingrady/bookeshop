/*
 * The MIT License
 *
 *   Copyright (c) 2012, Mahmoud Ben Hassine (md.benhassine@gmail.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
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
 * @author Mahmoud Ben Hassine
 */
public class BookManagerImpl implements BookManager{

    private BookDAO bookDAO;

    /** {@inheritDoc} */
    public List<Book> getDiscountBooks() {
        //Business rule : if no available offers, display some books from the catalogue
        List<Book> books = bookDAO.getOffers();
        return books.size() != 0 ? books : bookDAO.getCatalogue();
    }

    /** {@inheritDoc} */
    public List<Book> getBooksByCategory(int categoryId) {
        return bookDAO.getBooksByCategory(categoryId);
    }

    /** {@inheritDoc} */
    public List<Book> getBooksByTitleAuthor(String keyword) {
        return bookDAO.getBooksByTitleAuthor(keyword);
    }

    /** {@inheritDoc} */
    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    /** {@inheritDoc} */
    public void checkoutBook(String isbn,int quantity) throws OutOfStockException {
        try {
            bookDAO.updateStock(isbn, quantity);
        } catch (InsufficientStockException ise) {
            throw new OutOfStockException(ise.getRequestedQuantity(),ise.getCurrentStock(),ise.getBookISBN());
        }
    }

    /*
     * Setters for DI
     */
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

}
