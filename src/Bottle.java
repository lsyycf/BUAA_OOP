public class Bottle {
    private final int id;
    private final String name;
    private final int capacity;
    private final int ce;
    private boolean isEmpty;

    public int getCe() {
        return ce;
    }

    public Bottle(int id, String name, int capacity, int ce) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.ce = ce;
        this.isEmpty = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean getIsEmpty() { return isEmpty; }

    public void setIsEmpty(boolean empty) { isEmpty = empty; }
}
