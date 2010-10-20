/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.business.impl;

import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.business.exception.StockUnavailableException;
import fr.mbh.bookeshop.dao.api.BookDAO;
import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import fr.mbh.bookeshop.domain.Book;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mahmoud
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
