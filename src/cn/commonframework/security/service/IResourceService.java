/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.model.Resource;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 ����03:53:15
 *
 */
public interface IResourceService {

	public void saveResource(Resource resource);
	
	public void updateResource(Resource resource);
	
	public void deleteResource(Resource resource);
	
	public int batchUpdate(String hql,Object...o);
	
	public Resource findById(String id);
	
	public List<Resource> getAll();
	
	public List<Resource> getAllByHql(String hql);
	
	public List<Resource> getAllByCriteria(Criterion... criterion);
	
	public List<Resource> getAllByExample(Resource resource,boolean enableLike,String... properties);
	
	public List<Resource> getAllByExample(Resource resource);
	
	/**
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param resource ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(Resource resource,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(Resource resource,PageParams pageParams,Order...defaultOrders );
}
