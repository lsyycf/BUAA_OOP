public class DefBottle extends Bottle {

    public DefBottle(int id, String name, int capacity, int ce) {
        super(id, name, capacity, ce);
    }

    public void use(Adventurer ad) {
        ad.addDef(this.getCe() + this.getCapacity() / 100);
        ad.setCe();
        setIsEmpty(true);
    }
}
