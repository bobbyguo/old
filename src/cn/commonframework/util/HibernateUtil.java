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
 * @description:Hibernate�����࣬�ṩSession�Ļ�ȡ���رգ���������ύ���ع���
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
	 * �õ�һ��Session��
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		Session s = sessionFactory.openSession();
		return s;
	}

	/**
	 * �ӵ�ǰ�߳��л�ȡSession�����û����Ϊ������һ���µ�Session��
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
	 * ���´���Session������
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
	 * �رյ�ǰǰ�̵�Session��
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
	 * �ر�ָ����һ��Session��
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
	 * ���������ļ�
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
	 * ��ʼһ������
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
	 * �ύ����
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
	 * �ع�����
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
