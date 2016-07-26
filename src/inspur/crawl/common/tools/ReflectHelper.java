/**
 * 
 */
package inspur.crawl.common.tools;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ������
 *
 */
public class ReflectHelper {
	
	/**
	 * ��ȡobj����fieldName��Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		System.out.println("obj::"+obj.getClass()+"   fieldName::"+fieldName);
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * ��ȡobj����fieldName������ֵ
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
// TODO: Local variable could be declared final
		Field field = getFieldByFieldName(obj, fieldName);
		System.out.println("filed::::"+field);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
				System.out.println("value1:::"+value+"  "+obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				System.out.println("value2:::"+value+"  obj::"+obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * ����obj����fieldName������ֵ
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
// TODO: Local variable could be declared final
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	public static Object getProperty(Object obj,String methodName,Object args[])throws SecurityException, NoSuchFieldException,
	IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Class<?> owner = obj.getClass();
		Class<?> argsClass[]=null;
		if(args != null && args.length>0){
			argsClass=new Class[args.length];
			for(int i=0,j=args.length;i<j;i++){
				argsClass[i]=args[i].getClass();
			}
		}
		Method method = null;
		try {
			method = owner.getMethod(methodName, argsClass);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return method.invoke(obj, args);
	}
	
	public static void setProperty(String classname,String fieldName,Object value)throws SecurityException, NoSuchFieldException,
	IllegalArgumentException, IllegalAccessException{
		Class<?> owner = null;
		try {
			owner = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Field field=owner.getDeclaredField(fieldName);
		Object property=field.get(owner);
		property=value;
	}

	/** Default constructor */
	public ReflectHelper() {
	}

}
