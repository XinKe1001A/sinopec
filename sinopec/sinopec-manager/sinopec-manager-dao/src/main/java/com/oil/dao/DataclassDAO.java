package com.oil.dao;

import java.sql.Timestamp;
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

import com.oil.entity.Dataclass;

/**
 * A data access object (DAO) providing persistence and search support for
 * Dataclass entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oil.entity.Dataclass
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("DataclassDAO")
public class DataclassDAO {
	private static final Logger log = LoggerFactory
			.getLogger(DataclassDAO.class);
	// property constants
	public static final String CLASS_NAME = "className";
	public static final String LEVEL = "level";
	public static final String PROIRITY = "proirity";
	public static final String CREATION_ACCOUNT_ID = "creationAccountId";
	public static final String RESPONSIBILITY_DEPARTMENT = "responsibilityDepartment";
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

	public void save(Dataclass transientInstance) {
		log.debug("saving Dataclass instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Dataclass persistentInstance) {
		log.debug("deleting Dataclass instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dataclass findById(java.lang.Long id) {
		log.debug("getting Dataclass instance with id: " + id);
		try {
			Dataclass instance = (Dataclass) getCurrentSession().get(
					"com.oil.entity.Dataclass", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Dataclass> findByExample(Dataclass instance) {
		log.debug("finding Dataclass instance by example");
		try {
			List<Dataclass> results = (List<Dataclass>) getCurrentSession()
					.createCriteria("com.oil.entity.Dataclass")
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
		log.debug("finding Dataclass instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Dataclass as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Dataclass> findByLevelOrderByPriority(Integer level) {
		log.debug("finding Dataclass instance with property: " + LEVEL
				+ ", value: " + level);
		
		try {
			String queryString = "from Dataclass as model where model."
					+ LEVEL + "= ? order by " + PROIRITY;
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, level);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Dataclass> findByClassName(Object className) {
		return findByProperty(CLASS_NAME, className);
	}

	public List<Dataclass> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List<Dataclass> findByProirity(Object proirity) {
		return findByProperty(PROIRITY, proirity);
	}

	public List<Dataclass> findByCreationAccountId(Object creationAccountId) {
		return findByProperty(CREATION_ACCOUNT_ID, creationAccountId);
	}

	public List<Dataclass> findByResponsibilityDepartment(
			Object responsibilityDepartment) {
		return findByProperty(RESPONSIBILITY_DEPARTMENT,
				responsibilityDepartment);
	}

	public List<Dataclass> findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findAll() {
		log.debug("finding all Dataclass instances");
		try {
			String queryString = "from Dataclass";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Dataclass merge(Dataclass detachedInstance) {
		log.debug("merging Dataclass instance");
		try {
			Dataclass result = (Dataclass) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Dataclass instance) {
		log.debug("attaching dirty Dataclass instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dataclass instance) {
		log.debug("attaching clean Dataclass instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DataclassDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DataclassDAO) ctx.getBean("DataclassDAO");
	}
}