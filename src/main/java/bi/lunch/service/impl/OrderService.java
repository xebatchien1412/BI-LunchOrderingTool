package bi.lunch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bi.lunch.dao.IDAO;
import bi.lunch.entity.Order;
import bi.lunch.service.IOrderService;

@Service	
public class OrderService implements IOrderService {
	@Autowired
	IDAO<Order> dao;

	@Override
	public boolean insert(Order t) {
		return dao.insert(t);
	}

	@Override
	public boolean delete(Order t) {
		return dao.delete(t);
	}

	@Override
	public boolean update(Order t) {
		return dao.update(t);
	}

	@Override
	public Order getUserById(int id) {
		return dao.getById(id);
	}

	@Override
	public List<Order> getAll() {
		return dao.getAll();
	}
}
