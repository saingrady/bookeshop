/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.business.api;

import fr.mbh.bookeshop.business.exception.StockUnavailableException;
import fr.mbh.bookeshop.domain.Book;
import java.util.List;

/**
 *
 * @author Mahmoud
 */
public interface BookManager {

    public List<Book> getBooks();

    public List<Book> getBooksByCategory(int categoryId);

    public List<Book> getBooksByTitleAuthor(String keyword);

    public Book getBookByIsbn(String isbn);

    public void checkoutBook(String isbn,int quantity) throws StockUnavailableException;

}
