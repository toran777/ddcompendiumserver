package it.ddcompendium.repository;

import java.util.List;

public interface CrudRepository<T> {

	public T findOne(T t) throws Exception;

	public List<T> findAll(T t) throws Exception;

	public void insert(T t) throws Exception;

	public void update(T t) throws Exception;

	public void delete(Integer id) throws Exception;
}
