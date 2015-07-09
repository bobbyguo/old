/**
 * 
 */
package cn.commonframework.organization.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import cn.commonframework.organization.model.OrganType;
import cn.commonframework.util.BaseDAO;

/**
 * 组织类型DAO层操作类
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 上午10:57:02 <br>
 *
 */
public class OrganTypeDao extends BaseDAO<OrganType> implements IOrganTypeDao {

	public OrganTypeDao() {
		super(OrganType.class);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.util.BaseDAO#getAllByExample(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganType> getAllByExample(OrganType organType) {
		DetachedCriteria criteria =  DetachedCriteria.forClass(OrganType.class);
		criteria.add(Example.create(organType).ignoreCase().enableLike(MatchMode.ANYWHERE).excludeZeroes());
		if(organType.getParentType()!=null){
			criteria.createCriteria("parentType").add(Example.create(organType.getParentType()));
		}
		return this.getHibernateTemplate().findByCriteria(criteria);
	}	
}
