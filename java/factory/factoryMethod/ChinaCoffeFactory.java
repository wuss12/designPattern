package factory.factoryMethod;

import factory.Cappuccino;
import factory.Coffee;
import factory.Latte;

public class ChinaCoffeFactory extends AbstractCoffeeFactory {
    @Override
    public Coffee[] createCoffee() {
        return new Coffee[]{new Cappuccino(),new Latte()};
    }
}
