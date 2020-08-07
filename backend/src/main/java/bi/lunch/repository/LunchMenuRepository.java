package bi.lunch.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bi.lunch.dao.IDAO;
import bi.lunch.entity.LunchMenu;

@Repository
public class LunchMenuRepository implements IDAO<LunchMenu> {

	@Autowired
	private SessionFactory session;

	private Session getSession() {
		try {
			return session.getCurrentSession();
		} catch (Exception e) {
			return session.openSession();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<LunchMenu> getAll() {
		Query query = getSession().createNamedQuery("LunchMenu.findAll");
		return (List<LunchMenu>) query.getResultList();
	}

	@Override
	public LunchMenu getById(int id) {
		return getSession().get(LunchMenu.class, id);
	}

	@Override
	@Transactional
	public boolean insert(LunchMenu t) {
		try {
			getSession().save(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(LunchMenu t) {
		try {
			getSession().delete(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(LunchMenu t) {
		try {
			getSession().merge(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
