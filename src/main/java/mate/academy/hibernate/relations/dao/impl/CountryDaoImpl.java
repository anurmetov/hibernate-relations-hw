package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, CountryDao.class);
    }

    @Override
    public Country add(Country country) {
        return add(country);
    }

    @Override
    public Optional<Country> get(Long id) {
        return Optional.of(get(id)).orElseThrow(() -> new DataProcessingException("Cant get country from DB. ID: " + id));
    }
}
