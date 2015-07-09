/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.dao.IMenuDao;
import cn.commonframework.security.model.Menu;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-15 ÏÂÎç03:08:52 <br>
 *
 */
public class MenuServiceImpl implements IMenuService {

	private IMenuDao menuDao = null;
	
	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#batchUpdate(java.lang.String, java.lang.Object[])
	 */
	public int batchUpdate(String hql, Object... o) {
		// TODO Auto-generated method stub
		return this.getMenuDao().batchUpdate(hql, o);
	}

	public void batchUpdate(List<Menu> list) {
		// TODO Auto-generated method stub
		this.getMenuDao().batchUpdate(list);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#deleteMenu(cn.commonframework.security.model.Menu)
	 */
	public void deleteMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.getMenuDao().delete(menu);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#findById(java.lang.String)
	 */
	public Menu findById(String id) {
		// TODO Auto-generated method stub
		return this.getMenuDao().findById(id);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#fuzzyQuery(cn.commonframework.security.model.Menu, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation fuzzyQuery(Menu menu, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getMenuDao().fuzzyQuery(menu, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#getAll()
	 */
	public List<Menu> getAll() {
		// TODO Auto-generated method stub
		return this.getMenuDao().getAll();
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#getAllByCriteria(org.hibernate.criterion.Criterion[])
	 */
	public List<Menu> getAllByCriteria(Criterion... criterion) {
		// TODO Auto-generated method stub
		return this.getMenuDao().getAllByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#getAllByExample(cn.commonframework.security.model.Menu, boolean, java.lang.String[])
	 */
	public List<Menu> getAllByExample(Menu menu, boolean enableLike,
			String... properties) {
		// TODO Auto-generated method stub
		return this.getMenuDao().getAllByExample(menu, enableLike, properties);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#getAllByExample(cn.commonframework.security.model.Menu)
	 */
	public List<Menu> getAllByExample(Menu menu) {
		// TODO Auto-generated method stub
		return this.getMenuDao().getAllByExample(menu);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#getAllByHql(java.lang.String)
	 */
	public List<Menu> getAllByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getMenuDao().getAllByHql(hql);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#getAllWithPage(cn.commonframework.security.model.Menu, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation getAllWithPage(Menu menu, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getMenuDao().getAllWithPage(menu, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#saveMenu(cn.commonframework.security.model.Menu)
	 */
	public void saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.getMenuDao().save(menu);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.security.service.IMenuService#updateMenu(cn.commonframework.security.model.Menu)
	 */
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.getMenuDao().update(menu);
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	public IMenuDao getMenuDao() {
		return menuDao;
	}
}
