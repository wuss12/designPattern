# 代理
**代理：给某一个对象提供一个代理对象，并由代理对象控制对原对象的访问**

***
**为什么要是用代理？**

*中介隔离作用：在某些情况下，客户类，不想或者不能直接引用委托对象，而代理对象可以在客户类和
委托类间起到中介的作用，特征，就是代理类和委托类都实现同一个接口
*开闭原则，增加功能：代理类，除了是客户类和代理类的中介之外，我们还可以给代理类增加新的功能，来
扩展 委托类的功能。这样我们只要修改代理类，就能实现对委托类的增强，符合开闭原则，代理类本身并
不真正实现服务，而是调用委托类的相关方法，来实现特定的功能，真正的业务功能，还是由委托类完成


![Image text](https://github.com/wuss12/designPattern/blob/master/java/proxyPattern/img/%E4%B8%8B%E8%BD%BD.png)

**常见的代理模式**
    按照代理创建时间来划分，可以分为两种，静态代理、动态代理。静态代理是由程序员
程序员创建，或者有工具类生成源代码，在对其编译是在运行之前，代理类就已经生成了。
动态代理是在程序运行时，由程序动态生成的。

我们以买房子为例，虽然我们可以自己去找房子但是耗时耗力，我们可以找中介，最后，由
我们自己决定买什么    

## 1.静态代理
***
**第一步：创建服务类接口**
```
public interface BuyHouseService {
    void buyHouse();
}
```
***
**第二步：实现服务接口**
```
public class BuyHouseServiceImp implements BuyHouseService{
    @Override
    public void buyHouse() {
        System.out.println("I want to buy house.");
    }
}
```
***
**第三步：创建代理类**
```
public class BuyHouseProxy implements BuyHouseService {
    BuyHouseService buyHouseService;

    public BuyHouseProxy(BuyHouseService buyHouseService) {
        this.buyHouseService = buyHouseService;
    }

    @Override
    public void buyHouse() {
        before();
        buyHouseService.buyHouse();
        after();
    }
    public void before(){
        System.out.println("before buy house");
    }
    public void after(){
        System.out.println("after buy house");
    }
}

```
***
**第四步：编写测试类**
```
public class Test {
    public static void main(String[] args) {
        BuyHouseService buyHouse = new BuyHouseServiceImp();
        buyHouse.buyHouse();
        System.out.println("----------------------");
        BuyHouseService proxy = new BuyHouseProxy(buyHouse);
        proxy.buyHouse();
    }
}
```

**静态代理类总结**
- 优点：在符合开闭原则下，实现对功能的扩展
- 缺点：我们需要为每一个服务类提供一个代理类，工作量大，管理不方便，一旦接口发生
变化，需要修改对应的代理

## 2.动态代理

    在动态代理中，我们不用再手动编写代理类，只需要编写一个动态处理器即可，真正的
代理对象在运行时候，有jvm帮我们创建

**第一步：编写动态处理器**
---
```
public class DynamicProxyHandler implements InvocationHandler {
    private Object object;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(proxy,args);
        after();
        return result;
    }
    public void before(){
        System.out.println("before buy house");
    }
    public void after(){
        System.out.println("after buy house");
    }
}
```

**第二步：编写测试类**
```
public class Test {
    public static void main(String[] args) {
        BuyHouseService houseService = new BuyHouseServiceImp();
        BuyHouseService proxyHouse = (BuyHouseService) Proxy.newProxyInstance(Test.class.getClassLoader(),new Class[]{BuyHouseService.class},new DynamicProxyHandler(houseService));
        System.out.println("----------------------");
        proxyHouse.buyHouse();
    }
}

```
注意Proxy.newProxyInstance()方法接受三个参数：

- ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
- Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型
- InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法

**动态代理总结**
  相对于静态代理，我们的动态代理，减少了我们的工作量，减少了对业务接口的依赖，降低
了耦合，但是仍然拜托不了，紧支持 interface 代理的桎梏

## 3.cglib 代理
 JDK实现动态代理需要实现类通过接口定义业务方法，对于没有接口的类，如何实现动态代理呢，
 这就需要CGLib了。CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类
 创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。
 但因为采用的是继承，所以不能对final修饰的类进行代理。JDK动态代理与CGLib动态
 代理均是实现Spring AOP的基础
 
 **第一步：创建CGLIB代理类**
 ```
 public class CglibProxy implements MethodInterceptor {
     private Object target;
 
     public CglibProxy(Object target) {
         this.target = target;
     }
 
     //  //给目标对象创建一个代理对象
     public Object  getInstance(final Object target) {
         //1.工具类
         Enhancer en = new Enhancer();
         //2.设置父类
         en.setSuperclass(target.getClass());
         //3.设置回调函数
         en.setCallback(this);
         //4.创建子类(代理对象)
         return en.create();
     }
     @Override
     public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
         before();
         Object result = method.invoke(target,objects);
         after();
         return result;
     }
     public void before(){
         System.out.println("before buy house");
     }
     public void after(){
         System.out.println("after buy house");
     }
 }
 ```
 **第二步：创建测试类**
  ```
  public class Test {
      public static void main(String[] args) {
          BuyHouseService buyHouse = new BuyHouseServiceImp();
          CglibProxy cglibProxy = new CglibProxy(buyHouse);
          BuyHouseService buyHouseCglibProxy = (BuyHouseService) cglibProxy.getInstance();
  
          buyHouseCglibProxy.buyHouse();
      }
  }
   ```
   
   CGLIB代理总结： CGLIB创建的动态代理对象比JDK创建的动态代理对象的性能更高，但是CGLIB创建代理对象时所花费的时间却比JDK多得多。所以对于单例的对象，因为无需频繁创建对象，用CGLIB合适，反之使用JDK方式要更为合适一些。同时由于CGLib由于是采用动态创建子类的方法，对于final修饰的方法无法进行代理。