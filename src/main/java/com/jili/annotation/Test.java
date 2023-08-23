package com.jili.annotation;
 
import java.lang.annotation.Annotation;
 
public class Test {

    //当以后我们在定义一个作用于类的注解时候，如果希望该注解也作用于其子类，那么可以用@Inherited 来进行修饰。
    public static void main(String[] args) {
        // 打印父类注解信息
        Annotation[] fatherAnnotations = Father.class.getAnnotations();
        System.out.println("------- 父类 Father 信息 --------");
        System.out.println("父类注解个数：" + fatherAnnotations.length);
        for (Annotation fa : fatherAnnotations) {
            System.out.println(fa.annotationType().getSimpleName());
        }
        // 打印子类注解信息
        Annotation[] childAnnotations = Child.class.getAnnotations();
        System.out.println("------- 子类 Child 信息 --------");
        System.out.println("子类注解个数：" + childAnnotations.length);
        for (Annotation ca: childAnnotations) {
            System.out.println(ca.annotationType().getSimpleName());
        }
    }
 
}