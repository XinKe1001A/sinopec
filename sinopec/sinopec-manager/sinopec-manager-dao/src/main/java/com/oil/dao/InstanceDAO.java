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

import com.oil.entity.Instance;

/**
 * A data access object (DAO) providing persistence and search support for
 * Instance entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oil.entity.Instance
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository
public class InstanceDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InstanceDAO.class);
	// property constants
	public static final String ORGANIZATION_ID = "organizationId";
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

	public void save(Instance transientInstance) {
		log.debug("saving Instance instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Instance persistentInstance) {
		log.debug("deleting Instance instance");
		try {
			getCurrentSession().delete(persistentInstance);
			getCurrentSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Instance findById(java.lang.Long id) {
		log.debug("getting Instance instance with id: " + id);
		try {
			Instance instance = (Instance) getCurrentSession().get(
					"com.oil.entity.Instance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Instance> findByExample(Instance instance) {
		log.debug("finding Instance instance by example");
		try {
			List<Instance> results = (List<Instance>) getCurrentSession()
					.createCriteria("com.oil.entity.Instance")
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
		log.debug("finding Instance instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Instance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Instance> findByOrganizationId(Object organizationId) {
		return findByProperty(ORGANIZATION_ID, organizationId);
	}

	public List findAll() {
		log.debug("finding all Instance instances");
		try {
			String queryString = "from Instance";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Instance merge(Instance detachedInstance) {
		log.debug("merging Instance instance");
		try {
			Instance result = (Instance) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Instance instance) {
		log.debug("attaching dirty Instance instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Instance instance) {
		log.debug("attaching clean Instance instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InstanceDAO getFromApplicationContext(ApplicationContext ctx) {
		return (InstanceDAO) ctx.getBean("InstanceDAO");
	}
}