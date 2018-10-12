package factory.factoryMethod;

import factory.Coffee;

public class FactoryMethodTest {
    private static   void show(Coffee[] coffees){
        for (Coffee coffee : coffees){
            System.out.println(coffee.getName());
        }
    }

    public static void main(String[] args) {
        AbstractCoffeeFactory chinaFactory = new ChinaCoffeFactory();
        Coffee[] chinaCoffee = chinaFactory.createCoffee();
        System.out.println("China coffee factory can produce :");
        show(chinaCoffee);
        System.out.println("-------------------------------");

        AbstractCoffeeFactory ameriacFactory = new AmericaCoffeFectory();
        Coffee[] americaCoffee = ameriacFactory.createCoffee();
        System.out.println("America coffee factory can produce :");
        show(americaCoffee);
    }
}
