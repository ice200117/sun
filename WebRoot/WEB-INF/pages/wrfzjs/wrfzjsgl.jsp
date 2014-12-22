<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>污染防治技术管理</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript">
	function doAdd() {
		$('#addWrfzjsWindow').window("open");
	}

	function doEdit() {
		alert("修改...");
	}

	function doDelete() {
		alert("删除...");
	}

	function doSearch() {
		$('#searchWindow').window("open");
	}

	
	//工具栏
	var toolbar = [ {
		id : 'button-search',
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}
	];
	// 定义列
	var columns = [ [ {
		field : 'wrfzjs_id',
		title : '技术编号',
		width : 80,
		align : 'center'
	}, {
		field : 'wrfzjs_jsmc',
		title : '技术名称',
		width : 120,
		align : 'center'
	}, {
		field : 'wrfzjs_yyhy',
		title : '应用行业',
		width : 120,
		align : 'center'
	}, {
		field : 'wrfzjs_szd',
		title : '所在地',
		width : 120,
		align : 'center'
	}, {
		field : 'wrfzjs_hzfs',
		title : '合作方式',
		width : 120,
		align : 'center'
	} , {
		field : 'wrfzjs_wrlx',
		title : '污染类型',
		width : 100,
		align : 'center'
	}, {
		field : 'wrfzjs_sczt',
		title : '审查状态',
		width : 100,
		align : 'center'
	}] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 收派标准数据表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList : [ 30, 50, 100 ],
			pagination : true,
			toolbar : toolbar,
			url : "json/decidedzone.json",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加、修改污染防治技术信息
		$('#addWrfzjsWindow').window({
			title : '添加污染防治技术信息',
			width : 600,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});

		// 查询污染防治技术信息
		$('#searchWindow').window({
			title : '查询污染防治技术信息',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		
		$("#btn").click(function() {
			alert("执行查询...");
		});

	});
	
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	
	<!-- 添加 修改污染防治技术信息-->
	<div class="easyui-window" title="污染防治技术信息添加修改" id="addWrfzjsWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>

		<div style="overflow:auto;padding:5px;" border="false">
			<form>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">污染防治技术信息</td>
					</tr>					
					<tr>
						<td>污染防治技术信息名称</td>
						<td><input type="text" name="wrfzjs_jsmc" class="easyui-validatebox" required="true" />
						</td>	
										
						<td>应用行业</td>
						<td><input type="text" name="wrfzjs_yyhy" class="easyui-validatebox" required="true" />
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="wrfzjs_gjz" class="easyui-validatebox" required="true" />
						</td>					

						<td>所在地</td>
						<td><input type="text" name="wrfzjs_szd" class="easyui-validatebox" required="true" />
						</td>
					</tr>
					<tr>
						<td>成果简介</td>
						<td><input type="text" name="wrfzjs_cgjj" />
						</td>					

						<td>应用范围</td>
						<td><input type="text" name="wrfzjs_yyfw" />
						</td>
					</tr>
					<tr>
						<td>前景分析</td>
						<td><input type="text" name="wrfzjs_qjfx" />
						</td>	
						
						<td>污染类型</td>
						<td><input type="text" name="wrfzjs_wrlx" />
						</td>
					</tr>
					<tr>
						<td>知识产权类型</td>
						<td><input type="text" name="wrfzjs_zscqlx" />
						</td>					

						<td>知识产权编号</td>
						<td><input type="text" name="wrfzjs_zscqbh" />
						</td>
					</tr>
					<tr>
						<td>成果所处阶段</td>
						<td><input type="text" name="wrfzjs_jgscjd" />
						</td>					

						<td>合作方式</td>
						<td><input type="text" name="wrfzjs_hzfs" />
						</td>
					</tr>
					<tr>
						<td>治理污染的效率</td>
						<td><input type="text" name="wrfzjs_zlwrdxl" />
						</td>					

						<td>能耗</td>
						<td><input type="text" name="wrfzjs_nf" />
						</td>
					</tr>
					<tr>
						<td>经效比</td>
						<td><input type="text" name="wrfzjs_jxb" />
						</td>	
										
						<td>审查状态</td>
						<td><input type="text" name="wrfzjs_sczt" />
						</td>	
					</tr>
					
				</table>
			</form>
		</div>
	</div>
	<!-- 查询污染防治技术信息 -->
	<div class="easyui-window" title="查询污染防治技术信息窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
											
					<tr>
						<td>污染防治技术信息名称</td>
						<td><input type="text" name="wrfzjs_jsmc" />
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="wrfzjs_gjz" />
						</td>
					</tr>
					<tr>
						<td>污染类型</td>
						<td><input type="text" name="wrfzjs_wrlx" />
						</td>
					</tr>
					<tr>
						<td>审查状态</td>
						<td><input type="text" name="wrfzjs_sczt" />
						</td>
					</tr>
					
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>