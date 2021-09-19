package org.homework.hibernate.repository.interfaces;


import org.homework.hibernate.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E extends BaseModel<ID>, ID> {

    Optional<E> findById(ID id);

    List<E> findAll();

    E save(E e);

    List<E> saveAll(Iterable<E> itrb);

    ID update(E e);

    void deleteById(ID id);

    void close();
}
