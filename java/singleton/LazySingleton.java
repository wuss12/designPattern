package singleton;

public class LazySingleton {
    private LazySingleton(){
    }

    private static LazySingleton instance = null;
    public static synchronized LazySingleton getInstance(){
        if(instance != null){
            return instance;
        }
        instance = new LazySingleton();
        return instance;
    }
}
