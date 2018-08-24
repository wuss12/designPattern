package proxyPattern.proxy3;

import proxyPattern.proxy1.BuyHouseService;
import proxyPattern.proxy1.BuyHouseServiceImp;

public class Test {
    public static void main(String[] args) {
        BuyHouseService buyHouse = new BuyHouseServiceImp();
        CglibProxy cglibProxy = new CglibProxy(buyHouse);
        BuyHouseService buyHouseCglibProxy = (BuyHouseService) cglibProxy.getInstance();

        buyHouseCglibProxy.buyHouse();
    }
}
