package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;
    private final Class<T> entityClass;

    protected AbstractDao(SessionFactory sessionFactory, Class<T> entityClass) {
        this.factory = sessionFactory;
        this.entityClass = entityClass;

    }

    public T add(T entity) {
        Transaction tx = null;
        Session session = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;

        } catch (Exception ex) {

            if (tx != null) {
                tx.rollback();
            }
            throw new DataProcessingException(
                    "The " + entityClass.getSimpleName() + " could not be added to DB. ", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.find(entityClass, id));
        } catch (Exception ex) {
            throw new DataProcessingException("Can't get "
                    + entityClass.getSimpleName() + " from DB", ex);
        }
    }

}


