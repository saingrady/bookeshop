/*
 * This file is part of the Book-eShop project.
 *
 *    Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 *    Author :
 *   	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package org.benassi.bookeshop.business.impl;

import org.benassi.bookeshop.business.api.CategoryManager;
import org.benassi.bookeshop.data.access.api.CategoryDAO;
import org.benassi.bookeshop.data.model.Category;

import java.util.List;

/**
 * Category Manager implementation
 * @author Mahmoud Ben Hassine
 */
public class CategoryManagerImpl implements CategoryManager {

    private CategoryDAO categoryDAO;

    /** {@inheritDoc} */
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    /** {@inheritDoc} */
    public Category getCategoryById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    /*
    * Setters for DI
    */
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

}
