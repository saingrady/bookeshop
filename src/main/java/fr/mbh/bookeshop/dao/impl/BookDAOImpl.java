/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.dao.impl;

import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import fr.mbh.bookeshop.dao.api.BookDAO;
import fr.mbh.bookeshop.domain.Book;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Mahmoud
 */

public class BookDAOImpl extends HibernateDaoSupport implements BookDAO {

    public List<Book> getBooks() {
        return  this.getHibernateTemplate().find("from Book");
    }

    public List<Book> getBooksByCategory(int categoryId) {
        return  this.getHibernateTemplate().find("from Book where category = ? ",categoryId);
    }

    public List<Book> getBooksByTitleAuthor(String keyword) {

        Criteria criteria = this.getSession().createCriteria(Book.class);
        criteria.add( Restrictions.or(
                        Restrictions.like("title",keyword, MatchMode.ANYWHERE).ignoreCase(),
                        Restrictions.ilike("author",keyword, MatchMode.ANYWHERE) // ilike = a case-insensitive like (see hibernate javadoc)
                ) );
        return criteria.list();

        /*
        * may use the following query but it is case sensitive
        */
        //  return  this.getHibernateTemplate().findByNamedParam("from Book b where b.author like :keyword or b.title like :keyword ", "keyword", "%" + keyword + "%");
    }

    public Book getBookByIsbn(String isbn) {
        return this.getHibernateTemplate().get(Book.class,isbn);
    }
    
    public void updateQuantity(String isbn,int quantity) throws InsufficientStockException{
        Book book = getBookByIsbn(isbn);
        if (book.getQuantity() >= quantity ){
            book.setQuantity(book.getQuantity() - quantity);
            this.getHibernateTemplate().update(book);
        }else
            throw new InsufficientStockException("Insufficient stock of " + quantity + " items for book '"+ book.getTitle() + "'");
    }

}
