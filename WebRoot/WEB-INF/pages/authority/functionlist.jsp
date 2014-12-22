<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	// 工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查看',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '新增',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}];
	//定义冻结列
	var frozenColumns = [ [ {
		field : 'id',
		checkbox : true,
		//rowspan : 2
	}, {
		field : 'funcName',
		title : '名称',
		width : 160,
		//rowspan : 2
	} ] ];


	// 定义标题栏
	var columns = [ [ {
		field : 'url',
		title : 'url',
		width : 320,
		//rowspan : 2,
		align : 'center'
	}, /*{
		field : 'birthday',
		title : '上一级功能',
		width : 120,
		//rowspan : 2,
		align : 'center'
	},*/ {
		field : 'operator',
		title : '操作人',
		width : 120,
		//rowspan : 2,
		align : 'center'
	}] ];
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/func_paginationQuery.action",
			idField : 'id', 
			frozenColumns : frozenColumns,
			columns : columns,
			onClickRow : onClickRow,
			onDblClickRow : doDblClickRow
		});
		
		$("body").css({visibility:"visible"});
		
	});
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		var items = $('#grid').datagrid('selectRow',rowIndex);
		doView();
	}
	// 单击
	function onClickRow(rowIndex){

	}
	
	function doAdd() {
		//alert("添加用户");
		location.href="${pageContext.request.contextPath}/page_authority_functioninfo.action";
	}

	function doView() {
		var item = $('#grid').datagrid('getSelected');
		// 将id 发送给 服务器 进行 查询， 将结果显示 form中 (非Ajax请求)
		location.href="${pageContext.request.contextPath}/func_editView.action?id="+item.id;
	}

	function doDelete() {
		// 定义数组
		var ids = [];
		// 获得选中所有行
		var items = $('#grid').datagrid('getSelections');
		
		// 获得选择所有行 id 放入 ids 数组
		for(var i=0; i<items.length; i++){
		    ids.push(items[i].id);	    
		}
		if(ids.length >0){
			// 将选中 id 发送给 服务器删除
			$.post("${pageContext.request.contextPath}/func_delete.action",{"ids": ids.join(",")}, function(data){
				if(data.msg == "success"){
					$.messager.alert("提示信息","删除成功","info");
				}
			});
			$('#grid').datagrid('reload'); // 重新表格数据
			$('#grid').datagrid('uncheckAll'); // 取消所有行选择效果
		}
	}
	
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>