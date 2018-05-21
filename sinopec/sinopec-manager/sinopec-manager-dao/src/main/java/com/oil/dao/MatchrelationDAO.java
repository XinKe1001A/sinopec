package com.oil.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oil.entity.Matchrelation;

/**
 * A data access object (DAO) providing persistence and search support for
 * Matchrelation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oil.entity.Matchrelation
 * @author MyEclipse Persistence Tools
 */
@Repository
@Transactional
public class MatchrelationDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MatchrelationDAO.class);
	// property constants
	public static final String COL_NAME = "colName";

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Matchrelation transientInstance) {
		log.debug("saving Matchrelation instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Matchrelation persistentInstance) {
		log.debug("deleting Matchrelation instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Matchrelation findById(java.lang.Integer id) {
		log.debug("getting Matchrelation instance with id: " + id);
		try {
			Matchrelation instance = (Matchrelation) getCurrentSession().get(
					"com.oil.entity.Matchrelation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Matchrelation> findByExample(Matchrelation instance) {
		log.debug("finding Matchrelation instance by example");
		try {
			List<Matchrelation> results = (List<Matchrelation>) getCurrentSession()
					.createCriteria("com.oil.entity.Matchrelation")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Matchrelation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Matchrelation as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Matchrelation> findByColName(Object colName) {
		return findByProperty(COL_NAME, colName);
	}

	public List findAll() {
		log.debug("finding all Matchrelation instances");
		try {
			String queryString = "from Matchrelation";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Matchrelation merge(Matchrelation detachedInstance) {
		log.debug("merging Matchrelation instance");
		try {
			Matchrelation result = (Matchrelation) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Matchrelation instance) {
		log.debug("attaching dirty Matchrelation instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Matchrelation instance) {
		log.debug("attaching clean Matchrelation instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MatchrelationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MatchrelationDAO) ctx.getBean("MatchrelationDAO");
	}
}