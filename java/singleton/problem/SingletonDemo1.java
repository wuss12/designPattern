package singleton.problem;

import java.lang.reflect.Constructor;

public class SingletonDemo1 {
    private static SingletonDemo1 instance = new SingletonDemo1();
    private SingletonDemo1(){

    }
    public static SingletonDemo1 getInstance(){
        return instance;
    }

    public static void main(String[] args) throws Exception{
        SingletonDemo1 s1 = SingletonDemo1.getInstance();
        SingletonDemo1 s2 = SingletonDemo1.getInstance();
        System.out.println(s1 == s2);

        Class clazz = SingletonDemo1.class;
        Constructor declaredConstructor = clazz.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        SingletonDemo1 s3 = (SingletonDemo1) declaredConstructor.newInstance(null);
        System.out.println(s3 == s1);
    }
}
