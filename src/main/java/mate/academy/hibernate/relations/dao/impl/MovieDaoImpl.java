package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Movie.class);
    }

    @Override
    public Movie add(Movie movie) {
        return super.add(movie);
    }

    @Override
    public Optional<Movie> get(Long id) {
        return Optional.of(super.get(id)).orElseThrow(()
                -> new DataProcessingException("Cant get movie from DB. ID: " + id));
    }
}
