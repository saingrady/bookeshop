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

import fr.mbh.bookeshop.dao.domain.Book;
import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import fr.mbh.bookeshop.dao.api.BookDAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BookDAOImpl extends HibernateDaoSupport implements BookDAO {

    public List<Book> getBooks() {
        //TODO return only available books (check quantity field or Book_Stock table when added)
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
