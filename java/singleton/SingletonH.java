package singleton;

public class SingletonH {
    private SingletonH(){
        System.out.println("SingletonH 被初始化");
    }
    /**
     *    类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     *    没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class SingletonHolder{
        public SingletonHolder(){
            System.out.println("SingletonHolder 被初始化");
        }
        private static SingletonH instance = new SingletonH();
    }
    public static SingletonH getInstance(){
        return SingletonHolder.instance;
    }
}
