/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.business.impl;

import fr.mbh.bookeshop.business.api.CategoryManager;
import fr.mbh.bookeshop.dao.api.CategoryDAO;
import fr.mbh.bookeshop.domain.Category;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mahmoud
 */
public class CategoryManagerImpl implements CategoryManager {

    private CategoryDAO categoryDAO;

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    public Category getCategoryById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

}
