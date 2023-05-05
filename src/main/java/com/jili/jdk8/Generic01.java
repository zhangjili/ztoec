package com.jili.jdk8;

class Generic01<T> {
    private T key;
 
    public Generic01(T key) {
        this.key = key;
    }
 
 
    /**
     * 1、这个虽然在方法中使用了泛型，但这并不是一个泛型方法。这只是类中一个普通的
     * 成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。所以在这个方法中才
     * 可以继续使用 T 这个泛型。
     */
    public T getKey() {
        return key;
    }
 
    /**
     * 1、这个方法显然是有问题的，在编译器会给我们提示这样的错误信息"cannot reslove symbol E"
     * 因为在类的声明中并未声明泛型E，所以在使用E做形参和返回值类型时，编译器会无法识别。
     */
//    public E setKey(E key) {
//        this.key = key;
//    }
 
}