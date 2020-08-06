package bi.lunch.repository;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bi.lunch.dao.IDAO;
import bi.lunch.entity.User;

@Repository
public class UserRepository implements IDAO<User> {
	private static final Logger log = Logger.getLogger(UserRepository.class.getName());

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
	public List<User> getAll() {
		Query query = getSession().createNamedQuery("User.findAll");
		return (List<User>) query.getResultList();
	}

	@Override
	public User getById(int id) {
		return getSession().get(User.class, id);
	}

	@Override
	@Transactional
	public boolean insert(User t) {
		try {
			getSession().persist(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(User t) {
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
	public boolean update(User t) {
		try {
			getSession().merge(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
