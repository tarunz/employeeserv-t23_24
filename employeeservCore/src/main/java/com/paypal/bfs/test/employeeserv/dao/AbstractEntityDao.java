package com.paypal.bfs.test.employeeserv.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class AbstractEntityDao <T> {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    T get(Class<T> clazz, Serializable idValue) {
        return getSession().get(clazz, idValue);
    }

    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }
}
