package proxyPattern.proxy2;

import proxyPattern.proxy1.BuyHouseService;
import proxyPattern.proxy1.BuyHouseServiceImp;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        BuyHouseService houseService = new BuyHouseServiceImp();
        BuyHouseService proxyHouse = (BuyHouseService) Proxy.newProxyInstance(Test.class.getClassLoader(),new Class[]{BuyHouseService.class},new DynamicProxyHandler(houseService));
        System.out.println("----------------------");
        proxyHouse.buyHouse();
    }
}
