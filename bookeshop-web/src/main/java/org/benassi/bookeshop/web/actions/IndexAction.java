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

package org.benassi.bookeshop.web.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.util.ServletContextAware;
import org.benassi.bookeshop.business.api.CategoryManager;
import org.benassi.bookeshop.data.model.Category;
import org.benassi.bookeshop.web.util.BookeshopConstants;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * IndexAction : use category manager to get available categories
 * and put the list in servlet context (ServletContextAware)
 * //available categories are application scoped (until this requirement changes)
 * @author Mahmoud Ben Hassine
 */
public class IndexAction extends ActionSupport implements ServletContextAware{

    private CategoryManager categoryManager;

    @Override
    public void setServletContext(ServletContext context) {
        List<Category> categories = categoryManager.getCategories();
        context.setAttribute(BookeshopConstants.APPLICATION_CATEGORIES, categories);
    }

    /*
     * Setters for DI
     */
    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

}
