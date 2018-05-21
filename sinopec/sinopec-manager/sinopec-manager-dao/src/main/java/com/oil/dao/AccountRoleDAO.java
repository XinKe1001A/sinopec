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

import com.oil.entity.AccountRole;

/**
 * A data access object (DAO) providing persistence and search support for
 * AccountRole entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oil.entity.AccountRole
 * @author MyEclipse Persistence Tools
 */
@Repository
@Transactional
public class AccountRoleDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AccountRoleDAO.class);
	// property constants

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

	public void save(AccountRole transientInstance) {
		log.debug("saving AccountRole instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AccountRole persistentInstance) {
		log.debug("deleting AccountRole instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AccountRole findById(java.lang.Long id) {
		log.debug("getting AccountRole instance with id: " + id);
		try {
			AccountRole instance = (AccountRole) getCurrentSession().get(
					"com.oil.entity.AccountRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AccountRole> findByExample(AccountRole instance) {
		log.debug("finding AccountRole instance by example");
		try {
			List<AccountRole> results = (List<AccountRole>) getCurrentSession()
					.createCriteria("com.oil.entity.AccountRole")
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
		log.debug("finding AccountRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AccountRole as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all AccountRole instances");
		try {
			String queryString = "from AccountRole";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AccountRole merge(AccountRole detachedInstance) {
		log.debug("merging AccountRole instance");
		try {
			AccountRole result = (AccountRole) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AccountRole instance) {
		log.debug("attaching dirty AccountRole instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AccountRole instance) {
		log.debug("attaching clean AccountRole instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AccountRoleDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AccountRoleDAO) ctx.getBean("AccountRoleDAO");
	}
}