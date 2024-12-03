public class Axe extends Equipment {
    public Axe(int id, String name, int durability, int ce) {
        super(id, name, durability, ce);
    }

    public int battle(Adventurer ad, Adventurer da) {
        int beforeBattle = da.getHitPoint();
        da.setHitPoint(da.getHitPoint() / 10);
        int afterBattle = da.getHitPoint();
        return beforeBattle - afterBattle;
    }
}
