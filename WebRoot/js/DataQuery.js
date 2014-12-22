/*
*采用Jquery Ajax请求后台服务 将json文件交由CallBack函数执行
*zyf 2014-12-10
*/
function QueryDataGet(url,params,callback,scope)
{

    $.ajax({
        url: url, //目标地址
        type: "GET", //用POST方式传输
        dataType: "json", //数据格式:JSON
        data:params,
        beforeSend: function () { }, //发送数据之前
        complete: function () { }, //接收数据完毕
        success: function (response) 
        {
        	callback(response,this);
        },
        error:function()
        {
        	$.messager.show({
				title:'提示',
				msg:'服务器调用出错:'+url,
				showType:'show'
			});
        }
    });
}
function QueryDataPost(url,params,callback,scope)
{
	$.ajax({
        url: url, //目标地址
        type: "POST", //用POST方式传输
        dataType: "text", //数据格式:JSON
        data:params,
        beforeSend: function () { }, //发送数据之前
        complete: function () { }, //接收数据完毕
        success: function (response) 
        {
        	callback(response,this);
        },
        error:function()
        {
        	$.messager.show({
				title:'提示',
				msg:'服务器调用出错:'+url,
				showType:'show'
			});
        }
    });
}