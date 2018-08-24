package proxyPattern.proxy1;

public class Test {
    public static void main(String[] args) {
        BuyHouseService buyHouse = new BuyHouseServiceImp();
        buyHouse.buyHouse();
        System.out.println("----------------------");
        BuyHouseService proxy = new BuyHouseProxy(buyHouse);
        proxy.buyHouse();
    }
}
