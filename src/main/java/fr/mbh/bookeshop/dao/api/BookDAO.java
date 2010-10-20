/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.dao.api;

import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import fr.mbh.bookeshop.domain.Book;
import java.util.List;

/**
 *
 * @author Mahmoud
 */
public interface BookDAO {

    public List<Book> getBooks();

    public List<Book> getBooksByCategory(int categoryId);

    public List<Book> getBooksByTitleAuthor(String keyword);

    public Book getBookByIsbn(String isbn);

    public void updateQuantity(String isbn,int quantity) throws InsufficientStockException;
    
}
