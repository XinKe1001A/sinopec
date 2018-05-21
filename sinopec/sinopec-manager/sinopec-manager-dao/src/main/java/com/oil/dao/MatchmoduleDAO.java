package com.oil.dao;

import java.util.List;
import java.util.Set;

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

import com.oil.entity.Matchmodule;

/**
 * A data access object (DAO) providing persistence and search support for
 * Matchmodule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oil.entity.Matchmodule
 * @author MyEclipse Persistence Tools
 */
@Repository
@Transactional
public class MatchmoduleDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MatchmoduleDAO.class);
	// property constants
	public static final String MODULE_NAME = "moduleName";

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Matchmodule transientInstance) {
		log.debug("saving Matchmodule instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Matchmodule persistentInstance) {
		log.debug("deleting Matchmodule instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Matchmodule findById(java.lang.Long id) {
		log.debug("getting Matchmodule instance with id: " + id);
		try {
			Matchmodule instance = (Matchmodule) getCurrentSession().get(
					"com.oil.entity.Matchmodule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Matchmodule> findByExample(Matchmodule instance) {
		log.debug("finding Matchmodule instance by example");
		try {
			List<Matchmodule> results = (List<Matchmodule>) getCurrentSession()
					.createCriteria("com.oil.entity.Matchmodule")
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
		log.debug("finding Matchmodule instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Matchmodule as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Matchmodule> findByModuleName(Object moduleName) {
		return findByProperty(MODULE_NAME, moduleName);
	}

	public List findAll() {
		log.debug("finding all Matchmodule instances");
		try {
			String queryString = "from Matchmodule";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Matchmodule merge(Matchmodule detachedInstance) {
		log.debug("merging Matchmodule instance");
		try {
			Matchmodule result = (Matchmodule) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Matchmodule instance) {
		log.debug("attaching dirty Matchmodule instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Matchmodule instance) {
		log.debug("attaching clean Matchmodule instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MatchmoduleDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MatchmoduleDAO) ctx.getBean("MatchmoduleDAO");
	}
}