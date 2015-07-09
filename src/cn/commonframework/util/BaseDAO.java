/**
 * @(#)BaseDAO.java 2009-9-14 ����03:26:46
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * DAO���࣬ʵ�ֹ���DAO�ӿڣ��ṩ���������ݿ���������÷�����ƣ���ǿ����ĸ����ԡ�
 * 
 * @author :Bobby_Guo <br>
 * @version :1.0 <br>
 * @date :2009-9-14 ����03:26:46 <br>
 */
public class BaseDAO<T> extends HibernateDaoSupport implements IBaseDAO<T> {

	/**
	 * ����һ��ʵ����
	 */
	private Class<T> entityClass;

	/**
	 * �޲ι��췽��
	 */
	public BaseDAO() {

	}

	/**
	 * ���췽�� ��ʼ��ʵ����
	 * 
	 * @param entityClass
	 */
	public BaseDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * ɾ��һ��ʵ�����
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);

	}

	/**
	 * ������������ʵ�����
	 */
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().load(entityClass, id);
	}

	/**
	 * �������м�¼��
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * ����ʵ�����
	 */
	public void save(T t) {

		this.getHibernateTemplate().save(t);

	}

	/**
	 * ����ʵ�����
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);

	}

	/**
	 * ��������
	 */
	public int batchUpdate(String hql, Object... o) {

		return this.getHibernateTemplate().bulkUpdate(hql, o);
	}

	/**
	 * ����ɾ��
	 */
	public void batchDelete(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
		/*
		 * if(list.size()>0){ for(T t : list){ this.delete(t); } }
		 */
	}

	/**
	 * ��������
	 */
	public void batchUpdate(List<T> list) {
		if (list.size() > 0) {
			for (T t : list) {
				this.update(t);
			}
		}

	}

	/**
	 * QBC��ѯ
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByCriteria(Criterion... criterion) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		for (Criterion c : criterion) {
			criteria.add(c);
		}

		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * QBE��ѯ enableLike��ʾ�Ƿ�Ϊģ����ѯ propertiesΪģ�������Ҫ��ȥ����������
	 */
	public List<T> getAllByExample(T t, boolean enableLike,
			String... properties) {
		Example example = null;
		if (enableLike) {
			example = Example.create(t).ignoreCase().enableLike(
					MatchMode.ANYWHERE).excludeZeroes();
		} else {
			example = Example.create(t);
		}
		for (String s : properties) {
			example.excludeProperty(s);
		}
		return this.getAllByCriteria(example);
	}

	/**
	 * HQL��ѯ
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByHql(String hql) {

		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * QBE��ѯ ��ȷ��ѯ ģ�����ֻ�Ի�����������������Ч������������������Ч��
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByExample(T t) {
		return this.getHibernateTemplate().findByExample(t);
	}

	/**
	 * ��ҳ��ѯ��Ŀǰ���displaytagʹ��
	 * 
	 * @param t
	 *            ��ѯ����
	 * @param start
	 *            ��ʼ��¼��
	 * @param maxResults
	 *            һ�β�ѯ��¼��
	 * @param sortName
	 *            �����ֶ�
	 * @param order
	 *            �������"1"Ϊ����"2"Ϊ����
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllWithPage(T t, int start, int maxResults,
			String sortName, String order) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Example.create(t));
		if (null != order && "1".equals(order)) {
			criteria.addOrder(Order.asc(sortName));
		}
		if (null != order && "2".equals(order)) {
			criteria.addOrder(Order.desc(sortName));
		}

		return this.getHibernateTemplate().findByCriteria(criteria, start,
				maxResults);
	}

	/**
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * 
	 * @param t
	 *            ��ѯ����
	 * @param pageParams
	 *            ��ҳ��ѯ��ز���
	 * @param defaultOrders
	 *            Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	@SuppressWarnings("unchecked")
	public Pagenation getAllWithPage(T t, PageParams pageParams,
			Order... defaultOrders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Example.create(t));
		// int totalCount =
		// this.getHibernateTemplate().findByCriteria(criteria).size();
		int totalCount = this.count(t);
		for (Order order : defaultOrders) {
			criteria.addOrder(order);
		}
		if (null != pageParams.getOrder() && "1".equals(pageParams.getOrder())) {
			criteria.addOrder(Order.asc(pageParams.getSortName()));
		}
		if (null != pageParams.getOrder() && "2".equals(pageParams.getOrder())) {
			criteria.addOrder(Order.desc(pageParams.getSortName()));
		}

		List<T> list = this.getHibernateTemplate().findByCriteria(criteria,
				pageParams.getStart(), pageParams.getPageSize());

		return new Pagenation(list, totalCount);
	}

	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * 
	 * @param t
	 *            ��ѯ����
	 * @param pageParams
	 *            ��ҳ��ѯ��ز���
	 * @param defaultOrders
	 *            Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	@SuppressWarnings("unchecked")
	public Pagenation fuzzyQuery(T t, PageParams pageParams,
			Order... defaultOrders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Example.create(t).ignoreCase().enableLike(
				MatchMode.ANYWHERE).excludeZeroes());
		// int totalCount =
		// this.getHibernateTemplate().findByCriteria(criteria).size();
		int totalCount = this.fuzzyCount(t);
		for (Order order : defaultOrders) {
			criteria.addOrder(order);
		}
		if (null != pageParams.getOrder() && "1".equals(pageParams.getOrder())) {
			criteria.addOrder(Order.asc(pageParams.getSortName()));
		}
		if (null != pageParams.getOrder() && "2".equals(pageParams.getOrder())) {
			criteria.addOrder(Order.desc(pageParams.getSortName()));
		}

		List<T> list = this.getHibernateTemplate().findByCriteria(criteria,
				pageParams.getStart(), pageParams.getPageSize());

		return new Pagenation(list, totalCount);
	}

	/**
	 * ���Ҽ�¼��������֧��������ѯ ��ȷ��ѯ��
	 */
	public int count(T t) {

		StringBuffer hqlBuf = new StringBuffer("select count(*) from ")
				.append(t.getClass().getSimpleName());
		try {
			boolean whereFlag = true;
			boolean andFlag = false;
			Field[] fields = t.getClass().getDeclaredFields();
			// System.out.println(fields.length);
			for (Field f : fields) {

				String name = f.getName();
				if (!name.equals("serialVersionUID")) {

					String type = PropertyUtils.getPropertyType(t, name)
							.getSimpleName();

					if (!type.endsWith("Set") && !type.endsWith("Map")
							&& !type.endsWith("List")) {
						Object value = PropertyUtils.getSimpleProperty(t, name);
						if (value != null && !"".equals(value)) {
							if (andFlag) {
								hqlBuf.append(" and ");
							}
							if (whereFlag) {
								hqlBuf.append("  where ");
							}
							whereFlag = false;
							if (value instanceof String) {
								hqlBuf.append(name + "='" + value + "' ");
							} else {
								hqlBuf.append(name + "=" + value + " ");
							}
							andFlag = true;
						}
					}

					// System.out.println(f.getName()+"/"+PropertyUtils.getPropertyType(t,name)+"/"+PropertyUtils.getSimpleProperty(t,name));
				}
			}

			// System.out.println("hql:  "+hqlBuf.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(((Long) this.getHibernateTemplate().find(
				hqlBuf.toString()).get(0)).toString());
	}

	/**
	 * ���Ҽ�¼��������֧��������ѯ ģ����ѯ�� ע��ҵ����������(���Զ�������)�������δ����
	 * 
	 */
	public int fuzzyCount(T t) {

		StringBuffer hqlBuf = new StringBuffer("select count(*) from ")
				.append(t.getClass().getSimpleName());
		try {
			boolean whereFlag = true;
			boolean andFlag = false;
			Field[] fields = t.getClass().getDeclaredFields();
			// System.out.println(fields.length);
			for (Field f : fields) {

				String name = f.getName();
				if (!name.equals("serialVersionUID")) {

					String type = PropertyUtils.getPropertyType(t, name)
							.getSimpleName();

					if (!type.endsWith("Set") && !type.endsWith("Map")
							&& !type.endsWith("List")) {
						Object value = PropertyUtils.getSimpleProperty(t, name);
						if (value != null && !"".equals(value)) {
							if (andFlag) {
								hqlBuf.append(" and ");
							}
							if (whereFlag) {
								hqlBuf.append("  where ");
							}
							whereFlag = false;
							if (value instanceof String) {
								hqlBuf
										.append(name + " like '%" + value
												+ "%' ");
							} else {
								hqlBuf.append(name + "=" + value + " ");
							}
							andFlag = true;
						}
					}

					// System.out.println(f.getName()+"/"+PropertyUtils.getPropertyType(t,name)+"/"+PropertyUtils.getSimpleProperty(t,name));
				}
			}

			// System.out.println("hql:  "+hqlBuf.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(((Long) this.getHibernateTemplate().find(
				hqlBuf.toString()).get(0)).toString());
	}

	/**
	 * ���Ҽ�¼��������֧��������ѯ ģ����ѯ�� ֧��ҵ����������(���Զ�������)��Ϊ��ѯ����������� ע����֧�ּ���������Ϊ��ѯ�����������
	 * 
	 * @param t
	 * @return
	 */
	public int fuzzyCountWithUserType(T t) {

		StringBuffer hqlBuf = new StringBuffer("select count(*) from ").append(
				t.getClass().getSimpleName()).append(" t ");
		try {
			boolean whereFlag = true;
			boolean andFlag = false;
			Field[] fields = t.getClass().getDeclaredFields();
			System.out.println(fields.length);
			for (Field f : fields) {

				String name = f.getName();
				if (!name.equals("serialVersionUID")) {

					String type = PropertyUtils.getPropertyType(t, name)
							.getName();// �������ȫ��
					// ����ֶ������� Integer Long String Short Character Double Number
					// Float

					if (type.endsWith("Integer") || type.endsWith("Long")
							|| type.endsWith("String")
							|| type.endsWith("Short")
							|| type.endsWith("Character")
							|| type.endsWith("Double")
							|| type.endsWith("Number")
							|| type.endsWith("Float")) {
						Object value = PropertyUtils.getSimpleProperty(t, name);
						if (value != null && !"".equals(value)) {
							if (andFlag) {
								hqlBuf.append(" and ");
							}
							if (whereFlag) {
								hqlBuf.append("  where ");
							}
							whereFlag = false;
							if (value instanceof String) {
								hqlBuf.append("t." + name + " like '%" + value
										+ "%' ");
							} else {
								hqlBuf.append("t." + name + "=" + value + " ");
							}
							andFlag = true;
						}
					}

					// ����ֶ��������Զ������Ͳ��Ҳ��Ǽ���

					else if (!type.endsWith("Set") && !type.endsWith("Map")
							&& !type.endsWith("List")) {

						System.out.println(type);
						Field[] targetFields = Class.forName(type)
								.getDeclaredFields();
						System.out.println(targetFields.length);
						for (Field targetField : targetFields) {
							String targetName = targetField.getName();
							System.out.println(targetName);
							if (!targetName.equals("serialVersionUID")) {
								String targetType = PropertyUtils
										.getPropertyType(
												Class.forName(type)
														.newInstance(),
												targetName).getName();// �������ȫ��

								try {
									if (targetType.endsWith("Integer")
											|| targetType.endsWith("Long")
											|| targetType.endsWith("String")
											|| targetType.endsWith("Short")
											|| targetType.endsWith("Character")
											|| targetType.endsWith("Double")
											|| targetType.endsWith("Number")
											|| targetType.endsWith("Float")) {
										Object targetValue;

										targetValue = PropertyUtils
												.getNestedProperty(t, name
														+ "." + targetName);

										if (targetValue != null
												&& !"".equals(targetValue)) {
											if (andFlag) {
												hqlBuf.append(" and ");
											}
											if (whereFlag) {
												hqlBuf.append("  where ");
											}
											whereFlag = false;
											if (targetValue instanceof String) {
												hqlBuf
														.append("t." + name)
														.append(".")
														.append(
																targetName
																		+ " like '%"
																		+ targetValue
																		+ "%' ");
											} else {
												hqlBuf
														.append("t." + name)
														.append(".")
														.append(
																targetName
																		+ "="
																		+ targetValue
																		+ " ");
											}
											andFlag = true;
										}
									}
								} catch (NestedNullException e) {

								}

							}
						}

						/*
						 * Object value =
						 * PropertyUtils.getSimpleProperty(t,name);
						 * if(value!=null&&!"".equals(value)){ if(andFlag){
						 * hqlBuf.append(" and "); } if(whereFlag){
						 * hqlBuf.append("  where "); } whereFlag = false;
						 * if(value instanceof String){
						 * hqlBuf.append(name+" like '%"+value+"%' "); }else{
						 * hqlBuf.append(name+"="+value+" "); } andFlag = true;
						 * }
						 */
					}

					// System.out.println(f.getName()+"/"+PropertyUtils.getPropertyType(t,name)+"/"+PropertyUtils.getSimpleProperty(t,name));
				}
			}

			System.out.println("hql:  " + hqlBuf.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(((Long) this.getHibernateTemplate().find(
				hqlBuf.toString()).get(0)).toString());
	}

	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ�� ֧�ֲ�ѯ�����к����Զ������͵������
	 * ��֧�ּ���������Ϊ��ѯ���������������������������ػ�������Ӧ������
	 * 
	 * @param t
	 *            ��ѯ����
	 * @param pageParams
	 *            ��ҳ��ѯ��ز���
	 * @param defaultOrders
	 *            Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	@SuppressWarnings("unchecked")
	public Pagenation fuzzyQueryWithUserType(T t, PageParams pageParams,
			Order... defaultOrders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Example.create(t).ignoreCase().enableLike(
				MatchMode.ANYWHERE).excludeZeroes());

		try {
			// �ҳ�����t�����������ֶΡ�
			Field[] fields = t.getClass().getDeclaredFields();

			for (Field f : fields) {
				// �õ��ֶε����ơ�
				String name = f.getName();
				if (!name.equals("serialVersionUID")) {
					// �õ��ֶε�����ȫ�ơ�
					String type = PropertyUtils.getPropertyType(t, name)
							.getName();
					// ������Զ������� ��Ҫ���Զ������͵İ�����������java��ͷ���������ƣ���Ŀǰ��û��������
					// ��������
					if (!type.startsWith("java")) {
						// Object value =
						// PropertyUtils.getSimpleProperty(t,name);
						System.out.println(type);
						System.out.println(t.getClass().getMethod(
								"get" + name.substring(0, 1).toUpperCase()
										+ name.substring(1, name.length()))
								.getName());
						System.out.println(t.getClass().getMethod(
								"get" + name.substring(0, 1).toUpperCase()
										+ name.substring(1, name.length()))
								.invoke(t));
						// �����Զ��������ֶ�ģ�壬����Java������ƣ�ʹ��getXxx()�����õ������
						if (null != t.getClass().getMethod(
								"get" + name.substring(0, 1).toUpperCase()
										+ name.substring(1, name.length()))
								.invoke(t)) {
							criteria.createCriteria(name).add(
									Example.create(t.getClass().getMethod(
											"get"
													+ name.substring(0, 1)
															.toUpperCase()
													+ name.substring(1, name
															.length())).invoke(
											t)));
						}
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		// int totalCount =
		// this.getHibernateTemplate().findByCriteria(criteria).size();
		int totalCount = this.fuzzyCountWithUserType(t);
		for (Order order : defaultOrders) {
			criteria.addOrder(order);
		}
		if (null != pageParams.getOrder() && "1".equals(pageParams.getOrder())) {
			criteria.addOrder(Order.asc(pageParams.getSortName()));
		}
		if (null != pageParams.getOrder() && "2".equals(pageParams.getOrder())) {
			criteria.addOrder(Order.desc(pageParams.getSortName()));
		}

		List<T> list = this.getHibernateTemplate().findByCriteria(criteria,
				pageParams.getStart(), pageParams.getPageSize());

		return new Pagenation(list, totalCount);
	}
}