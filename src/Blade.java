public class Blade extends Equipment {
    public Blade(int id, String name, int durability, int ce) {
        super(id, name, durability, ce);
    }

    public int battle(Adventurer ad, Adventurer da) {
        da.subHitPoint(this.getCe() + ad.getAtk());
        return this.getCe() + ad.getAtk();
    }
}
