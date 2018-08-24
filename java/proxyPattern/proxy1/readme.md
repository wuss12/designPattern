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