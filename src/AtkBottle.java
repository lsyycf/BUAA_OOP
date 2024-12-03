public class AtkBottle extends Bottle {

    public AtkBottle(int id, String name, int capacity, int ce) {
        super(id, name, capacity, ce);
    }

    public void use(Adventurer ad) {
        ad.addAtk(this.getCe() + this.getCapacity() / 100);
        ad.setCe();
        setIsEmpty(true);
    }
}
