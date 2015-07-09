package cn.commonframework.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.commonframework.security.dao.IUserDao;
import cn.commonframework.security.model.User;
import cn.commonframework.util.PageParams;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Customer c = new Customer();
		/*c.setCustomernumber(88888);
		c.setCustomername("≤‚ ‘ ˝æ›");*/
		/*ICustomerDao dao = (ICustomerDao) context.getBean("customerDao");
		c = dao.findById(103);
		c.setAddressline2("test2");
		dao.update(c);*/
		//CustomerService cs = (CustomerService) context.getBean("customerService");
		//cs.test();
		IUserDao uDao = (IUserDao) context.getBean("userDao");
		for(int i=0;i<50;i++){
			User u = new User();
			u.setCode("2009"+i+1);
			u.setName("name"+i);
			u.setPassword("111");
			u.setIsEnabled("Y");
			uDao.save(u);
		}
		System.out.println(uDao.getAll().size());
		//System.out.println(c);
		//System.out.println(dao.getAll().size());
/*
		IUserDao uDao = (IUserDao) context.getBean("userDao");
		IRoleDao rDao = (IRoleDao) context.getBean("roleDao");
		IResourceDao reDao = (IResourceDao) context.getBean("resourceDao");
		User u = uDao.getAll().get(0);
		
		
		
		Role r = rDao.getAll().get(0);
		Set s = new HashSet();
		s.add(r);
		Resource rr = new Resource();
		rr.setName("all");
		rr.setType("URL");
		rr.setValue("/**");
		rr.setRoles(s);
		reDao.save(rr);*/
//		u.setCode("001");
//		u.setName("test");
//		//System.out.println(uDao.getAllByExample(u, false).size());
		
		//System.out.println(cs.batchUpdate("update Customer c set c.addressline2='OK'",c));
//		
		/*PageParams pp = new PageParams("row");
		System.out.println(pp.getStart());*/
	}

}
