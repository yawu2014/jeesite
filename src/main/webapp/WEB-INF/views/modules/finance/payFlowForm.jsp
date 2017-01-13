<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>流水</title>
<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/finance/payflow/list">流水总览</a></li>
		<li class="active"><a href="${ctx}/finance/payflow/form">添加流水</a></li>
	</ul><br/>
	<h1>流水添加修改</h1>
	<form:form id="inputForm" modelAttribute="payFlow" action="${ctx }/finance/payflow/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message }"/>
		<div class="control-group">
			<label class="control-label">支付款项:</label>
			<div class="controls">
				<form:input path="payName" htmlEscape="false" maxlength="20" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付来源:</label>
			<div class="controls">
				<select id="payFrom" name="fromWay">
					<c:forEach items="${fromWay}" var="way">
						<option value="${vay.id }">${way.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付对象:</label>
			<div class="controls">
				<select id="payTo" name="toWay">
					<c:forEach items="${toWay}" var="way">
						<option value="${way.id }">${way.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付金额:</label>
				<div class="controls">
				<form:input path="amount" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>