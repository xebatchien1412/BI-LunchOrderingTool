package bi.lunch.service;

import java.util.List;

import bi.lunch.entity.Order;

public interface IOrderService {
	public boolean insert(Order t);

	public boolean delete(Order t);

	public boolean update(Order t);

	public Order getUserById(int id);

	public List<Order> getAll();
}
