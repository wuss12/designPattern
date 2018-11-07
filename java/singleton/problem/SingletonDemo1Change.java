package singleton.problem;

import java.lang.reflect.Constructor;

public class SingletonDemo1Change {
    private static volatile boolean flag = true;
    private static SingletonDemo1Change instance = new SingletonDemo1Change();
    private SingletonDemo1Change() {
        if (flag ) {
            flag = false;
        } else {
            throw new RuntimeException("反射正则攻击单例:SingletonDemo1Change");
        }
    }

    public static SingletonDemo1Change getInstance() {
        return instance;
    }

    public static void main(String[] args) throws Exception {
        SingletonDemo1Change s1 = SingletonDemo1Change.getInstance();
        SingletonDemo1Change s2 = SingletonDemo1Change.getInstance();
        System.out.println(s1 == s2);

        Class clazz = SingletonDemo1Change.class;
        Constructor declaredConstructor = clazz.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        SingletonDemo1Change s3 = (SingletonDemo1Change) declaredConstructor.newInstance(null);
        System.out.println(s3 == s1);
    }
}
