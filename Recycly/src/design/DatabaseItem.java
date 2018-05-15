package design;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.google.gson.GsonBuilder;
import utility.DBConnection;
import utility.HibernateProxyTypeAdapter;

public class DatabaseItem {
	public void delete() {
		Session session = DBConnection.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(this);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}
	public void save() {
		Session session = DBConnection.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(this);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}
	public void update() {
		Session session = DBConnection.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(this);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}
	public Object getModel(int id) {
		Session session = DBConnection.getSessionFactory().openSession();
		session.createCriteria(this.getClass());
		Object obj = session.get(this.getClass(), id);
		session.close();
		return obj;
	}
	public List<Object> getList() {
		Session session = DBConnection.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(this.getClass());
		List<Object> list = criteria.list();
		session.close();
		return list;
	}
	public List<Object> getList(String keyword, String value) {
		Session session = DBConnection.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(this.getClass());
		criteria.add(Restrictions.ilike(keyword, value + "%"));
		List<Object> list = criteria.list();
		session.close();
		return list;
	}
	public Object find(String keyword, String value) {
		Session session = DBConnection.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(this.getClass());
		criteria.add(Restrictions.ilike(keyword, value + "%"));
		List<Object> list = criteria.list();
		session.close();
		if(list.isEmpty()||list.size()==0)
			return null;
		return list.get(0);
	}
	public List<Object> findList(String keyword, String value) {
		Session session = DBConnection.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(this.getClass());
		criteria.add(Restrictions.ilike(keyword, value));
		List<Object> list = criteria.list();
		session.close();
		return list;
	}

	public List<Object> findChild(String value) {
		Session session = DBConnection.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(this.getClass());
		criteria.createAlias("materials", "c");
		criteria.add(Restrictions.in("c.name",value));
		List<Object> list = criteria.list();
		session.close();
		return list;
	}
	//
	@Override
	public String toString() {
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).setPrettyPrinting().disableHtmlEscaping().create().toJson(this);
	}
	
}
