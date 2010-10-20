package fr.mbh.bookeshop.dao;

import fr.mbh.bookeshop.dao.api.BookDAO;
import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import fr.mbh.bookeshop.domain.Book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: mahmoud
 * Date: Aug 10, 2010
 * Time: 7:31:43 AM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class BookDAOImplTest {

    @Autowired
    private BookDAO bookDao;

    @Test
    public void testGetBooks() {
        List<Book> books = bookDao.getBooks();
        assertEquals(22,books.size());
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
        bookDao.updateQuantity(isbn,20);   //there are only 100 items available in stock
        assertEquals(80,bookDao.getBookByIsbn(isbn).getQuantity());
    }

    @Test
    @ExpectedException(InsufficientStockException.class)
    public void testUpdateQuantityKO() throws InsufficientStockException {
        bookDao.updateQuantity("9781430216407",200);   //there are only 100 items available in stock
    }

}
