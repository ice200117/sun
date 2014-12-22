<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("body").css({visibility:"visible"});
		$('#save').click(function(){
			var isValid = $('#form').form('validate');// 执行validate方法
			if(isValid){ // 手动判断
				$('#form').submit();
			}
		});
	});
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
		</div>
	</div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
       <s:form id="form" action="user_add" method="post" theme="simple">
           <table class="table-edit"  width="95%" align="center">
           		<tr class="title"><td colspan="4">基本信息</td></tr>
	           	<tr><td>用户名:</td><td>
	           		<s:hidden name="id" value="%{model.id}"></s:hidden>
	           		<s:textfield name="username" id="username" cssClass="easyui-validatebox" data-options="required:true" value="%{model.username}"></s:textfield>
	           	</td>
					<td>密码:</td><td>
						<s:password name="password" id="password" cssClass="easyui-validatebox" data-options="required:true" value="%{model.password}"></s:password>
					</td></tr>
				<tr><td>用户类型:</td><td>
						<s:textfield name="userType" id="userType" cssClass="easyui-validatebox" data-options="required:true" value="%{model.userType}"></s:textfield>
					</td></tr>
				<tr class="title"><td colspan="4">其他信息</td></tr>
	           	<tr><td>E-Mail:</td><td>
	           		<s:textfield name="userMail" id="userMail" value="%{model.userMail}"></s:textfield>
	           	</td>
					<td>手机号:</td><td>
						<s:textfield name="userPhone" id="userPhone" cssClass="easyui-numberbox" value="%{model.userPhone}"></s:textfield>
					</td></tr>
	           	<!-- <tr><td>性别:</td><td>
	           		<s:select list="{'男','女'}" name="gender" cssClass="easyui-combobox" id="gender" headerKey="" headerValue="请选择" cssStyle="width: 150px;" value="%{model.gender}"></s:select>
	           	</td>
					<td>单位:</td><td>
	           		<s:select list="{'总公司','分公司','厅点','基地运转中心','营业所'}" cssClass="easyui-combobox" name="station" id="station"  headerKey="" headerValue="请选择" cssStyle="width: 150px;" value="%{model.station}"></s:select>
				</td></tr>
				<tr>
					<td>联系电话</td>
					<td colspan="3">
						<s:textfield name="telephone" id="telephone" value="%{model.telephone}"></s:textfield>
					</td>
				</tr>
	           	<tr><td>备注:</td><td colspan="3">
	           		<s:textarea style="width:80%" name="remark" id="remark" value="%{model.remark}"></s:textarea>
	           	</td></tr> -->
           </table>
       </s:form>
	</div>
</body>
</html>