public class SprinklesDecorator extends CakeDecorator {
    private static final int SPRINKLES_COST = 2;

    public SprinklesDecorator(Cake wrappedCake) {
        super(wrappedCake);
    }

    @Override
    public int getCost() {
        return wrappedCake.getCost() + SPRINKLES_COST;
    }

    @Override
    public String getDescription() {
        return wrappedCake.getDescription() + " with sprinkles";
    }
}
