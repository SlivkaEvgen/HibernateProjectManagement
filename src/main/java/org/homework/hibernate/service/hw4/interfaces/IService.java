package org.homework.hibernate.service.hw4.interfaces;

import java.util.List;
import java.util.Optional;

public interface IService<T, ID> {

    Optional<T> getById(ID id);

    List<T> getAll();

    void delete(ID id);
}
