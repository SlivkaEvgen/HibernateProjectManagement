package org.homework.hibernate.repository;

import org.homework.hibernate.model.BaseModel;
import org.homework.hibernate.repository.interfaces.CrudRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFactory {

    private final static Map<String, CrudRepository> REPOSITORIES = new ConcurrentHashMap<>();

    public synchronized static <E extends BaseModel<ID>, R extends CrudRepository<E, ID>, ID> CrudRepository<E, ID> of(Class<E> modelClass) {
        final String modelName = modelClass.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new CrudRepositoryHibernateImpl<>(modelClass));
        }
        return REPOSITORIES.get(modelName);
    }
}
