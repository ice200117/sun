/*
*百度地图的相关工具
*zyf 2014-12-13
*/
//创建圆形查询的wkt
function CreateCircleWkt(centerPoi,radius)
{
	var wkt="POLYGON ((";
	var sides=32;//圆上面的点个数 
	var mPoi = getMecator(centerPoi);//转换为平面坐标
	var fstWkt="";
	for( var i = 0;i < sides;i++)
	{  
        var angle = (i / sides) * Math.PI * 2.0;  
        var dx = Math.cos( angle ) * radius;  
        var dy = Math.sin( angle ) * radius;  //计算平移距离
        var tdx=mPoi.x+dx;
        var tdy=mPoi.y+dy;
        var newPoi=getPoi(new BMap.Pixel(tdx, tdy));
        wkt+=newPoi.lng+" "+newPoi.lat+", ";
        if(i==0)
        {
        	fstWkt=newPoi.lng+" "+newPoi.lat;
        }
    }
	wkt+=fstWkt+"))";
	return wkt;
}
//根据球面坐标获得平面坐标。
function getMecator(poi)
{
	return map.getMapType().getProjection().lngLatToPoint(poi);
}
//根据平面坐标获得球面坐标。
function getPoi(mecator)
{
	return map.getMapType().getProjection().pointToLngLat(mecator);
}