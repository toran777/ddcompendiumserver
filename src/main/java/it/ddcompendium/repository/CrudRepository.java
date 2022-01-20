package it.ddcompendium.repository;

import java.util.List;

public abstract class CrudRepository<T> {
    protected T findOne(T t) throws Exception {
        return null;
    }

    protected List<T> findAll(T t) throws Exception {
        return null;
    }

    protected void insert(T t) throws Exception {
    }

    protected void delete(Integer id) throws Exception {
    }
}
