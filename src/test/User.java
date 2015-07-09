package test;
import java.util.ArrayList;
import java.util.List;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;


public class User {
	
	private String id;
	private String name;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	static List list = new ArrayList();
	static{
		
		for(int i=0;i<30;i++){
			User u = new User();
			u.setId("id"+i);
			u.setName("name"+i);
			u.setPassword("password"+i);
			list.add(u);
		}
		for(int i=0;i<20;i++){
			User u = new User();
			u.setId(9+"id"+"S"+i);
			u.setName("name"+"S"+i);
			u.setPassword("password"+"S"+i);
			list.add(u);
		}
		
	}
	
	public List getUsers(){
		
		return list;
		//String name =new ParamEncoder("element").encodeParameterName(TableTagParameters.PARAMETER_PAGE);

		//int pageNo = Integer.parseInt(request.getParameter(name));


	}
}
