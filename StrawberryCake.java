public class StrawberryCake extends Cake {
    @Override
    public int getCost() {
        return super.getCost() * 2;
    }

    @Override
    public String getDescription() {
        return "Strawberry cake";
    }
}
