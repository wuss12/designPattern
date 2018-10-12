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
