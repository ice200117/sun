package cn.sun.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import cn.sun.domain.Qiye;
import cn.sun.domain.User;
import cn.sun.utils.CommonUtility;
import cn.sun.utils.HibernateUtil;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

import com.vividsolutions.jts.geom.Coordinate;  
import com.vividsolutions.jts.geom.Geometry;  
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;  
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;  
import com.vividsolutions.jts.io.WKTReader; 
import com.vividsolutions.jts.io.WKTWriter;
import org.hibernatespatial.criterion.SpatialRestrictions; 

/**
 * 取派标准管理
 * 
 * @author seawind
 * 
 */
public class QiyeAction extends BaseAction {
	// 构造model
	public QiyeAction(String modelClassName) {
		try {
			model = Class.forName(modelClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加 或者 修改 标准 (ajax 方式) ==========================
	 */
	public String addOrUpdate() {
		// 获得登陆用户 ，管理 标准对象
		User user = (User) getSession().getAttribute("user");
		Qiye qiye = (Qiye) model;
		//CommonUtility.PrintInfo(qiye.getQyJdz()+"");
		qiye.setUser(user);
		WKTReader fromText = new WKTReader();  
        Geometry geom = null;  
        try {  
            geom = fromText.read(qiye.getGeoWkt());
            qiye.setQyGeo(geom);
        } catch (ParseException e) {  
            throw new RuntimeException("Not a WKT string:" + qiye.getGeoWkt());  
        }  
        
	    String uuid = UUID.randomUUID().toString().trim().replaceAll("-", ""); 
	    qiye.setQyYhbh(uuid);
	   // 调用业务层
		qiyeService.addOrUpdateQiye(qiye);
		return "addOrUpdateSUCCESS";
	}

	public String getMsg() {
		return msg;
	}

	private String msg = "success";
	
	/**
	 * 分页列表查询 自由SQL =================================================
	 */
	public String SpatialPaginationQuery()
	{
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		Qiye qiye = (Qiye) model;
		WKTReader fromText = new WKTReader();  
        Geometry geom = null;  
        try {
        	CommonUtility.PrintInfo(qiye.getGeoWkt());
			geom = fromText.read(qiye.getGeoWkt());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Qiye.class);
		ProjectionList projList = Projections.projectionList(); 
		projList.add(Projections.property("qyId").as("qyId"));
		projList.add(Projections.property("name").as("name")); 
		projList.add(Projections.property("qyJdz").as("qyJdz")); 
		projList.add(Projections.property("qyWdz").as("qyWdz"));
		projList.add(Projections.property("geoWkt").as("geoWkt"));
		detachedCriteria.setProjection(projList); 
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(Qiye.class)); 
		// 查询没有删除的数据
		detachedCriteria.add(Restrictions.eq("qyScbj", "0"));
		detachedCriteria.add(SpatialRestrictions.within("qyGeo", geom));
		pageRequestBean.setDetachedCriteria(detachedCriteria);

		// 传递分页查询 对象 给业务层
		pageResponseBean = qiyeService
				.paginationQuery(pageRequestBean);
		return "paginationQuerySUCCESS";
	}
	/**
	 * 分页列表查询 自由SQL =================================================
	 */
	public String SqlPaginationQuery()
	{
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		//CommonUtility.PrintInfo(radius+"");
		//封装条件查询
		Qiye qiye = (Qiye) model;
		String qyName=qiye.getName()==null?"":qiye.getName();
		String qySzd=qiye.getSzd()==null?"":qiye.getSzd();
		final String sql="select qy_id as qyId,qy_qymc as name,qy_jdz as qyJdz,qy_wdz as qyWdz,geo_wkt as geoWkt from t_enterprise";
		String sqlFilter=" where qy_scbj='0' and (qy_qymc like '%"+qyName+"%' or qy_szd like'%"+qySzd+"%')";
		if(qiye.getGeoWkt()!=null)
		{
			sqlFilter+=" and MBRContains(GeomFromText('"+qiye.getGeoWkt()+"'),qy_geo)";
		}
		Map map=new HashMap();
		map.put("qyId", Hibernate.STRING);
		map.put("name", Hibernate.STRING);
		map.put("qyJdz", Hibernate.DOUBLE);
		map.put("qyWdz", Hibernate.DOUBLE);
		map.put("geoWkt", Hibernate.STRING);
		// 传递分页查询 对象 给业务层
		pageResponseBean = qiyeService.SqlPaginationQuery(pageRequestBean, sql+sqlFilter,map);
		return "paginationQuerySUCCESS";
	}
	/**
	 * 分页列表查询 =================================================
	 */
	public String paginationQuery() {
		//String tbAlias="t_enterprise";
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		//CommonUtility.PrintInfo(page+"---"+rows+"");
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Qiye.class);
		
		ProjectionList projList = Projections.projectionList(); 
		projList.add(Projections.property("qyId").as("qyId"));
		projList.add(Projections.property("name").as("name")); 
		projList.add(Projections.property("qyJdz").as("qyJdz")); 
		projList.add(Projections.property("qyWdz").as("qyWdz"));
		projList.add(Projections.property("geoWkt").as("geoWkt"));
		detachedCriteria.setProjection(projList); 
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(Qiye.class)); 
		// 查询没有删除的数据
		detachedCriteria.add(Restrictions.eq("qyScbj", "0"));
		//封装条件查询
		Qiye qiye = (Qiye) model;
		if (qiye.getName() != null
				&& qiye.getName().trim().length() > 0) {
			detachedCriteria.add(Restrictions.like("name", "%"
					+ qiye.getName() + "%"
					));
		}
		if(qiye.getSzd()!=null&&qiye.getSzd().trim().length()>0){
			detachedCriteria.add(Restrictions.like("szd", "%"
					+ qiye.getSzd() + "%"));
		}
		if(qiye.getGeoWkt()!=null)
		{
			WKTReader fromText = new WKTReader();  
	        Geometry geom = null;  
	        try {
	        	//CommonUtility.PrintInfo(qiye.getGeoWkt());
				geom = fromText.read(qiye.getGeoWkt());
				detachedCriteria.add(SpatialRestrictions.within("qyGeo", geom));
				//CommonUtility.PrintInfo(qiye.getGeoWkt());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pageRequestBean.setDetachedCriteria(detachedCriteria);

		// 传递分页查询 对象 给业务层
		pageResponseBean = qiyeService
				.paginationQuery(pageRequestBean);

		return "paginationQuerySUCCESS";
	}
	/** 
     * create a Circle  创建一个圆，圆心(x,y) 半径RADIUS 
     * @param x 
     * @param y 
     * @param RADIUS 
     * @return 
     */  
    public Polygon createCircle(double x, double y, final double RADIUS)
    {  
    	GeometryFactory geometryFactory = new GeometryFactory();
        final int SIDES = 32;//圆上面的点个数  
        Coordinate coords[] = new Coordinate[SIDES+1];  
        for( int i = 0; i < SIDES; i++){  
            double angle = ((double) i / (double) SIDES) * Math.PI * 2.0;  
            double dx = Math.cos( angle ) * RADIUS;  
            double dy = Math.sin( angle ) * RADIUS;  
            coords[i] = new Coordinate( (double) x + dx, (double) y + dy );  
        }  
        coords[SIDES] = coords[0];  
        LinearRing ring = geometryFactory.createLinearRing( coords );  
        Polygon polygon = geometryFactory.createPolygon( ring, null );  
        return polygon;  
    }  
	private int page;
	private int rows;

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	private PageResponseBean pageResponseBean;

	public PageResponseBean getPageResponseBean() {
		return pageResponseBean;
	}

	/**
	 * 批量删除 =============================
	 */
	public String delete() {
		// 获得id 数组
		String[] idArr = ids.split(",");

		// 传递业务层 完成删除
		qiyeService.deleteBatch(idArr);

		return "deleteSUCCESS";
	}

	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}




	public List<Qiye> getQiyes() {
		return qiyes;
	}

	private List<Qiye> qiyes;

	

}
