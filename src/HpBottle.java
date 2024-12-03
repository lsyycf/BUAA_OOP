public class HpBottle extends Bottle {

    public HpBottle(int id, String name, int capacity, int ce) {
        super(id, name, capacity, ce);
    }

    public void use(Adventurer ad) {
        ad.addHitPoint(this.getCapacity());
        setIsEmpty(true);
    }
}
