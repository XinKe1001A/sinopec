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

import com.oil.entity.Attributes;

/**
 * A data access object (DAO) providing persistence and search support for
 * Attributes entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oil.entity.Attributes
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("AttributesDAO")
public class AttributesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AttributesDAO.class);
	// property constants
	public static final String ATTRIBUTE_NAME = "attributeName";
	public static final String PRIORITY = "priority";
	public static final String NOTE = "note";

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

	public void save(Attributes transientInstance) {
		log.debug("saving Attributes instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Attributes persistentInstance) {
		log.debug("deleting Attributes instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Attributes findById(java.lang.Long id) {
		log.debug("getting Attributes instance with id: " + id);
		try {
			Attributes instance = (Attributes) getCurrentSession().get(
					"com.oil.entity.Attributes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Attributes> findByExample(Attributes instance) {
		log.debug("finding Attributes instance by example");
		try {
			List<Attributes> results = (List<Attributes>) getCurrentSession()
					.createCriteria("com.oil.entity.Attributes")
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
		log.debug("finding Attributes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Attributes as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Attributes> findByAttributeName(Object attributeName) {
		return findByProperty(ATTRIBUTE_NAME, attributeName);
	}

	public List<Attributes> findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	public List<Attributes> findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findAll() {
		log.debug("finding all Attributes instances");
		try {
			String queryString = "from Attributes";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Attributes merge(Attributes detachedInstance) {
		log.debug("merging Attributes instance");
		try {
			Attributes result = (Attributes) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attributes instance) {
		log.debug("attaching dirty Attributes instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attributes instance) {
		log.debug("attaching clean Attributes instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AttributesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AttributesDAO) ctx.getBean("AttributesDAO");
	}
}