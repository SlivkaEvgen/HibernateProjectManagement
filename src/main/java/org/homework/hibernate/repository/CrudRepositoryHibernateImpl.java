package org.homework.hibernate.repository;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.homework.hibernate.model.BaseModel;
import org.homework.hibernate.repository.interfaces.CrudRepository;
import org.homework.hibernate.utils.HibernateSessionFactory;
import org.homework.hibernate.utils.SessionsOpenClose;
import java.io.Closeable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CrudRepositoryHibernateImpl<T extends BaseModel<ID>, ID> implements Closeable, CrudRepository<T, ID> {

    private final Class<T> modelClass;
    private final SessionsOpenClose sessionsOpenClose;

    public CrudRepositoryHibernateImpl(Class<T> modelClass) {
        this.modelClass = modelClass;
        this.sessionsOpenClose = SessionsOpenClose.getInstance();
    }

    @Override
    public List<T> saveAll(Iterable<T> itrb) {
        return StreamSupport.stream(itrb.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public T save(T t) {
        Session session = sessionsOpenClose.createSession();
        ID id = t.getId() == null ? save(t, session) : update(t);
        Optional<T> result = getById(id, session);
        sessionsOpenClose.closeSession(session);
        return result.get();
    }

    public ID save(T t, Session session) {
        return (ID) session.save(t);
    }

    @Override
    public ID update(T t) {
        Session session = sessionsOpenClose.createSession();
        session.saveOrUpdate(t);
        ID id = update(t, session);
        session.close();
        return id;
    }

    private ID update(T t, Session session) {
        return t.getId();
    }

    @Override
    public void deleteById(ID id) {
        Session session = sessionsOpenClose.createSession();
        getById(id, session).ifPresent(entity -> session.remove(entity));
        sessionsOpenClose.closeSession(session);
    }

    @Override
    public Optional<T> findById(ID id) {
        Session session = sessionsOpenClose.createSession();
        Optional<T> result = getById(id, session);
        sessionsOpenClose.closeSession(session);
        return result;
    }

    @SneakyThrows
    @Override
    public List<T> findAll() {
        Session session = sessionsOpenClose.createSession();
        JpaCriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(modelClass);
        List<T> resultList = session.createQuery(query.select(query.from(modelClass))).getResultList();
        sessionsOpenClose.closeSession(session);
        return resultList;
    }

    private Optional<T> getById(ID id, Session session) {
        return Optional.ofNullable(session.get(modelClass, id));
    }

    @Override
    public void close() {
        HibernateSessionFactory.close();
    }
}
