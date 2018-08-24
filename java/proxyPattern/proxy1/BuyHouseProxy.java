package proxyPattern.proxy1;

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
