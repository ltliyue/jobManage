package inspur.crawl.common.generator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class MyPlus extends PluginAdapter implements Plugin {
	static Logger logger = LoggerFactory.getLogger(MyPlus.class);
	
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		initDatabaseTools(introspectedTable);
		moveXml(introspectedTable);
		super.initialized(introspectedTable);
	}

	/**
	 * 初始化数据库附加工具 TODO:未完成
	 */
	private void initDatabaseTools(IntrospectedTable introspectedTable) {
		JDBCConnectionConfiguration config = context.getJdbcConnectionConfiguration();
		DataBaseTools.getInstanc(config.getDriverClass(), config.getConnectionURL(), config.getUserId(), config.getPassword());
//		jdbcConfig.get
	}

	/**
	 * 移动、备份xml配置文件
	 * @param introspectedTable
	 */
	private void moveXml(IntrospectedTable introspectedTable) {
		
		SqlMapGeneratorConfiguration sqlmapConfig = context.getSqlMapGeneratorConfiguration();
		String dir = sqlmapConfig.getTargetProject() + "\\" + 
			sqlmapConfig.getTargetPackage().replaceAll("\\.", "\\\\");
		final String fileName = StringUtil.splitLast(introspectedTable.getExampleType(), "\\.").replaceAll("Example", "Mapper") + ".xml";
		File file = new File(dir + "\\" + fileName);
		if (file.isFile()) {
			File dest = new File(dir + "\\_" + fileName + "." + new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()) + ".bak");
			file.renameTo(dest);
		}
	}

	/**
	 *
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
		Method methodCopy = new Method();
		methodCopy.setVisibility(JavaVisibility.PUBLIC);
		methodCopy.addJavaDocLine("/** ");
		methodCopy.addJavaDocLine(" * 拷贝，将对象中的字段全部拷贝到子对象中");
		methodCopy.addJavaDocLine(" * @param bean 接收对象的子类");
		methodCopy.addJavaDocLine(" * @return 拷贝完成后的子类");
		methodCopy.addJavaDocLine(" */ ");
		methodCopy.setName("<T extends " + topLevelClass.getType().getShortName() + "> T copy");
		methodCopy.setReturnType(new FullyQualifiedJavaType(""));
		methodCopy.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "bean"));
		
		for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
			String columnName = column.getJavaProperty();
			String methodName = Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
			methodCopy.addBodyLine("bean.set" + methodName + "(get" + methodName + "());");
		}
		methodCopy.addBodyLine("return bean;");
		topLevelClass.addMethod(methodCopy);
		
		
		Method methodToString = new Method();
		methodToString.setVisibility(JavaVisibility.PUBLIC);
		methodToString.addJavaDocLine("/** ");
		methodToString.addJavaDocLine(" * 格式化显示");
		methodToString.addJavaDocLine(" */ ");
		methodToString.addAnnotation("@Override");
		methodToString.setName("toString");
		methodToString.setReturnType(FullyQualifiedJavaType.getStringInstance());
//		    	return "year:" + getYear() + ", month:" + getMonth(); 
		methodToString.addBodyLine("return \"{\" + ");
		for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
			String columnName = column.getJavaProperty();
			String methodName = Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
			methodToString.addBodyLine("	\", " + columnName + ":\" + get" + methodName + "() + ");
		}
		methodToString.addBodyLine("\"}\";");
		topLevelClass.addMethod(methodToString);
		
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	/**
	 * 字段修改，添加注释
	 */
	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
