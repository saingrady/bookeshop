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

import fr.mbh.bookeshop.dao.exception.InsufficientStockException;
import org.benassi.bookeshop.data.model.Book;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.MessageSource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Hibernate implementation of book DAO interface
 */
public class BookDAOImpl extends HibernateDaoSupport implements BookDAO {

    private MessageSource messages;

    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }

    public List<Book> getOffers() {
        return  this.getHibernateTemplate().find("from Book where offer > 0");
    }

    public List<Book> getBooksByCategory(int categoryId) {
        return  this.getHibernateTemplate().find("from Book where category.id = ? ",categoryId);
    }

    public List<Book> getBooksByTitleAuthor(String keyword) {

        Criteria criteria = this.getSession().createCriteria(Book.class);
        //TODO : fix & test this restriction
        criteria.add( //Restrictions.or(
                        Restrictions.like("title",keyword, MatchMode.ANYWHERE).ignoreCase()
                        //Restrictions.ilike("author.firstName",keyword, MatchMode.ANYWHERE) // ilike = a case-insensitive like (see hibernate javadoc)
                        //Restrictions.ilike("author.lastName",keyword, MatchMode.ANYWHERE) // ilike = a case-insensitive like (see hibernate javadoc)
                ) ;
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
        Book book = this.getHibernateTemplate().get(Book.class,isbn);
        if (book.getStock() >= quantity ){
            book.setStock(book.getStock() - quantity);
            this.getHibernateTemplate().update(book);
        }else
            throw new InsufficientStockException(messages.getMessage("stock.insufficient",new Object[]{quantity,getBookByIsbn(isbn).getTitle(),book.getStock()},"Insufficient Stock",null));
    }

}
