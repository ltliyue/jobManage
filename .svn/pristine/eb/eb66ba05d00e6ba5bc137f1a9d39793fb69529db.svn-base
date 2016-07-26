package inspur.crawl.common.interceptor;
import inspur.crawl.common.tools.ReflectHelper;
import inspur.crawl.common.tools.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.lang.reflect.Field;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;



@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageHelper implements Interceptor {
	
	private static String dialect = "";	//数据库方言
	private static String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配)
	
	public Object intercept(Invocation ivk) throws Throwable {
		// TODO Auto-generated method stub
		if(ivk.getTarget() instanceof RoutingStatementHandler){
			RoutingStatementHandler statementHandler = (RoutingStatementHandler)ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
			
			if(mappedStatement.getId().matches(pageSqlId)){ //拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();//分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if(parameterObject==null){
					throw new NullPointerException("parameterObject尚未实例化！");
				}else{
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					String countSql = "select count(0) from (" + sql+ ")  tmp_count"; //记录统计
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql,boundSql.getParameterMappings(),parameterObject);
					
					// ======================== lishenjie by 2012/4/13 ============================
					// 筛选条件使用  MyBatis Generator 生成的 Criteria 作为参数传入时，
					// 需要给 countBS 的 additionalParameters 赋值
					
					// ============================================================================
					
					setParameters(countStmt,mappedStatement,countBS,parameterObject);
					ResultSet rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
					Page page = null;
					if(parameterObject instanceof Page){	//参数就是Page实体
						 page = (Page) parameterObject;
						 page.setEntityOrField(true);	 //见com.jiayu.sys.model.Page.entityOrField 注释
						 page.setTotalResult(count);
						 System.out.println(":::::"+page);
					}else if(parameterObject instanceof Map || parameterObject instanceof HashMap || parameterObject.getClass().toString().indexOf("java.util.HashMap")>-1||"java.util.Map".equals(parameterObject.getClass()) ){
						Map map=(Map) parameterObject;
						if(map.containsKey("obj")){
							parameterObject=map.get("obj");
							Field pageField = ReflectHelper.getFieldByFieldName(parameterObject,"page");
							if(pageField!=null){
								page = (Page) ReflectHelper.getValueByFieldName(parameterObject,"page");
								if(page==null)
									page = new Page();
								page.setEntityOrField(false); //见com.lvbang.sys.bean.Page.entityOrField 注释
								page.setTotalResult(count);
								ReflectHelper.setValueByFieldName(parameterObject,"page", page); //通过反射，对实体对象设置分页对象
								System.out.println(":::::"+map);
							}else{
								throw new NoSuchFieldException(parameterObject.getClass().getName()+"不存在 page 属性！");
							}
						}else if(map.containsKey("page")){
							page = (Page) map.get("page");
							if(page==null)
								page = new Page();
							page.setEntityOrField(false); //见com.lvbang.sys.bean.Page.entityOrField 注释
							page.setTotalResult(count);
						}					
					}else{	//参数为某个实体，该实体拥有Page属性
						Field pageField = ReflectHelper.getFieldByFieldName(parameterObject,"page");
						if(pageField!=null){
							page = (Page) ReflectHelper.getValueByFieldName(parameterObject,"page");
							System.out.println("page::::"+page);
							if(page==null){
								page = new Page();
								System.out.println("实例化page");
							}
							page.setEntityOrField(false); //见com.jiayu.sys.model.Page.entityOrField 注释
							page.setTotalResult(count);
							ReflectHelper.setValueByFieldName(parameterObject,"page", page); //通过反射，对实体对象设置分页对象
							System.out.println(parameterObject+"  ----"+page.getCurrentPage());
						}else{
							throw new NoSuchFieldException(parameterObject.getClass().getName()+"不存在 page 属性！");
						}
					}
					String pageSql = generatePageSql(sql,page);
					System.out.println("pageSql::::::::::::"+pageSql+"page::"+page.getCurrentPage()+"  "+page.getShowCount());
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.
				}
			}
		}
		return ivk.proceed();
	}

	
	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					int count=0;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						Object obj=metaObject.getOriginalObject();
						if(obj instanceof HashMap){
							if(propertyName.indexOf("__frch_")>=0 && propertyName.indexOf("_0")>0){
								propertyName=propertyName.substring(7,propertyName.length()-2);
							}
							Object tmpobj=metaObject.getValue(propertyName);
							if(tmpobj instanceof ArrayList){
								TypeHandler typeHandler = parameterMapping.getTypeHandler();
								if (typeHandler == null) {
									throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
								}
								List<Object> listobj=(List<Object>)tmpobj;
								for(Object obj1 : listobj){
									typeHandler.setParameter(ps, ++i, obj1, parameterMapping.getJdbcType());
									count++;
								}
							}
						}
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					if(count==0){
						TypeHandler typeHandler = parameterMapping.getTypeHandler();
						if (typeHandler == null) {
							throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
						}
						typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
					}
				}
			}
		}
	}
	
	/**
	 * 根据数据库方言，生成特定的分页sql
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePageSql(String sql,Page page){
		//System.out.println(sql+":"+page);
		if(page!=null && Tools.notEmpty(dialect)){
			StringBuffer pageSql = new StringBuffer();
			if("mysql".equals(dialect)){
				pageSql.append(sql);
				pageSql.append(" limit "+page.getCurrentResult()+","+page.getShowCount());
			}else if("oracle".equals(dialect)){
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(")  tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentResult()+page.getShowCount());
				pageSql.append(") where row_id>");
				pageSql.append(page.getCurrentResult());
			}else if("db2".equals(dialect)){
				pageSql.append("select * from ( select ROW_NUMBER() OVER(order by 0 desc) AS ROWNUM,a.* from (");
				pageSql.append(sql);
				pageSql.append(" ) as a) where ROWNUM >");
				pageSql.append(page.getCurrentResult());
				pageSql.append(" and ROWNUM<=");
				pageSql.append(page.getShowCount()+page.getCurrentResult());
			}
			return pageSql.toString();
		}else{
			return sql;
		}
	}
	
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (Tools.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (Tools.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
