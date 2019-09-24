package edu.utep.cs.cs4330.mypricewatcher;
import java.util.Random;

public class SimulatedBehavior implements PriceFindBehavior {
    @Override
    public Long findPrice(Item item) {
        Random r = new Random();
        Long val = (long) Math.floor(r.nextGaussian() * 100 + 500);

        if (!item.getInitialPriceSet()) {
            item.setInitialPrice(val);
            item.setInitialPriceSet(true);
        }

        return val;
    }
}
