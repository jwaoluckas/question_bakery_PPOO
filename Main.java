/**
 * Create an order and print it
 */
public class Main {
    public static void main(String[] args) {
        // Create the order
        Order order = new Order();
        order.addCake(new ChocolateCake());
        order.addCake(new SayingDecorator(new VanillaCake(), "PLAIN!"));
        order.addCake(new SayingDecorator(new SprinklesDecorator(new VanillaCake()), "FANCY!"));
        order.addCake(new MultiLayerDecorator(
                new SayingDecorator(
                        new SayingDecorator(
                                new SprinklesDecorator(new SprinklesDecorator(new StrawberryCake())),
                                "One of"),
                        "EVERYTHING")));

        // Print the order
        order.printOrder();
    }
}
