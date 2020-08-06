package bi.lunch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bi.lunch.dao.IDAO;
import bi.lunch.entity.User;
import bi.lunch.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	IDAO<User> dao;

	@Override
	public boolean insert(User user) {
		return dao.insert(user);
	}

	@Override
	public boolean delete(User user) {
		return dao.delete(user);
	}

	@Override
	public boolean update(User user) {
		return dao.update(user);
	}

	@Override
	public User getUserById(int id) {
		return dao.getById(id);
	}

	@Override
	public List<User> getAll() {
		return dao.getAll();
	}

}
