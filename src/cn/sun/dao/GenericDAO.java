package cn.sun.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.sun.domain.Qiye;
import cn.sun.utils.CommonUtility;

/**
 * 使用DAO 的通用功能
 * @author seawind
 *
 */
@SuppressWarnings("all")
public class GenericDAO<T> extends HibernateDaoSupport {

	private String className;

	// 通过构造器 注入 类名
	public GenericDAO(String className) {
		this.className = className;
	}

	// 通用保存方法
	public void save(T obj) {
		this.getHibernateTemplate().save(obj);
	}

	// 通用修改方法
	public void update(T obj) {
		this.getHibernateTemplate().update(obj);
	}

	// 通用删除方法
	public void delete(T obj) {
		this.getHibernateTemplate().delete(obj);
	}

	// 通用查询方法
	public List<T> findAll() {
		return this.getHibernateTemplate().find("from " + className);
	}

	// 根据id 查询
	public T findById(Serializable id) {
		Class c = null;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T) this.getHibernateTemplate().get(c, id);
	}

	// 根据条件 查询
	public List<T> findByNamedQuery(String queryName, Object... args) {
		return this.getHibernateTemplate().findByNamedQuery(queryName, args);
	}

	public List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	// 分页条件查询
	public List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria,
			int firstResult, int maxResults) {
		//CommonUtility.PrintInfo(this.getHibernateTemplate().toString());
		return this.getHibernateTemplate().findByCriteria(detachedCriteria,
				firstResult, maxResults);
	}

	// 查询表中 总记录数
	public int findByTotalCount() {
		String hql = "select count(*) from " + className;
		List list = this.getHibernateTemplate().find(hql);
		long totalCount = (Long) list.get(0);
		return (int) totalCount;
	}
	public List<T> execSQLQuery(final int firstResult,final int maxResults,final String sql,final Map map)  
    {  
        return (List<T>) this.getHibernateTemplate().execute(  
                new org.springframework.orm.hibernate3.HibernateCallback()  
                {  
                    public Object doInHibernate(final Session session)  
                            throws HibernateException, SQLException  
                    {  
                        final SQLQuery query = session.createSQLQuery(sql);  
                        query.setMaxResults(maxResults);
                        query.setFirstResult(firstResult);
                        Iterator iterator = map.keySet().iterator();                
                        while (iterator.hasNext()) 
                        {    
	                        Object key = iterator.next();   
	                        query.addScalar(key.toString(),(Type)map.get(key));     
                        }            
                        //关键转换方法query.setResultTransformer,参数AliasToBeanResultTransformer(映射到的POJO类).  
                        try {
							query.setResultTransformer(new AliasToBeanResultTransformer(Class.forName(className)));
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
                        return query.list();  
                    }  
                });  
    }  
}
