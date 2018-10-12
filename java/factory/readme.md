**JAVA设计模式之工厂模式—Factory Pattern**

**1.工厂模式简介：**
    工厂模式用于对象的创建，使得客户端从具体的产品对象中解耦
    
**2.工厂模式分类：**    
    我们以咖啡为例：我们吧咖啡作为一种总称，具体可分为：美式咖啡、卡布奇诺、拿铁等等
    我们不使用工厂模式之前代码如下：
    
    
**2.1 简单工厂**    
    简单工厂其实不能算作是一种设计模式，它引入了创建者的概念，将实例化的代码从应用程序中剥离。在
创建者类的静态方法中，只处理创建对象的细节，后续创建实例的需求如需改变，只需要修改创建者类即可。

```public abstract class Coffee {
    /**
     * 获取coffee名称
     * @return
     */
    public abstract String getName();
}

public class Americano extends Coffee {
    @Override
    public String getName() {
        return "美式咖啡";
    }
}

public class Cappuccino extends Coffee {
    @Override
    public String getName() {
        return "卡布奇诺";
    }
}

public class Latte extends Coffee {
    @Override
    public String getName() {
        return "拿铁";
    }
}


package factory.simpleFactory;

public class SimpleFactory {
    public static Coffee instanceCoffice(String type){
        if("americano".equalsIgnoreCase(type)){
            return new Americano();
        }
        if("cappuccino".equalsIgnoreCase(type)){
            return new Cappuccino();
        }
        if("latte".equalsIgnoreCase(type)){
            return new Latte();
        }
        throw new RuntimeException("type["+type+"]类型不可识别，没有匹配到可实例化的对象！");
    }

    public static void main(String[] args) {
        Coffee americano = instanceCoffice("americano");
        System.out.println("coffee instance is : "+americano.getName());
        split();
        Coffee cappuccino = instanceCoffice("cappuccino");
        System.out.println("coffee instance is : "+cappuccino.getName());
        split();
        Coffee latte = instanceCoffice("latte");
        System.out.println("coffee instance is : "+latte.getName());
        split();
    }

    private static void split() {
        System.out.println("----------------------------------------");
    }

}
```