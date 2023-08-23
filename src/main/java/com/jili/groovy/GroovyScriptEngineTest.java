package com.jili.groovy;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

public class GroovyScriptEngineTest {
    public static void main(String[] args) throws IOException, ScriptException, ResourceException {
        // GroovyScriptEngine的根路径，如果参数是字符串数组，说明有多个根路径
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java/com/juxinli/groovy/shell/");
        //GroovyScriptEngine engine = new GroovyScriptEngine(new String[] {"src/main/java/com/juxinli/groovy/shell/"});

        Binding binding = new Binding();
        binding.setVariable("name", "juxinli");

        Object result1 = engine.run("GroovyShell_3_1.groovy", binding);
        System.out.println(result1);
        Object result2 = engine.run("GroovyShell_3_2.groovy", binding);
        System.out.println(result2);
    }
}
