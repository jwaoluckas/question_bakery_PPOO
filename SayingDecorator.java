/**
 * Adds a custom saying to a cake's description. Does not change the cost.
 * Can be stacked more than once on the same cake to add multiple sayings.
 */
public class SayingDecorator extends CakeDecorator {
    private final String saying;

    public SayingDecorator(Cake wrappedCake, String saying) {
        super(wrappedCake);
        this.saying = saying;
    }

    @Override
    public String getDescription() {
        return wrappedCake.getDescription() + " with saying \"" + saying + "\"";
    }
}
