/**
 * Base class for cake decorators. Wraps another Cake and by default
 * just delegates cost and description to it, so concrete decorators
 * only need to override what they actually change.
 */
public abstract class CakeDecorator extends Cake {
    protected final Cake wrappedCake;

    public CakeDecorator(Cake wrappedCake) {
        this.wrappedCake = wrappedCake;
    }

    @Override
    public int getCost() {
        return wrappedCake.getCost();
    }

    @Override
    public String getDescription() {
        return wrappedCake.getDescription();
    }
}
