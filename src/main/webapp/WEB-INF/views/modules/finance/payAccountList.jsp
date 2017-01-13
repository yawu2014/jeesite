<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>账户</title>
<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/finance/payaccount/list">账户总览</a></li>
		<li><a href="${ctx}/finance/payaccount/form">添加账户</a></li>
	</ul><br/>
	<%--总览全部账户 --%>
	<h1>账户总览</h1>
</body>
</html>