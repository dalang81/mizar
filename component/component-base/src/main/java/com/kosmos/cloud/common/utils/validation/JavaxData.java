package com.kosmos.cloud.common.utils.validation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;

/**
 * javax 注解验证帮助类
 * @author kaka q+
 *
 */
public class JavaxData {
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	 
	/**
	 * javax验证注解
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String validate(Object obj) {
		if(obj instanceof List){
			List<Object> objList = (List)obj;
			for(Object obj1 : objList){
     		   //验证对象的属性
     		   String result = validate(obj1);
     		   if(StringUtils.isNotEmpty(result)){
     			   return result;
     		   }
     	   }
		}else{
			String result = singleValidate(obj);
			if(StringUtils.isNotEmpty(result)){
  			   return result;
  		   }
		}
		
	   	return null;
	}
	
	public static String singleValidate(Object o){
		Validator validator = factory.getValidator();
		//验证当前对象
	   	Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);
	   	for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
	   		return constraintViolation.getMessage();
	   	}
	   	//验证对象的属性
	   	String result = method(o);
	   	if(StringUtils.isNotEmpty(result)){
	   		return result;
	   	}
	   	return null;
	}
	
	/** 
     * 验证对象属性 
     * @param obj 目标对象 
     */  
    @SuppressWarnings({ "rawtypes"})
	public static String method(Object obj){  
       try{  
           Class clazz = obj.getClass();  
           Field[] fields = obj.getClass().getDeclaredFields();//获得属性  
           for (Field field : fields) {  
               PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);  
               Method getMethod = pd.getReadMethod();//获得get方法  
               Object o = getMethod.invoke(obj);//执行get方法返回一个Object  
               if(o == null){
            	   continue;
	       		}
               if(o instanceof Integer || o instanceof String || o instanceof Double || o instanceof Float
        		   || o instanceof Long || o instanceof Boolean || o instanceof Date || o instanceof Byte ||
        		   	o instanceof Short || o instanceof Character){
            	   continue;
               }
               
               String result = validate(o);
    		   if(StringUtils.isNotEmpty(result)){
    			   return result;
    		   }
           }  
       }catch (Exception e) {  
           e.printStackTrace();  
       }   
       return null;
    }  
}
