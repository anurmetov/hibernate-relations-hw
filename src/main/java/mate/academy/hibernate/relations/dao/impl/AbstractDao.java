package mate.academy.hibernate.relations.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;
    private final Class<T> entityClass;


    protected AbstractDao(SessionFactory sessionFactory, Class<T> entityClass) {
        this.factory = sessionFactory;
        this.entityClass = entityClass;

    }

    public T add(T entity) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();

        } catch (Exception ex) {
            ex.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }

    public Optional<T> get(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            T result = session.find(entityClass, id);
            tx.commit();
            return Optional.ofNullable(result);

        } catch (Exception ex) {
            ex.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }
            return Optional.empty();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}


