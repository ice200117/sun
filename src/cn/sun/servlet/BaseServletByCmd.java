package cn.sun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.sun.domain.Qiye;
import cn.sun.service.QiyeService;
import cn.sun.service.impl.QiyeServiceImpl;
import cn.sun.utils.CommonUtility;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;



public class BaseServletByCmd extends HttpServlet 
{
	/**
	 * 业务Servlet 
	 * @author zyf 2014-12-13
	 */
	
	private static final long serialVersionUID = -949586723887253639L;
	private QiyeService qiyeService;
	public void setQiyeService(QiyeService qiyeService) {
		this.qiyeService = qiyeService;
	}
	public BaseServletByCmd() 
	{
		super();
	}
	 public void init() throws ServletException 
	{  
        super.init();  
	}  
	public void destroy() 
	{
		super.destroy(); 
	}
	 @Override  
    public void service(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException, RuntimeException {  
		 doGet(request,response);  
    }     
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		try 
		{
			CommonUtility.PrintInfo("****根据命令获取数据****");
			response.setContentType("text/text;charset=utf-8");
			String cmdName=new String(request.getParameter("cmdName").toString().getBytes("ISO-8859-1"),"gb2312");
			int pageIndex=Integer.parseInt(new String(request.getParameter("pageIndex").toString().getBytes("ISO-8859-1"),"gb2312"));
			int pageSize=Integer.parseInt(new String(request.getParameter("pageSize").toString().getBytes("ISO-8859-1"),"gb2312"));
			PageRequestBean pageRequestBean = new PageRequestBean();
			pageRequestBean.setPage(pageIndex);
			pageRequestBean.setRows(pageSize);
			PageResponseBean responseBean=null;
			if(cmdName.equals("queryQiye"))
			{
				//QiyeService ctrl=new QiyeServiceImpl();
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
				pageRequestBean.setDetachedCriteria(detachedCriteria);
				responseBean=qiyeService.paginationQuery(pageRequestBean);
			}
			JSONObject jsonObj=JSONObject.fromObject(responseBean);
			PrintWriter out=response.getWriter();
			out.println(jsonObj.toString());
			out.flush();
			out.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
