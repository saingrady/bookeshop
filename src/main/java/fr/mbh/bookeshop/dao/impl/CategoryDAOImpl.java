/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.dao.impl;

import fr.mbh.bookeshop.dao.api.CategoryDAO;
import fr.mbh.bookeshop.domain.Category;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mahmoud
 */
public class CategoryDAOImpl extends HibernateDaoSupport implements CategoryDAO {


    public List<Category> getCategories() {
        return  this.getHibernateTemplate().find("from Category");
    }

    public Category getCategoryById(int categoryId) {
        return this.getHibernateTemplate().get(Category.class,categoryId);

    }

}
