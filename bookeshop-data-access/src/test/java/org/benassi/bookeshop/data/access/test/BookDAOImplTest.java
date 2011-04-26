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
    public void testGetBookOfferOK() {
        String isbn = "9781430216407"; // 5% discount for book 'pro spring dm server'
        int offer = bookDao.getBookByIsbn(isbn).getOffer();
        assertEquals(5,offer);
    }

    @Test
    public void testGetBookOfferKO() {
        String isbn = "9781430219088"; // no offer for book 'Pro Hyper-V'
        int offer = bookDao.getBookByIsbn(isbn).getOffer();
        assertEquals(0,offer);
    }

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
        bookDao.updateStock("9781430216407",200);   //there are only 100 items available in stock
    }

}
