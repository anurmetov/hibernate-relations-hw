package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao<Actor> implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Actor.class);
    }

    @Override
    public Actor add(Actor actor) {
        return super.add(actor);
    }

    @Override
    public Optional<Actor> get(Long id) {
        return Optional.of(super.get(id)).orElseThrow(()
                -> new DataProcessingException("Cant get author from DB. ID: " + id));
    }
}
