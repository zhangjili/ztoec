package com.jili.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.jpmml.model.StringUtil;

import java.util.HashMap;

public class GroovyClassLoaderTest {
    //第一种方式

    private static GroovyClassLoader groovyClassLoader = null;

    public static void initGroovyClassLoader() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        // 设置该GroovyClassLoader的父ClassLoader为当前线程的加载器(默认)
        groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
    }
    public static void main(String[] args) {
        try {
            initGroovyClassLoader();
            // 获得GroovyShell_2加载后的class
            Class<?> groovyClass = groovyClassLoader.parseClass(" static boolean check(def data, def target) {\n" +
                    "        print('>>>>>>>>>>>>>>>>>>>>>>>Groovy: data: ' + data.toString() + ' , target: ' + target + '\\n')\n" +
                    "        switch (data.RELATIONALOPERATOR) {\n" +
                    "            case '≤':\n" +
                    "                return (data.AMOUNTNUM as Long) >= (target as Long)\n" +
                    "                break\n" +
                    "            case '>':\n" +
                    "                return (data.AMOUNTNUM as Long) < (target as Long)\n" +
                    "                break\n" +
                    "            case '<':\n" +
                    "                return (data.AMOUNTNUM as Long) > (target as Long)\n" +
                    "                break\n" +
                    "            case '=':\n" +
                    "                return (data.AMOUNTNUM as Long) == (target as Long)\n" +
                    "                break\n" +
                    "            default:\n" +
                    "                return (data.AMOUNTNUM as Long) <= (target as Long)\n" +
                    "                break\n" +
                    "        }\n" +
                    "    }");

            HashMap<String, String> map = new HashMap<>();
            map.put("RELATIONALOPERATOR","≥");
            map.put("AMOUNTNUM","5");
            // 获得GroovyShell_2的实例
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            // 反射调用sayHello方法得到返回值
            Object methodResult = groovyObject.invokeMethod("check", new Object[] {map, 2});
            if (methodResult != null) {
                String result = methodResult.toString();
                System.out.println(result);
            }
        } catch (Exception e) {
            throw new RuntimeException("加载失败");
        }
    }
}
