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

package fr.mbh.bookeshop.dao.api;

import fr.mbh.bookeshop.dao.domain.Book;
import fr.mbh.bookeshop.dao.exception.InsufficientStockException;

import java.util.List;


public interface BookDAO {

    public List<Book> getBooks();

    public List<Book> getBooksByCategory(int categoryId);

    public List<Book> getBooksByTitleAuthor(String keyword);

    public Book getBookByIsbn(String isbn);

    public void updateStock(String isbn,int quantity) throws InsufficientStockException;
    
}
