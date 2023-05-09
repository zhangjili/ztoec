/**   
 * Copyright © 2017 bailianpay.com All rights reserved.
 * 
 * @Title: Util.java 
 * @Prject: pay_risk_api_1.0
 * @Package: com.zrj.pay.risk.common.util 
 * @Description: TODO
 * @author: ningbin   
 * @date: 2017年12月7日 下午5:22:52 
 * @version: V1.0   
 */
package com.jili.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cglib.beans.BeanMap;

/**
 * @ClassName: Util
 * @Description: 工具类
 * @author: ningbin
 * @date: 2017年12月7日 下午5:22:52
 */
public class BeanUtil {

    /**
     * @Title: beanToMap
     * @Description: Map to Bean
     * @param bean
     * @throws Exception
     * @return: Map<String,Object>
     */
    public static <T> Map<String, Object> beanToMap(T bean) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == bean) {
            throw new RuntimeException("BeanToMap error : Bean null");
        }
        try {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        } catch (Exception e) {
            throw e;
        }
        return map;
    }
    
    public static <T> Map<String, Object> beanToMap(T bean, Boolean upperCase) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == bean) {
            throw new RuntimeException("BeanToMap error : Bean null");
        }
        try {
        	Field fields[] = bean.getClass().getDeclaredFields();
        	for(Field field : fields) {
        		String name = field.getName();
        		if(name.equals("serialVersionUID")) {
        			continue;
        		}
        		field.setAccessible(true);
        		Object value = field.get(bean);
        		if(upperCase) {
        			name = name.toUpperCase();
        		}
        		map.put(name, value);
        	}
        } catch (Exception e) {
            throw e;
        }
        return map;
    }
    
    public static <T> Map<String, String> beanToStringMap(T bean) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        if (null == bean) {
            throw new RuntimeException("BeanToMap error : Bean null");
        }
        try {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
            	String val = "";
            	if(null != beanMap.get(key)) {
            		val = beanMap.get(key).toString();
            	}
                map.put(key + "", val);
            }
        } catch (Exception e) {
            throw e;
        }
        return map;
    }

    /**
     * @Title: mapToBean
     * @Description: Bean to Map
     * @param map
     * @param bean
     * @return: T
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) throws Exception{
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
    
    public static <T> T mapToBean(Map<String, Object> map, T bean, boolean upperCase) throws Exception {
    	try {
    		Map<String, Object> temp = map;
    		if(upperCase) {
    			temp = new HashMap<>();
    			for(String key : map.keySet()) {
    				Object val = map.get(key);
    				temp.put(key.toUpperCase(), val);
    			}
    		}
	    	Field fields[] = bean.getClass().getDeclaredFields();
	    	for(Field field : fields) {
	    		String name = field.getName();
	    		if(name.equals("serialVersionUID")) {
        			continue;
        		}
	    		field.setAccessible(true);
	    		String mapKey = name;
	    		if(upperCase) {
	    			mapKey = mapKey.toUpperCase();
	    			name = name.toUpperCase();
	    		}
	    		if(name.equals(mapKey)) {
	    			field.set(bean, temp.get(mapKey));
	    		}
	    	}
    	}catch(Exception e) {
    		throw e;
    	}
    	return bean;
    }
    
    public static <T> T stringMapToBean(Map<String, String> map, T bean) throws Exception {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

}
