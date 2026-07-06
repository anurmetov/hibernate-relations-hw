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
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;

        } catch (Exception ex) {
            ex.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }
            throw new DataProcessingException(
                    "The object could not be loaded from a DB. ", ex);
        }
    }

    public Optional<T> get(Long id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            T result = session.find(entityClass, id);
            tx.commit();
            return Optional.ofNullable(result);

        } catch (Exception ex) {
            ex.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }
            throw new DataProcessingException(
                    "The object could not be loaded from a DB. ", ex);
        }
    }
}


