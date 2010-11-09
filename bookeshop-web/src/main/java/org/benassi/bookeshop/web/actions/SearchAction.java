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

package org.benassi.bookeshop.web.actions;

import com.sun.xml.internal.xsom.impl.parser.state.AttributesImpl;
import fr.mbh.bookeshop.business.api.BookManager;
import fr.mbh.bookeshop.dao.domain.Book;
import org.apache.struts2.interceptor.RequestAware;

import java.util.List;
import java.util.Map;

/**
 * Action class to lokk for books by Title/Author
 */
public class SearchAction implements RequestAware{

    private String keyword;
    private BookManager bookManager;
    private Map<String, Object> request;

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String execute(){
        List<Book> foundBooks = bookManager.getBooksByTitleAuthor(keyword);
        request.put("foundBooks", foundBooks);
        return "success";
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
}
