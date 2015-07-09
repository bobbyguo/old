/**
 * @(#)BaseDAO.java 2009-9-14 下午03:26:46
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
 * DAO基类，实现公用DAO接口，提供基本的数据库操作。采用泛型设计，增强代码的复用性。
 * 
 * @author :Bobby_Guo <br>
 * @version :1.0 <br>
 * @date :2009-9-14 下午03:26:46 <br>
 */
public class BaseDAO<T> extends HibernateDaoSupport implements IBaseDAO<T> {

	/**
	 * 声明一个实体类
	 */
	private Class<T> entityClass;

	/**
	 * 无参构造方法
	 */
	public BaseDAO() {

	}

	/**
	 * 构造方法 初始化实体类
	 * 
	 * @param entityClass
	 */
	public BaseDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 删除一个实体对象。
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);

	}

	/**
	 * 根据主键检索实体对象
	 */
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().load(entityClass, id);
	}

	/**
	 * 查找所有记录。
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * 保存实体对象
	 */
	public void save(T t) {

		this.getHibernateTemplate().save(t);

	}

	/**
	 * 更新实体对象
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);

	}

	/**
	 * 批量更新
	 */
	public int batchUpdate(String hql, Object... o) {

		return this.getHibernateTemplate().bulkUpdate(hql, o);
	}

	/**
	 * 批量删除
	 */
	public void batchDelete(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
		/*
		 * if(list.size()>0){ for(T t : list){ this.delete(t); } }
		 */
	}

	/**
	 * 批量更新
	 */
	public void batchUpdate(List<T> list) {
		if (list.size() > 0) {
			for (T t : list) {
				this.update(t);
			}
		}

	}

	/**
	 * QBC查询
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
	 * QBE查询 enableLike表示是否为模糊查询 properties为模版对象中要除去的属性名称
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
	 * HQL查询
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByHql(String hql) {

		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * QBE查询 精确查询 模板对象只对基本数据类型属性有效，对其他对象类型无效。
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByExample(T t) {
		return this.getHibernateTemplate().findByExample(t);
	}

	/**
	 * 分页查询，目前结合displaytag使用
	 * 
	 * @param t
	 *            查询对象
	 * @param start
	 *            起始记录号
	 * @param maxResults
	 *            一次查询记录数
	 * @param sortName
	 *            排序字段
	 * @param order
	 *            排序规则，"1"为升序，"2"为降序
	 * @return 分页并排序后的查询列表。
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
	 * 分页查询（精确查询），目前结合displaytag使用
	 * 
	 * @param t
	 *            查询对象
	 * @param pageParams
	 *            翻页查询相关参数
	 * @param defaultOrders
	 *            默认排序组
	 * @return 分页并排序后的查询列表。
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
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * 
	 * @param t
	 *            查询对象
	 * @param pageParams
	 *            翻页查询相关参数
	 * @param defaultOrders
	 *            默认排序组
	 * @return 分页并排序后的查询列表。
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
	 * 查找记录总条数，支持条件查询 精确查询。
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
	 * 查找记录总条数，支持条件查询 模糊查询。 注：业务数据类型(即自定义类型)的情况尚未处理。
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
	 * 查找记录总条数，支持条件查询 模糊查询。 支持业务数据类型(即自定义类型)作为查询条件的情况。 注：不支持集合类型作为查询条件的情况。
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
							.getName();// 获得类型全称
					// 如果字段类型是 Integer Long String Short Character Double Number
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

					// 如果字段类型是自定义类型并且不是集合

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
												targetName).getName();// 获得类型全称

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
	 * 分页查询（模糊查询），目前结合displaytag使用 支持查询条件中含有自定义类型的情况。
	 * 不支持集合类型作为查询条件的情况，如遇到此情况请重载或新增对应方法。
	 * 
	 * @param t
	 *            查询对象
	 * @param pageParams
	 *            翻页查询相关参数
	 * @param defaultOrders
	 *            默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	@SuppressWarnings("unchecked")
	public Pagenation fuzzyQueryWithUserType(T t, PageParams pageParams,
			Order... defaultOrders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Example.create(t).ignoreCase().enableLike(
				MatchMode.ANYWHERE).excludeZeroes());

		try {
			// 找出对象t的所有声明字段。
			Field[] fields = t.getClass().getDeclaredFields();

			for (Field f : fields) {
				// 得到字段的名称。
				String name = f.getName();
				if (!name.equals("serialVersionUID")) {
					// 得到字段的类型全称。
					String type = PropertyUtils.getPropertyType(t, name)
							.getName();
					// 如果是自定义类型 （要求自定义类型的包命名不能以java开头），不完善，但目前还没发现特例
					// 待修正。
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
						// 增加自定义类型字段模板，运用Java反射机制，使用getXxx()方法得到其对象。
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