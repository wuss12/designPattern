package singleton;

public class VolatileSingleton {
    private static volatile VolatileSingleton instance = null;

    private VolatileSingleton(){

    }

    public static VolatileSingleton getInstance() {
        if(instance != null){
            return instance;
        }
        synchronized (VolatileSingleton.class){
            if(instance == null){
                instance = new VolatileSingleton();
            }
        }
        return instance;
    }
}
