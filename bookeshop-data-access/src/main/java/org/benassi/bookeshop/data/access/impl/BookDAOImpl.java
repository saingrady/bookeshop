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

package org.benassi.bookeshop.data.access.impl;

import org.benassi.bookeshop.data.access.api.BookDAO;
import org.benassi.bookeshop.data.access.exception.InsufficientStockException;
import org.benassi.bookeshop.data.model.Book;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Hibernate implementation of book DAO interface
 */
public class BookDAOImpl extends HibernateDaoSupport implements BookDAO {

    final Logger logger = LoggerFactory.getLogger(BookDAOImpl.class);

    private int maxCatalogueResults;

    public void setMaxCatalogueResults(int maxCatalogueResults) {
        this.maxCatalogueResults = maxCatalogueResults;
    }

    public List<Book> getOffers() {
        return  this.getHibernateTemplate().find("from Book where offer > 0");
    }

    public List<Book> getCatalogue() {
        Criteria criteria = this.getSession().createCriteria(Book.class);
        criteria.setFirstResult(1);
        criteria.setMaxResults(maxCatalogueResults);
        return criteria.list();
    }

    public List<Book> getBooksByCategory(int categoryId) {
        return  this.getHibernateTemplate().find("from Book where category.id = ? ",categoryId);
    }

    public List<Book> getBooksByTitleAuthor(String keyword) {
        return  this.getHibernateTemplate().findByNamedParam("from Book b where upper(b.author.firstName) like :keyword or upper(b.author.lastName) like :keyword or upper(b.title) like :keyword ", "keyword", "%" + keyword.toUpperCase() + "%");
    }

    public Book getBookByIsbn(String isbn) {
        return this.getHibernateTemplate().get(Book.class,isbn);
    }


    public void updateStock(String isbn,int quantity) throws InsufficientStockException{
        Book book = this.getHibernateTemplate().get(Book.class,isbn);
        if (book.getStock() >= quantity ){
            book.setStock(book.getStock() - quantity);
            this.getHibernateTemplate().update(book);
        }else{
            logger.error("Insufficient stock of " + quantity + " item(s) for book " + isbn + ". Remaining only " + book.getStock()  + " item(s).");
            throw new InsufficientStockException(quantity,book.getStock(),isbn);
        }
    }

}
