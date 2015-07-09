<%@ page contentType="text/html;charset=Gb2312" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<link href="${ctx}/styles/admin/admin.css" type="text/css" rel="stylesheet">
	<title>${CompanyName}${ProjectName}</title>
	<%@ include file="/commons/meta.jsp" %>
</head>
  
<frameset rows="*" cols="*" frameborder="NO" border="1px" framespacing="0">
	<frameset cols="30%,*" frameborder="NO" border="1px" framespacing="0">
		<frame src="${ctx}/commonFramework/organization/structure/structureTree.jsp?struTypeId='<s:property value="%{struType.id}"/>'"  name="structureLeftFrame">
		<frame src="${ctx}/commonFramework/organization/structure/structureContent.jsp" name="structureRightFrame">
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>