//		field.addJavaDocLine("add field doc");
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	}
	
    /**
     * 修改 查询结果对象映射，支持自定义结果对象
     */
	@Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    	String resultClass = introspectedTable.getTableConfiguration().getProperty("resultClass");
    	if(!StringUtil.isEmpty(resultClass)) {
    		final List<Attribute> list = element.getAttributes();
    		for (int i = 0; i < list.size(); i++) {
    			if(list.get(i).getName().equals("type")) {
    				list.set(i, new Attribute("type", resultClass));
    				break;
    			}
    		}
    	}
        return true;
    }
	
    /**
     * 修改client接口，支持自定义结果对象
     */
	@Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
    	String resultClass = introspectedTable.getTableConfiguration().getProperty("resultClass");
    	if(!StringUtil.isEmpty(resultClass)) {
    		interfaze.addImportedType(new FullyQualifiedJavaType(resultClass));
    		method.setReturnType(new FullyQualifiedJavaType("List<" + resultClass + ">"));
    	}
        return true;
    }

	/**
	 * 修改client接口，支持自定义结果对象
	 */
	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		String resultClass = introspectedTable.getTableConfiguration().getProperty("resultClass");
		if(!StringUtil.isEmpty(resultClass)) {
			interfaze.addImportedType(new FullyQualifiedJavaType(resultClass));
			method.setReturnType(new FullyQualifiedJavaType(resultClass));
		}
		return true;
	}
	

	
    /**
     * 修改 select 查询xml 对象， 添加自动分页功能
     */
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated( XmlElement element, IntrospectedTable introspectedTable) {
		List<Element> subEl = element.getElements();
		subEl.clear();
		
		// 当skipCount > 0 时
		{
			XmlElement skipGreater0 = new XmlElement("if");
			subEl.add(skipGreater0);
			skipGreater0.addAttribute(new Attribute("test", "skipCount > 0"));
			skipGreater0.addElement(new TextElement(" select * FROM ( SELECT temp_table.*,ROWNUM rn from ( select "));
			{
				XmlElement childEl = new XmlElement("include");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("refid", "Base_Column_List"));
			}
			
			skipGreater0.addElement(new TextElement("from " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));
			
			{
				XmlElement childEl = new XmlElement("if");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "_parameter != null"));
				XmlElement childchildEl = new XmlElement("include");
				childEl.addElement(childchildEl);
				childchildEl.addAttribute(new Attribute("refid", "Example_Where_Clause"));
			}
			
			{
				XmlElement childEl = new XmlElement("if");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "orderByClause != null"));
				childEl.addElement(new TextElement("order by ${orderByClause}"));
			}
			
			skipGreater0.addElement(new TextElement(") temp_table "));
			
			{
				XmlElement childEl = new XmlElement("if");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "top >= 0"));
				childEl.addElement(new TextElement("<![CDATA[ where rownum <= ${top} ]]>"));
			}
			
			skipGreater0.addElement(new TextElement(" ) where rn>=${skipCount}"));

		}
		
		// 当skipCount == 0 时
		{
			XmlElement skipEquals0 = new XmlElement("if");
			subEl.add(skipEquals0);
			skipEquals0.addAttribute(new Attribute("test", "skipCount == 0"));
			
			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "top >= 0"));
				childEl.addElement(new TextElement("SELECT temp_table.*,ROWNUM rn from ( "));
			}
			
			skipEquals0.addElement(new TextElement("select"));
			
			{
				XmlElement childEl = new XmlElement("include");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("refid", "Base_Column_List"));
			}
			
			skipEquals0.addElement(new TextElement("from " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));
			
			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "_parameter != null"));
				XmlElement childchildEl = new XmlElement("include");
				childEl.addElement(childchildEl);
				childchildEl.addAttribute(new Attribute("refid", "Example_Where_Clause"));
			}
			
			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "orderByClause != null"));
				childEl.addElement(new TextElement("order by ${orderByClause}"));
			}
			
			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "top >= 0"));
				childEl.addElement(new TextElement(" ) temp_table <![CDATA[ where rownum <= ${top} ]]>"));
			}
		}
		return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /** example 中添加分页信息 */
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		addLimit(topLevelClass, introspectedTable, "top");
		
		// 添加 忽略条数 的设置
		String name = "skipCount";
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(FullyQualifiedJavaType.getIntInstance());
		field.setName(name);
		field.setInitializationString("0");
		commentGenerator.addFieldComment(field, introspectedTable);
		
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * 设置查询数据库时掠过前面多少条记录");
		method.addJavaDocLine(" * @param skipCount 忽略前面记录的数量");
//		method.addJavaDocLine(" * @param orderByClause 排序方式，必填");
		method.addJavaDocLine(" */");
//		method.setName("set" + camel + "andOrderByClause");
		method.setName("set" + camel);
		method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
		method.addBodyLine("this." + name + " = " + name + ";");
//		method.addBodyLine("this." + orderByClause + " = " + orderByClause + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		
		
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("getEndCount");
		method.addBodyLine("return skipCount + top;");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(FullyQualifiedJavaType.getIntInstance());
		field.setName(name);
		field.setInitializationString("-1");
		commentGenerator.addFieldComment(field, introspectedTable);
		
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}
	
	

}
