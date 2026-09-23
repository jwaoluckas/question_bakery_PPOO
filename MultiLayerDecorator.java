public class MultiLayerDecorator extends CakeDecorator {
    private static final int MULTI_LAYER_COST = 5;

    public MultiLayerDecorator(Cake wrappedCake) {
        super(wrappedCake);
    }

    @Override
    public int getCost() {
        return wrappedCake.getCost() + MULTI_LAYER_COST;
    }

    @Override
    public String getDescription() {
        return "Multi-layered " + wrappedCake.getDescription();
    }
}
