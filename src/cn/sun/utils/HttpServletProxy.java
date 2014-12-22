package cn.sun.utils;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.web.context.WebApplicationContext;  
import org.springframework.web.context.support.WebApplicationContextUtils; 

import cn.sun.domain.Qiye;

public class HttpServletProxy extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4913294148456546155L;

	/**
	 *HttpServlet 代理 
	 * @author zyf 2014-12-13
	 */
	Log logger = LogFactory.getLog(HttpServletProxy.class);  
    private String targetServlet;  
    private HttpServlet proxy;  
    public void init() throws ServletException 
    {  
        this.targetServlet = getInitParameter("targetServlet");  
        getServletBean();  
        proxy.init(getServletConfig());  
        System.out.println(targetServlet + " was inited by HttpServletProxy  successfully......");  
    }  
    private void getServletBean() {  
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());  
        this.proxy = (HttpServlet) wac.getBean(targetServlet);  
    }  
    @Override  
    public void service(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException, RuntimeException {  
        proxy.service(request, response);  
    }  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	proxy.service(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		proxy.service(request, response);
	}
}
