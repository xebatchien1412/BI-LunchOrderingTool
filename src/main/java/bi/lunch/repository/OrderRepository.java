package bi.lunch.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bi.lunch.dao.IDAO;
import bi.lunch.entity.Order;

@Repository
public class OrderRepository implements IDAO<Order> {
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
	public List<Order> getAll() {
		Query query = getSession().createNamedQuery("Order.findAll");
		return (List<Order>) query.getResultList();
	}

	@Override
	public Order getById(int id) {
		return getSession().get(Order.class, id);
	}

	@Override
	@Transactional
	public boolean insert(Order t) {
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
	public boolean delete(Order t) {
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
	public boolean update(Order t) {
		try {
			getSession().merge(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
