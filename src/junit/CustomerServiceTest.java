package junit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.commonframework.test.Customer;
import cn.commonframework.test.CustomerService;

public class CustomerServiceTest {
	private static CustomerService customerService;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
			customerService = (CustomerService) cxt.getBean("customerService");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	
	
	@Test public void getPerson(){
		Customer c = customerService.findById(103);
		System.out.println(c.getCity());
	}
	
	@Test public void update(){
		Customer c = customerService.findById(103);
		c.setAddressline2("OK");
		customerService.updateCustomer(c);
	}
	
	//类似的加上其他需要测试的方法。
	@Test public void delete(){
		try {
			Customer c = customerService.findById(102);
			customerService.deleteCustomer(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
