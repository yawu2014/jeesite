<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>流水</title>
<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/finance/payaccount/list">流水总览</a></li>
		<li><a href="${ctx}/finance/payflow/form">添加流水</a></li>
	</ul>
	<br/>
	<form:form id="searchForm" modelAttribute="payFlow" action="${ctx }/finance/payflow" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" values="${page.pageNo }"/>
		<input id="pageSize" name="pageSize" type="hidden" values="${page.pageSize }"/>
		<label>名称:</label>
		<form:input path="payName" htmlEscape="false" maxlength="50" class="input-medium"/> &nbsp;&nbsp;
		<label>时间:</label>
		<form:input path="serialNo" htmlEscape="false" maxlength="50" class="input-medium"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流水号</th>
				<th>名称</th>
				<th>来源</th>
				<th>去向</th>
				<th>金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="item">
				<tr>
					<td>${item.serialNo }</td>
					<td>${item.payName }</td>
					<td>${item.fromWay }</td>
					<td>${item.toWay }</td>
					<td>${item.amount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>