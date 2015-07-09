/**
 * 
 */
package cn.commonframework.organization.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import cn.commonframework.organization.model.Organ;
import cn.commonframework.util.BaseDAO;

/**
 * 组织DAO层操作类
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 下午03:47:28 <br>
 *
 */
public class OrganDao extends BaseDAO<Organ> implements IOrganDao {

	public OrganDao() {
		super(Organ.class);
	}
	
	/* (non-Javadoc)
	 * @see cn.commonframework.util.BaseDAO#getAllByExample(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organ> getAllByExample(Organ organ) {
		DetachedCriteria criteria =  DetachedCriteria.forClass(Organ.class);
		criteria.add(Example.create(organ).ignoreCase().enableLike(MatchMode.ANYWHERE).excludeZeroes());
		if(organ.getOrganType() != null){
			criteria.createCriteria("organType").add(Example.create(organ.getOrganType()));
		}
		
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
}
