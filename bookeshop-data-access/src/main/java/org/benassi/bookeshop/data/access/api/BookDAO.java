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

package org.benassi.bookeshop.data.access.api;


import org.benassi.bookeshop.data.access.exception.InsufficientStockException;
import org.benassi.bookeshop.data.model.Book;

import java.util.List;

/**
 * Interface for book data access object
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
