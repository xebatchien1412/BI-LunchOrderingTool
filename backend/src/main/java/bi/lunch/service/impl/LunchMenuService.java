package bi.lunch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bi.lunch.dao.IDAO;
import bi.lunch.entity.LunchMenu;
import bi.lunch.service.ILunchMenuService;

@Service
public class LunchMenuService implements ILunchMenuService {

	@Autowired
	IDAO<LunchMenu> dao;

	@Override
	public boolean insert(LunchMenu t) {
		return dao.insert(t);
	}

	@Override
	public boolean delete(LunchMenu t) {
		return dao.delete(t);
	}

	@Override
	public boolean update(LunchMenu t) {
		return dao.update(t);
	}

	@Override
	public LunchMenu getUserById(int id) {
		return dao.getById(id);
	}

	@Override
	public List<LunchMenu> getAll() {
		return dao.getAll();
	}

}
