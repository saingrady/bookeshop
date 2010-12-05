/*
 * This file is part of the Book-eShop project.
 *
 *  Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 * Author :
 * 	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package fr.mbh.bookeshop.dao.impl;

import fr.mbh.bookeshop.dao.api.BookDAO;
import fr.mbh.bookeshop.dao.domain.Book;
import fr.mbh.bookeshop.dao.domain.BookOffer;
import fr.mbh.bookeshop.dao.domain.BookStock;
import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Hibernate implementation of book DAO interface
 */
public class BookDAOImpl extends HibernateDaoSupport implements BookDAO {

    public List<Book> getOffers() {
        return  this.getHibernateTemplate().find("from Book where isbn in (from BookOffer where offer > 0)");
    }

    public int getBookStock(String isbn) {
        BookStock bookStock = this.getHibernateTemplate().get(BookStock.class,isbn);
        return bookStock.getStock();
    }

    public int getBookOffer(String isbn) {
        BookOffer bookOffer = this.getHibernateTemplate().get(BookOffer.class,isbn);
        return bookOffer.getOffer();
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


    public void updateStock(String isbn,int quantity) throws InsufficientStockException{
        // TODO should throw DataAccessException (add trigger for negative stock)
        BookStock bookStock = this.getHibernateTemplate().get(BookStock.class,isbn);
        if (bookStock.getStock() >= quantity ){
            bookStock.setStock(bookStock.getStock() - quantity);
            this.getHibernateTemplate().update(bookStock);
        }else
            throw new InsufficientStockException("Insufficient stock of " + quantity + " items for book '"+ getBookByIsbn(isbn).getTitle() + "'! Remaining only " + bookStock.getStock()  + " item(s)");
    }

}
