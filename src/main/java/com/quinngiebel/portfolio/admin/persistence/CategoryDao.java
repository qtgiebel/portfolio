package com.quinngiebel.portfolio.admin.persistence;

import com.quinngiebel.portfolio.admin.entities.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategoryDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


/* Create */
    public Integer addCategory(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer)session.save(category);
        transaction.commit();
        session.close();
        return id;
    }

/* Read */
    public List<Category> getAllCategories(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        List<Category> categories = session.createQuery(query).getResultList();
        session.close();

        return categories;
    }

    public List<Category> getCategoriesByColumn(String column, Object value) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);

        Expression<String> propertyPath = root.get(column);
        query.where(builder.equal(propertyPath, value));

        List<Category> results = session.createQuery(query).getResultList();
        session.close();

        return results;
    }

    public Category getCategoryById(int id) {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, id);
        session.close();

        return category;
    }

    public Category getCategoryByCategoryName(String title) {
        return getCategoriesByColumn("title", title).get(0);
    }

/* Update */

/* Delete */
    public void deleteCategory(Category toDelete) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(toDelete);
        transaction.commit();
        session.close();
    }

}
