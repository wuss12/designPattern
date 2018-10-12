package factory.factoryMethod;

import factory.Americano;
import factory.Cappuccino;
import factory.Coffee;

public class AmericaCoffeFectory extends AbstractCoffeeFactory {
    @Override
    public Coffee[] createCoffee() {
        return new Coffee[]{new Americano(),new Cappuccino()};
    }
}
