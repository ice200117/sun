<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理企业信息</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath }/js/DataQuery.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=R56QZQmtdTpQHNpdOT78TZxl"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/RectangleZoom/1.2/src/RectangleZoom_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/MapUtility.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<script type="text/javascript">
	var map;//全局地图
	var enableAdd=0;
	var myDrawingManagerObject;//全局空间查询
	var opts = {
			width : 250,     // 信息窗口宽度
			height: 120,     // 信息窗口高度
			title : "企业信息" , // 信息窗口标题
			enableMessage:false//设置允许信息窗发送短息
		   };
	function doAdd(){
		//$('#addQiyeWindow').window("open");
		map.setDefaultCursor("crosshair");
		enableAdd=1;
	}
	
	function doEdit(){
		alert("修改...");
	}
	
	function doDelete(){
		alert("删除...");
	}
	
	function doSearch(){
		$('#searchWindow').window("open");
	}
	function doExtentSearch(){
		myDrawingManagerObject.open();
		//myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_MARKER);
		myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_CIRCLE);
		//myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_RECTANGLE);
		//myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_POLYGON);
	}
	function doServletSearch(){
		//绘制矩形
		/*var myDrag = new BMapLib.RectangleZoom(map, {
			followText: "拖拽鼠标进行操作"
		});
		myDrag.open();  //开启拉框放大*/
		//Ajax请求前台代码
		var baseParams={cmdName:"queryQiye",pageIndex:1,pageSize:15};
		QueryDataGet("${pageContext.request.contextPath}/BaseServletByCmd.do",baseParams,function(json)
				{
					$.each(json.rows,function(i,entity)
							{
								alert(entity.name+":"+entity.qyJdz+","+entity.qyWdz);
							});
				},this);
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
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}
	,{
		id : 'button-extsearch',
		text : '空间查询',
		iconCls : 'icon-search',
		handler : doExtentSearch
	}
	,{
		id : 'button-servlet',
		text : 'Ajax&Servlet',
		iconCls : 'icon-search',
		handler : doServletSearch
	}];
	// 定义列
	var columns = [ [ 
	             	{
			field : 'id',
			checkbox : true,
		}, 
		{
			field : 'name',
			title : '企业名称',
			width : 250,
			align : 'center'
		}
	]];
	
	$(function(){
		//初始化地图下拉框
		InitLocalComp();
		
		//初始化地图
		InitLocalMap("廊坊");
		//初始化空间查询
		InitDrawingManager();
		
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 企业信息数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/qiye_SqlPaginationQuery.action",
			idField : 'id',
			columns : columns,
			onLoadSuccess:function(data)
			{
				map.clearOverlays();
				
				for(var i=0;i<parseInt(data.total);i++)
				{
					var entity=data.rows[i];
					var markerPt = new BMap.Point(parseFloat(entity.qyJdz), parseFloat(entity.qyWdz));
					var marker = new BMap.Marker(markerPt);
					map.addOverlay(marker); 
					var winContent="企业名称："+entity.name+"<br/>经度:"+entity.qyJdz+"<br/>纬度:"+entity.qyWdz;
					addClickHandler(winContent,marker);
				}
			},
			onClickRow:function(rowIndex)
			{
				var row = $('#grid').datagrid('getSelected');
				var markerPt = new BMap.Point(parseFloat(row.qyJdz), parseFloat(row.qyWdz));
				map.centerAndZoom(markerPt,15);
			}

		});
		function addClickHandler(content,marker){
			marker.addEventListener("click",function(e)
			{
				openInfo(content,e)
			});
		}
		function openInfo(content,e){
			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
			map.openInfoWindow(infoWindow,point); //开启信息窗口
		}
		// 添加、修改企业信息
		$('#addQiyeWindow').window({
	        title: '添加企业信息',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询企业信息
		$('#searchWindow').window({
	        title: '查询企业信息',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 条件查询
		$("#queryBtn").click(function(){
	
			// 将表单参数 发送到服务器 ，执行查询，将结果 刷新 datagrid 中
			$("#grid").datagrid('load',{
				"name" : $("[name='searchname']").val(),
				"szd" : $("[name='searchszd']").val(),
			});
			// 窗口关闭 
			$('#searchWindow').window("close");
			// 表单设置空 
			$('#searchForm').form('clear');
		});
		
		// 添加修改 企业 按钮
		$("#save").click(function(){
			$("#qiyeForm").form({
				url : '${pageContext.request.contextPath}/qiye_addOrUpdate.action',
				onSubmit : function(){
					return $("#qiyeForm").form('validate');
				},
				success : function(data){
					data = eval("("+data+")");
					if(data.msg == "success"){
						$('#addQiyeWindow').window("close"); // 关闭窗口
						$("#qiyeForm").form('clear'); // 表单重置
						$("#grid").datagrid('reload'); // 表格数据刷新
					}else{
						$.messager.alert('信息','分区信息保存失败！' ,'error');
					}
				}
			});
			$("#qiyeForm").submit();
			enableAdd=0;
			map.setDefaultCursor("hand");
		});
		
		
		
	});
	//初始化空间查询
	function InitDrawingManager()
	{
		var styleOptions = {
		        strokeColor:"red",    //边线颜色。
		        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
		        strokeWeight: 3,       //边线的宽度，以像素为单位。
		        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
		        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
		        strokeStyle: 'solid' //边线的样式，solid或dashed。
		    }
		myDrawingManagerObject = new BMapLib.DrawingManager(map, 
				{
					isOpen: false, 
				    drawingType: BMAP_DRAWING_MARKER, 
				    enableDrawingTool: true,
				    enableCalculate: false,
				    drawingToolOptions: 
					    {
					        anchor: BMAP_ANCHOR_TOP_RIGHT,
					        offset: new BMap.Size(5, 5),
					        scale: 0.5, //工具栏缩放比例
					        drawingTypes : [
					            BMAP_DRAWING_MARKER,
					            BMAP_DRAWING_CIRCLE,
					            BMAP_DRAWING_POLYLINE,
					            BMAP_DRAWING_POLYGON,
					            BMAP_DRAWING_RECTANGLE 
					         ]
		    			},
	    			circleOptions: styleOptions, //圆的样式
	    	        polylineOptions: styleOptions, //线的样式
	    	        polygonOptions: styleOptions, //多边形的样式
	    	        rectangleOptions: styleOptions //矩形的样式
				});
		myDrawingManagerObject.addEventListener("overlaycomplete", function(e) {
		    //alert(e.drawingMode);
		    //alert(e.overlay);
		    //alert(e.calculate);
		    //alert(e.label);
		    if(e.drawingMode=="rectangle"||e.drawingMode=="polygon")
		    {
			    var rectangle=e.overlay;
			    var geoWkt="POLYGON ((";
			    var fstPt=rectangle.getPath()[0];
			    for(var i=0;i<rectangle.getPath().length;i++)
			    {
				    var pt=rectangle.getPath()[i];
				    var pointStr=pt.lng+" "+pt.lat;
				    pointStr+=", "; 
				    geoWkt+=pointStr;
			    }
			    geoWkt+=fstPt.lng+" "+fstPt.lat;
			    geoWkt+="))";
			    $("#grid").datagrid('load',{
					"geoWkt" : geoWkt,
					"radius":0
				});
		    }
		    
		    if(e.drawingMode=="circle")
		    {
			    var circle=e.overlay;
			    var centerPt=circle.getCenter();
			    var nowRadius=circle.getRadius();
			    var geoWkt=CreateCircleWkt(centerPt,nowRadius);
			    $("#grid").datagrid('load',{
					"geoWkt" : geoWkt
				});
		    }
		    if(e.drawingMode=="marker")
		    {
			    var nowMarker=e.overlay;
			    var centerPt=nowMarker.getPosition();
			    var nowRadius=50.0;
			    /*if(map.getZoom()<=15)
			    {
			    	nowRadius=map.getZoom()*10.0;
			    }*/
			    var geoWkt=CreateCircleWkt(centerPt,nowRadius);
			    $("#grid").datagrid('load',{
					"geoWkt" : geoWkt
				});
		    }
			myDrawingManagerObject.close();
		});
	}
	//设置地图  参数城市名称
	function InitLocalMap(city)
	{
		map = new BMap.Map("localmap");    // 创建Map实例
		//map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		map.centerAndZoom(city,15);          // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		// 添加带有定位的导航控件
	  	var navigationControl = new BMap.NavigationControl({
	    // 靠左上角位置
	    anchor: BMAP_ANCHOR_TOP_LEFT,
	    // LARGE类型
	    type: BMAP_NAVIGATION_CONTROL_LARGE,
	    // 启用显示定位
	    enableGeolocation: true
	  });
	  	map.addControl(navigationControl);
	  	map.addEventListener("click",function(e){
		  	$("#qyJdz").val(e.point.lng+"");
		  	$("#qyWdz").val(e.point.lat+"");
		  	$("#geoWkt").val("POINT ("+e.point.lng+" "+e.point.lat+")");
		  	if(enableAdd==1)
		  	{
		  		$('#addQiyeWindow').window("open");
		  	}
			//alert(e.point.lng + "," + e.point.lat);
		});
	}
	//初始化城市
	function InitLocalComp()
	{
		//初始化企业类型下拉框
		
		//城市下拉框
		$('#cbxcities').combobox({
		    url:'${pageContext.request.contextPath}/json/cities.json',
		    valueField:'id',
		    textField:'text',
		    onSelect:function(record){
		    	InitLocalMap(record.text);
	    	}
		});
		//搜索地名
		$('#searchpoi').searchbox({
		    searcher:function(value,name){
				var local = new BMap.LocalSearch(map, {
					renderOptions: {map: map, panel: "poi_result"}
				});
				local.search(value);
		    },
		    prompt:'输入关键字'
		});
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">   
	<div id="enlst" data-options="region:'west',title:'企业列表',split:true" style="width:350px;">
	<table id="grid" singleSelect="true"></table>
	</div>
	<div id="localmap" data-options="region:'center',title:'电子地图',split:true">
	</div>
	<div id="maptool" data-options="region:'east',title:'地图搜索',split:true" style="width:250px;">
		<div class="datagrid-toolbar">	
			<a id="rezoom" href="#" class="easyui-linkbutton" plain="true" icon="icon-reload">城市定位</a>
		</div>
		<form id="form" method="post" >
			<table class="table-edit" width="100%" >				
				<tr>
				<td>
					<input id="cbxcities" name="cbxcities" value="选择城市" style="width:200px;"/>
				</td>
				</tr>
			</table>
		</form>
		<div class="datagrid-toolbar">	
			<a id="queryPoi" href="#" class="easyui-linkbutton" plain="true" icon="icon-search">地名查询</a>
		</div>
		<form id="form" method="post" >
			<input id="searchpoi" name="searchpoi" style="width:200px;"/>
			<div id="poi_result" style="width:100%"></div>
		</form>
	</div>
	<!-- 添加 修改企业 -->
	<div class="easyui-window" title="企业 添加修改" id="addQiyeWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="qiyeForm" method="post" >
			<table class="table-edit" width="100%" >				
				<tr><td>
					<b>企业名称</b>
					<input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>
				</td>
					<td>
					<b>所在地</b>
					<input type="text" id="szd" name="szd" class="easyui-validatebox" required="true"/>
				</td></tr>
				<tr><td>
					<b>企业性质</b>
				    <input type="text" id="qyxz" name="qyxz"/>
				</td><td>
					<b>经度</b>
				    <input type="text" id="qyJdz" name="qyJdz" class="easyui-validatebox" />
				</td></tr>
				<tr><td>
					<b>注册地址</b>
					<input type="text" id="zcdz" name="zcdz" />
				</td><td>
					<b>纬度</b>
				    <input type="text" id="qyWdz" name="qyWdz" class="easyui-validatebox"/>
				</td></tr>
				<tr><td>
					<b>法人代表</b>
				    <input type="text" id="frdb" name="frdb" class="easyui-validatebox" required="true"/>
				</td><td>
				<b>备注</b>
					<input type="text" id="qyBz" name="qyBz"/>
				</td></tr>		
				<tr><td>
					<b>所属行业</b>
				    <input type="text" id="sshy" name="sshy" />
				</td></tr>
				
				<tr><td>
					<b>业务领域</b>
				    <input type="text" id="ywly" name="ywly" />
			    </td></tr>
				<tr><td>
					<b>浏览次数</b>
				    <input type="text" id="qyLlcs" name="qyLlcs" class="easyui-numberbox"/>
				    <input type="hidden" id="geoWkt" name="geoWkt"/>
				</td></tr>
			</table>
		</form>
		</div>
	</div>
	<!-- 查询分区 -->
	<div class="easyui-window" title="查询企业窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="searchForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>企业名称</td>
						<td><input type="text" name="searchname" id="name"/></td>
					</tr>
					<tr>
						<td>所在地</td>
						<td><input type="text" name="searchszd" id="szd"/></td>
					</tr>

					<tr>
						<td colspan="2"><a id="queryBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>