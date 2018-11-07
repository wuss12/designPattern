**单例模式**
**定义：** 确保一个类只有一个实例，并且自行实例化，并像整个系统提供该实例

**类型：** 创建类模型

**类图：**

![Image text](https://github.com/wuss12/designPattern/blob/master/java/singleton/img/singleton.gif)


单例模式有以下几个要点：
>*  私有的构造方法
>*  指向自己实例的私有静态引用
>*  以自己实例为返回值的静态的公有的方法

>----

单例模式 根据初始化的时机不同，分为 饿汉式单例 和 懒汉式单例。饿汉式单例，
顾名思义，就是尽量早的初始化对自身引用的实例。因此饿汉模式的单例在
单例类被加载的时候，就初始化一个对象，交给自己的引用。而懒汉模式
在调用取得实例化方法的时候才会实例化对象
    
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

上面的例子中，类EagerSingleton被加载的时候，初始化 instance变量，
会调用它的私有构造函数，产生出单例类的唯一对象。

**饿汉模式是典型的以空间换时间** 当类被加载的时候就会创建实例，无论你
是否使用，先创建出来，然后每次使用都不用在判断，节省了运行的时间

**懒汉模式：**
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
懒汉模式，懒汉顾名思义，很懒，在需要使用的时候才会创建
**懒汉模式是典型的时间换空间** ，每次使用的时候都会判断该实例是否存在
浪费判断的时间，如果一直没有人使用，则不会创造实例，节省了空间。

尽管懒汉模式是安全的，但是每一次使用都需要判断，很浪费时间，有没有更加
优化的解决方案呢

**双重检查锁**

双重检查锁：并不是每一次进入getinstance 都需要同步，而是先判断该对象是否
存在，如果不存在才进入下面的同步块

双重锁机制 会使用关键字：volatile，它的意思是被 volatile 修饰的变量
的值不会被 本地线程缓存，所有对该变量的读写都是直接操作共享内存，
从而保证多线程正确的处理该变量

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

这种实现方式既能保证线程安全，有对性能不造成很大影响，它只会在第一次初始化的
时候，需要同步，从而加快了运行速度。

  提示：volition 关键字可能会会屏蔽掉jvm中一些必要的优化代码，所以运行效率
并不是很高，因此没有特别的需要不要使用

**Lazy initialization holder class模式**

这种方式综合的使用了类级内部类  和 多线程缺省 同步锁的知识：
>* 什么是类级内部类
简单的说，类级内部类，就是用static 修饰的内部类，若没有被static修饰的
成员式内部类，叫做 对象及内部类

类级内部类，相当于外部类的static的成分，它的对象 和外部类对象不存在依赖
关系，因此可以直接创建，而对象级内部类 的实例是绑定在外部类实例中的

在类级内部类中可以定义静态方法，只能引用外部类的静态属性和方法

类级内部类是外部类的成员，因此只有在被使用的时候才会被装载

>* 多线程缺省同步锁

在开发中为了解决同步的问题我们会使用synchronized 添加互斥锁，从而来
控制同步，但是在某些时候，JVM已经隐含的为我们执行同步，这个时候就不需要
我们自己来同步，这些情况包括：
1. 静态初始化器(在静态字段上，或者static {}快中的初始化器) 初始化数据时
2.访问final 字段的时候
3.在创建线程之前创建对象时
4.线程可以看见它将要处理的对象时

解决思路：静态初始化器，它可以由jvm 保障线程的安全。比如前面的饿汉模式
但是，但是会浪费空间，因为不管你需不需要，类在装在的时候就会初始化对象


  如果有一种方式能够让类在初始化的时候不去装载对象，那么久解决了问题，一种
可行的方式是采用类级内部类，在这个类级内部类里面去创建对象的实例。这样一来
只要不使用类级内部类，那么就不会创建实例。从而实现了延迟加载和线程安全。

    public class SingletonH {
        private SingletonH(){}
        /**
         *    类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
         *    没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
         */
        private static class SingletonHolder{
            private static SingletonH instance = new SingletonH();
        }
        public static SingletonH getInstance(){
            return SingletonHolder.instance;
        }
    }
当getInstance方法第一次被调用的时候，他第一次读取SingletonHolder.instance;
导致 SingletonHolder 被初始化而这个类在装载并被初始化的时候，会初始化它的静态域，
从而创建Singleton的实例，由于是静态的域，因此只会在虚拟机装载类的时候
初始化一次，并由虚拟机来保证它的线程安全性。
                       
这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。

**单例和枚举**

单元素的枚举类型，已经是实现单例的最佳模式

**可能出现的问题以及改进**
1. 通过反射破坏单例
```public class SingletonDemo1 {
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
```
输出结果：
```
true
false
```
可以针对反射破坏单例做一定的调整，来组织反射获得(让直接 只调用构造函数 抛出运行时异常即可)
改进如下：
```public class SingletonDemo1Change {
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
```
当然使用反射 先修改 flag 在调用构造函数 也是能破坏该 单例，我们此时只讨论 只调用构造函数

2. 序列化和反序列化破坏单例

```public class SingletonDemo2 implements Serializable {
    private static SingletonDemo2 instance = new SingletonDemo2();
    private SingletonDemo2(){

    }
    public static SingletonDemo2 getInstance(){
        return instance;
    }

    public static void main(String[] args) throws Exception{
        SingletonDemo2 s1 = SingletonDemo2.getInstance();
        SingletonDemo2 s2 = SingletonDemo2.getInstance();
        System.out.println(s1 == s2);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(s1);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempFile"));
        SingletonDemo2 s3 = (SingletonDemo2) ois.readObject();
        System.out.println(s1 == s3);
    }
}```
结果是：
true
false
-----
我们可以通过重写 readResolve，来解决 序列化破坏单例
代码如下：

```public class SingletonDemo2Change implements Serializable{
    private static SingletonDemo2Change instance = new SingletonDemo2Change();
    private SingletonDemo2Change(){}
    public static SingletonDemo2Change getInstance(){
        return instance;
    }
    private Object readResolve(){
        return instance;
    }

    public static void main(String[] args) throws Exception{
        SingletonDemo2Change s1 = getInstance();
        SingletonDemo2Change s2 = getInstance();
        System.out.println(s1 == s2);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(s1);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempFile"));
        SingletonDemo2Change s3 = (SingletonDemo2Change) ois.readObject();
        ois.close();
        System.out.println(s1 == s3);
    }
}
```
结果如下:
ture
ture

可以参考
SerSingleton例子