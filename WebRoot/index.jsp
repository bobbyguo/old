<%@ page contentType="text/html;charset=Gb2312" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<link href="${ctx}/styles/admin/admin.css" type="text/css" rel="stylesheet">
	<title>${CompanyName}${ProjectName}</title>
	<%@ include file="/commons/meta.jsp" %>
</head>
  
<frameset rows="20%,*" cols="*" frameborder="NO" border="1px" framespacing="0"  >
	<frame src="${ctx}/top.jsp" name="topFrame" scrolling="NO" noresize="noresize">
	<frameset cols="15%,*" frameborder="NO" border="1px" framespacing="0" >
		<frame src="${ctx}/menu.jsp" name="leftFrame"  >
		<frame src="${ctx}/main.jsp" name="mainFrame"  >
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>