<%@ page language="java" import="java.util.*,test.User,org.displaytag.tags.TableTagParameters,org.displaytag.util.ParamEncoder" pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="user" class="test.User"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/screen.css">
	

  </head>
  <%
  //request.setAttribute("list",user.getUsers());

  request.setAttribute("size",1000);
  String name =new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
  String sort =  new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_SORT);
  String order = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_ORDER);
  if(request.getParameter(sort)!=null){
  	//user.getUsers().remove(9);
  }
  int pageNo;
  if(request.getParameter(name)!=null){
   pageNo = Integer.parseInt(request.getParameter(name));
  }else{
  pageNo=1;
  }
  if(pageNo==1){
  	request.setAttribute("list",user.getUsers().subList(0,10));
  }
  if(pageNo==2){
  	request.setAttribute("list",user.getUsers().subList(10,20));
  }
  if(pageNo==3){
  	request.setAttribute("list",user.getUsers().subList(20,30));
  }
  if(pageNo==4){
  	request.setAttribute("list",user.getUsers().subList(30,40));
  }
  if(pageNo==5){
  	request.setAttribute("list",user.getUsers().subList(40,50));
  }
 
 out.println("sort-------"+request.getParameter(sort));
 out.println("order------------"+request.getParameter(order));
 out.println("pageNo-------"+pageNo);
 out.println(request.getParameter("pagesize"));
  
   %>
   
  <body>
   <display:table name="list" sort="external" partialList="true"  size="size" requestURI="userList.jsp" pagesize="10" id="row" export="true" class="ITS">
   <display:caption>用户列表</display:caption>
   <display:column title="序号" ><c:out value="${row_rowNum}"/></display:column>
   <display:column property="id" sortable="true" sortName="id" sortProperty="id"></display:column>
   <display:column property="name"></display:column>
   <display:column property="password"></display:column>
   </display:table>
   <input type="text" id="pagesize" name="pagesize" value="${pagesize}" />
  </body>
</html>
   <script language="javascript">
  
   </script>