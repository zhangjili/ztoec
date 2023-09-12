package com.jili.fanxing;


public class GenericMethod {
    public static void main(String[] args) {
        Double[] num = {1.11, 2.22, 3.33, 4.44, 5.55, 6.66};
        String[] str = {"Hello", "World", "你好", "世界"};
 
        Generic01 generic01 = new Generic01();
        System.out.println(generic01.toGeneric01(num));
        System.out.println(generic01.toGeneric01(str));


        Generic02<Double> doubleGeneric02 = new Generic02<>();
        System.out.println(doubleGeneric02.toGeneric02(num));
        Generic02<String> doubleGeneric03 = new Generic02<>();
        System.out.println(doubleGeneric03.toGeneric02(str));


        Generic03 generic03 = new Generic03();
        generic03.toGeneric03(num);
    }
}
 
class Generic01 {
    public <T> T toGeneric01(T[] arr) {
        return arr[arr.length - 1];
    }
}
 
class Generic02<T> {
    public T toGeneric02(T[] arr) {
        return arr[arr.length - 1];
    }
}

// public <T> 中的这个 T 是修饰符的功能，代表是个泛型方法，与static修饰静态方法一个意思，但是，这里的<T>并不是返回值类型，
// 而是表示这里传入的参数可以是泛型或者下面定义的变量可以是泛型，<T>在这里的目的是为了确保参数中可以出现T这种数据类型或者方法中定义的变量可以是T这种数据类型。
class Generic03 {
    public <T> void toGeneric03(T[] arr) {
        T t = arr[arr.length - 1];
    }
}

/*
上面的样例测试中，我分别编写了两个类 Generic01 和  Generic02。Generic01类 编写的方法的返回值样式为
<T> T，Generic02类 编写的方法返回值的样式为 T。在编写的时候发现，如果方法的返回值写成 T，那么这个类必须为泛型，简单来说就是 Generic02类 后面必须加上 <T>，否则会报错！
Generic02类加上<T>后，那么我们在通过此类调用方法时，就必须传入类型，比如Integer、Double、String、我们自定义的类等等一些类，而方法返回为 <T> T 的 Generic01类 则无需知道类型，传入即可~
*/
