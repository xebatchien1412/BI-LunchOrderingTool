package bi.lunch.service;

import java.util.List;

import bi.lunch.entity.User;

public interface IUserService {
	public boolean insert(User user);

	public boolean delete(User user);

	public boolean update(User user);

	public User getUserById(int id);

	public List<User> getAll();
}
