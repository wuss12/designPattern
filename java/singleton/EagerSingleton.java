package singleton;

/**
 * 饿汉模式
 */
public class EagerSingleton {
    /**
     * 私有的构造函数
     */
    private EagerSingleton(){}


    private static EagerSingleton instance = new EagerSingleton();

    public static EagerSingleton getInstance(){
        return instance;
    }
}
