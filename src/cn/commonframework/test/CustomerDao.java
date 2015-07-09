package cn.commonframework.test;

import cn.commonframework.util.BaseDAO;

public class CustomerDao extends BaseDAO<Customer> implements ICustomerDao {

	public CustomerDao() {
		super(Customer.class);
		
	}
	

}
