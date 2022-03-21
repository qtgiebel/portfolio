package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.Category;
import org.hibernate.mapping.Set;

public class CategoryDao extends GenericDao<Category> {

    public CategoryDao() {
        this.setType(Category.class);
    }

}
