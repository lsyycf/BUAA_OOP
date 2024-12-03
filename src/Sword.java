public class Sword extends Equipment {
    public Sword(int id, String name, int durability, int ce) {
        super(id, name, durability, ce);
    }

    public int battle(Adventurer ad, Adventurer da) {
        da.subHitPoint(this.getCe() + ad.getAtk() - da.getDef());
        return this.getCe() + ad.getAtk() - da.getDef();
    }
}
