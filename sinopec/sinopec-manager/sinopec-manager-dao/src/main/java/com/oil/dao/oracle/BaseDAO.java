package com.oil.dao.oracle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository("baseDao")
public class BaseDAO<T>{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}


	public Serializable save(Object o) {
		return this.getCurrentSession().save(o);
	}


	public void delete(Object o) {
		this.getCurrentSession().delete(o);		
	}


	public void update(Object o) {
		this.getCurrentSession().update(o);
	}

	
	public void saveOrUpdate(Object o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(param != null && param.length > 0){
			for(int i=0; i<param.length; i++){
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(param != null && param.size() > 0){
			for(int i=0; i<param.size(); i++){
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object[] param, Integer rows, Integer page) {
		// TODO Auto-generated method stub
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, List<Object> param, Integer rows, Integer page) {
		// TODO Auto-generated method stub
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	

	@SuppressWarnings("unchecked")
	public T get(Class<T> c, Serializable id) {
		// TODO Auto-generated method stub
		return (T) this.getCurrentSession().get(c, id);
	}


	public T get(String hql, Object[] param) {
		// TODO Auto-generated method stub
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}


	public T get(String hql, List<Object> param) {
		// TODO Auto-generated method stub
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}


	public Long count(String hql) {
		return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
	}


	public Long count(String hql, Object[] param) {
		// TODO Auto-generated method stub
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}


	public Long count(String hql, List<Object> param) {
		// TODO Auto-generated method stub
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}


	public Integer executeHql(String hql) {
		// TODO Auto-generated method stub
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}


	public Integer executeHql(String hql, Object[] param) {
		// TODO Auto-generated method stub
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}


	public Integer executeHql(String hql, List<Object> param) {
		// TODO Auto-generated method stub
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> executeSql(String sql) {
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(Criteria dc) {
		// TODO Auto-generated method stub

		return dc.list();
	}


	public Criteria createDetachedCriteria(Class<T> clazz) {
		// TODO Auto-generated method stub
		Criteria dc = getCurrentSession().createCriteria(clazz);
		return dc;
	}

	@SuppressWarnings("unchecked")
	public List<T> findBySQL(String sql, Class<T> cls, List<Object> param) {

		Query q = this.getCurrentSession().createSQLQuery(sql).addEntity(cls);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setLong(i, (Long) param.get(i));
			}
		}
		return q.list();

	}


}
