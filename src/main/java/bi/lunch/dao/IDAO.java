package bi.lunch.dao;

import java.util.List;

public interface IDAO<T> {

	public List<T> getAll();

	public T getById(int id);

	public boolean insert(T t);

	public boolean delete(T t);

	public boolean update(T t);
}
