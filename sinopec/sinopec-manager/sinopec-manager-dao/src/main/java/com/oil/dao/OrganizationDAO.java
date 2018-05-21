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

import com.oil.entity.Organization;

/**
 * A data access object (DAO) providing persistence and search support for
 * Organization entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oil.entity.Organization
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("OrganizationDAO")
public class OrganizationDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OrganizationDAO.class);
	// property constants
	public static final String CODE3265 = "code3265";
	public static final String NAME = "name";
	public static final String NAME_SIMPLE = "nameSimple";
	public static final String PARENT = "parent";
	public static final String PARENT_ID = "parentId";
	public static final String ADDRESS = "address";
	public static final String CONTACTS = "contacts";
	public static final String PHONE = "phone";
	public static final String PROPERTY = "property";
	public static final String STATE = "state";
	public static final String CONTEXT = "context";
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

	public void save(Organization transientInstance) {
		log.debug("saving Organization instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Organization persistentInstance) {
		log.debug("deleting Organization instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Organization findById(java.lang.Long id) {
		log.debug("getting Organization instance with id: " + id);
		try {
			Organization instance = (Organization) getCurrentSession().get(
					"com.oil.entity.Organization", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Organization> findByExample(Organization instance) {
		log.debug("finding Organization instance by example");
		try {
			List<Organization> results = (List<Organization>) getCurrentSession()
					.createCriteria("com.oil.entity.Organization")
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
		log.debug("finding Organization instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Organization as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Organization> findByCode3265(Object code3265) {
		return findByProperty(CODE3265, code3265);
	}

	public List<Organization> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Organization> findByNameSimple(Object nameSimple) {
		return findByProperty(NAME_SIMPLE, nameSimple);
	}

	public List<Organization> findByParent(Object parent) {
		return findByProperty(PARENT, parent);
	}

	public List<Organization> findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	public List<Organization> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Organization> findByContacts(Object contacts) {
		return findByProperty(CONTACTS, contacts);
	}

	public List<Organization> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Organization> findByProperty(Object property) {
		return findByProperty(PROPERTY, property);
	}

	public List<Organization> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Organization> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findAll() {
		log.debug("finding all Organization instances");
		try {
			String queryString = "from Organization";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Organization merge(Organization detachedInstance) {
		log.debug("merging Organization instance");
		try {
			Organization result = (Organization) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Organization instance) {
		log.debug("attaching dirty Organization instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Organization instance) {
		log.debug("attaching clean Organization instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrganizationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OrganizationDAO) ctx.getBean("OrganizationDAO");
	}
}