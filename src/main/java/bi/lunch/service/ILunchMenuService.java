package bi.lunch.service;

import java.util.List;

import bi.lunch.entity.LunchMenu;

public interface ILunchMenuService {
	public boolean insert(LunchMenu user);

	public boolean delete(LunchMenu user);

	public boolean update(LunchMenu user);

	public LunchMenu getUserById(int id);

	public List<LunchMenu> getAll();
}
