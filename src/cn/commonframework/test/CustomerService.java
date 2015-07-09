package cn.commonframework.test;

public class CustomerService {
	private ICustomerDao customerDao= null;

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	public void updateCustomer(Customer customer){
		this.getCustomerDao().update(customer);
	}
	
	public Customer findById(int id){
		return this.getCustomerDao().findById(id);
	}
	
	public void test(){
		Customer c = this.findById(103);
		c.setAddressline2("Test Transaction!!!");
		this.updateCustomer(c);
		/*c = this.findById(102);
		this.getCustomerDao().delete(c);*/
	}
	public void deleteCustomer(Customer customer){
		this.getCustomerDao().delete(customer);
	}
	
	public int batchUpdate(String hql,Object... o){
		return this.getCustomerDao().batchUpdate(hql, o);
	}
}
