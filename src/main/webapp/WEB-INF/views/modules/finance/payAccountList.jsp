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
	<form:form id="searchForm" modelAttribute="payAccount" action="${ctx }/finance/payaccount" class="breadcrumb form-search">
		<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
		<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/>
		<label>
		账户类型:
		</label>
		<form:input path="type" htmlEscape="false" maxlength="50" class="input-medium"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>账户</th>
				<th>类型</th>
				<th>金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="item">
				<tr>
					<td>${item.name }</td>
					<td>${item.type }</td>
					<td>${item.amount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>