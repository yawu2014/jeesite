<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>账户</title>
<meta name="decorator" content="default"/>
<style type="text/css">
input[type="radio"] {
	margin:0 auto;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/finance/payaccount/list">账户总览</a></li>
		<li class="active"><a href="${ctx}/finance/payaccount/form">添加账户</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="payAccount" action="${ctx }/finance/payaccount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message }"/>
		<div class="control-group">
			<label class="control-label">账户名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户类型:</label>
			<div class="controls">
				<div class="radio">
					<input type="radio" name="type" value="<%=com.thinkgem.jeesite.modules.finance.entity.PayAccount.TYPE_IN %>">收入
				</div>
				<div class="radio">
					<input type="radio" name="type" value="<%=com.thinkgem.jeesite.modules.finance.entity.PayAccount.TYPE_OUT %>">支出
				</div>
				<div class="radio">
					<input type="radio" name="type" value="<%=com.thinkgem.jeesite.modules.finance.entity.PayAccount.TYPE_INNER %>">内部账户
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">余额:</label>
			<div class="controls">
				<form:input path="amount"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>