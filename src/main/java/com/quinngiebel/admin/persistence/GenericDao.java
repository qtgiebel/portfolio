package com.quinngiebel.admin.persistence;

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

/**
 * Creates a generic DAO that can accept a class type parameter to operate on any entity.
 * Inspired by a class made by Paula Waite.
 * @param <T> The class type of the entity.
 */
public class GenericDao<T> {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    private Class<T> type;

    public GenericDao() {
    }

    public GenericDao(Class<T> type) {
        this.type = type;
    }

/* Create */
    public int insert(T toInsert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int)session.save(toInsert);
        transaction.commit();
        session.close();
        return id;
    }

/* Read */
    public List<T> getAll(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> resultList = session.createQuery(query).getResultList();
        session.close();

        return resultList;
    }

    public T getById(int id) {
        Session session = sessionFactory.openSession();
        T result = session.get(type, id);
        session.close();

        return result;
    }

    public List<T> getByColumn(String column, Object value) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        Expression<String> propertyPath = root.get(column);
        query.where(builder.equal(propertyPath, value));

        List<T> results = session.createQuery(query).getResultList();
        session.close();

        return results;
    }

/* Update */
    public void update(T toUpdate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(toUpdate);
        transaction.commit();
        session.close();
    }

/* Delete */
    public void delete(T toDelete) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(toDelete);
        transaction.commit();
        session.close();
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
