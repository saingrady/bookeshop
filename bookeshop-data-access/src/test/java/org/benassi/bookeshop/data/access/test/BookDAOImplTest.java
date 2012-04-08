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

package org.benassi.bookeshop.data.access.test;

import org.benassi.bookeshop.data.access.api.BookDAO;
import org.benassi.bookeshop.data.access.exception.InsufficientStockException;
import org.benassi.bookeshop.data.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class BookDAOImplTest {

    @Autowired
    private BookDAO bookDao;

    @Test
    public void testGetCatalogue(){
        List<Book> books = bookDao.getCatalogue();
        assertEquals(10,books.size());
    }


    @Test
    public void testGetBooksByCategory() {
        List<Book> books = bookDao.getBooksByCategory(1);
        assertEquals(6,books.size());
    }

    @Test
    public void testGetBooksByTitleAuthor() {
        String keyword = "pro";
        List<Book> books = bookDao.getBooksByTitleAuthor(keyword);
        assertEquals(4,books.size());
    }

    @Test
    public void testGetBookByIsbn() {
        Book book = bookDao.getBookByIsbn("9781430216407");
        assertNotNull(book);
        assertEquals("Pro Spring dm Server",book.getTitle());
    }

    @Test
    public void testUpdateQuantityOK() throws InsufficientStockException {
        String isbn = "9781430216407"; // pro spring dm server
        bookDao.updateStock(isbn,20);   //there are only 100 items available in stock
        assertEquals(80,bookDao.getBookByIsbn(isbn).getStock());
    }

    @Test
    @ExpectedException(InsufficientStockException.class)
    public void testUpdateQuantityKO() throws InsufficientStockException {
        String isbn = "9781430216407";
        bookDao.updateStock(isbn,200);   //there are only 100 items available in stock
        assertEquals(100,bookDao.getBookByIsbn(isbn).getStock());
    }

}
