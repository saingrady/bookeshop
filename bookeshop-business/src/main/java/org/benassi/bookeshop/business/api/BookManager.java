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

package org.benassi.bookeshop.business.api;

import org.benassi.bookeshop.business.exception.OutOfStockException;
import org.benassi.bookeshop.data.model.Book;

import java.util.List;

/**
 *
 * Book Manager business interface
 * @author Mahmoud Ben Hassine
 * 
 */
public interface BookManager {

    /**
     * lists discount books
     * @return the list of discount books
     */
    public List<Book> getDiscountBooks();

    /**
     * look for books by category
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
     * look for a book by ISBN
     * @param isbn the book ISBN to look for
     * @return the book
     */
    public Book getBookByIsbn(String isbn);

    /**
     * checkout a book from the stock
     * @param isbn the book ISBN
     * @param quantity the quantity to checkout
     * @throws {@link org.benassi.bookeshop.business.exception.OutOfStockException} thrown if no stock is available for the requested quantity
     */
    public void checkoutBook(String isbn, int quantity) throws OutOfStockException;

}
