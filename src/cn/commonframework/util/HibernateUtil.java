/**
 * @(#)HibernateUtil.java Jul 28, 2009 10:37:45 PM
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import cn.commonframework.debug.Debug;

/**
 * @description:Hibernate工具类，提供Session的获取、关闭，及事务的提交及回滚。
 * @author :Bobby <br>
 * @version :1.0 <br>
 * @date :Jul 28, 2009 10:37:45 PM <br>
 */
public class HibernateUtil {
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;

	static {
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Exception building SessionFactory: "
					+ ex.getMessage(), ex);
		}
	}

	/**
	 * 得到一个Session。
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		Session s = sessionFactory.openSession();
		return s;
	}

	/**
	 * 从当前线程中获取Session，如果没有则为其设置一个新的Session。
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			Debug.println("Opening new session for this threadLocal.");
			session = currentSession();
			threadLocal.set(session);
		}

		return session;
	}

	/**
	 * 重新创建Session工厂。
	 */
	public static void rebuildSessionFactory() {
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Exception building SessionFactory: "
					+ ex.getMessage(), ex);
		}
	}

	/**
	 * 关闭当前前程的Session。
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	/**
	 * 关闭指定的一个Session。
	 * 
	 * @param s
	 * @throws HibernateException
	 */
	public static void closeSession(Session s) throws HibernateException {
		if (s != null && s.isOpen()) {
			s.close();
		}
	}

	/**
	 * 
	 * @return sessionFactory
	 */
	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 设置配置文件
	 * 
	 * @param configFile
	 */
	public static void setConfigFile(String configFile) {
		HibernateUtil.configFile = configFile;
		sessionFactory = null;
	}

	/**
	 * 
	 * @return configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * 开始一个事务
	 */
	public static void beginTrans() {
		try {
			getSession().beginTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to open a transaction..."
					+ e.getMessage(), e);
		}
	}

	/**
	 * 提交事务
	 */
	public static void commitTrans() {
		try {
			getSession().getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to commit a transaction..."
					+ e.getMessage(), e);
		}
	}

	/**
	 * 回滚事务
	 */
	public static void rollbackTrans() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to rollback a transaction..."
					+ e.getMessage(), e);
		}
	}
}
