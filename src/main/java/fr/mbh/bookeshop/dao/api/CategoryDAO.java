/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.dao.api;

import fr.mbh.bookeshop.domain.Category;
import java.util.List;

/**
 *
 * @author Mahmoud
 */
public interface CategoryDAO {

    public List<Category> getCategories();

    public Category getCategoryById(int categoryId);
}
