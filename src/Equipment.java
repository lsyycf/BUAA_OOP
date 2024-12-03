public class Equipment {
    private final int id;
    private final String name;
    private int durability;
    private final int ce;

    public Equipment(int id, String name, int durability, int ce) {
        this.id = id;
        this.name = name;
        this.durability = durability;
        this.ce = ce;
    }

    public int getCe() {
        return this.ce;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDurability() {
        return durability;
    }

    public void addDurability(int addNum) {
        this.durability = durability + addNum;
    }

    public void subDurability(int subNum) {
        this.durability = durability - subNum;
    }
}
