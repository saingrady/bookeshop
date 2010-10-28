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

import fr.mbh.bookeshop.business.api.CategoryManager;
import fr.mbh.bookeshop.dao.domain.Category;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * IndexAction : use category manager to get available categories
 * and put the list in servlet context (ServletContextAware)
 * //available categories are application scoped (until this requirement changes)
 */
public class IndexAction implements ServletContextAware{

    private CategoryManager categoryManager;

    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    public String execute(){
        return "success";
    }

    @Override
    public void setServletContext(ServletContext context) {
        List<Category> categories = categoryManager.getCategories();
        context.setAttribute("categories", categories);
    }
}
