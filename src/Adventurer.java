import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.Iterator;

public class Adventurer {
    private final int id;
    private final String name;
    private final HashMap<Integer, Bottle> bottleMap = new HashMap<>();
    private final HashMap<Integer, Equipment> equipmentMap = new HashMap<>();
    private final HashMap<String, ArrayList<Fragment>> fragmentMap = new HashMap<>();
    private final HashMap<Integer, Object> bagIdMap = new HashMap<>();
    private final HashMap<String, Equipment> equipmentBag = new HashMap<>();
    private final HashMap<String, HashMap<Integer, Bottle>> bottleBag = new HashMap<>();
    private final HashMap<Integer, Adventurer> employerMap = new HashMap<>();
    private final HashMap<Integer, Adventurer> chain = new HashMap<>();
    private final Queue<Adventurer> queue = new LinkedList<>();
    private final HashMap<Adventurer, Integer> employeeMap = new HashMap<>();
    private int hitPoint;
    private int atk;
    private int def;

    public String getName() {
        return name;
    }

    public void addAtk(int add) {
        this.atk = this.atk + add;
    }

    public void addHitPoint(int add) {
        this.hitPoint = this.hitPoint + add;
    }

    public void subHitPoint(int sub) {
        this.hitPoint = this.hitPoint - sub;
    }

    public void setHitPoint(int set) {
        this.hitPoint = set;
    }

    public void addDef(int add) {
        this.def = this.def + add;
    }

    public int setCe() {
        return this.atk + this.def;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public HashMap<Integer, Adventurer> getChain() {
        return chain;
    }

    public Bottle getBottle(int id) {
        return bottleMap.get(id);
    }

    public int getFragment(String name) {
        return this.fragmentMap.get(name).size();
    }

    public void addBottle(Bottle bottle) {
        this.bottleMap.put(bottle.getId(), bottle);
    }

    public void addEquipment(Equipment equipment) {
        this.equipmentMap.put(equipment.getId(), equipment);
    }

    public Equipment getEquipment(int equipmentId) {
        return this.equipmentMap.get(equipmentId);
    }

    public void employ(Adventurer adventurer) {
        this.employerMap.put(adventurer.id, adventurer);
        adventurer.employeeMap.put(this, 0);
    }

    public Equipment getEquipmentBag(String name) {
        return equipmentBag.get(name);
    }

    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
        this.hitPoint = 500;
        this.atk = 1;
        this.def = 0;
        setCe();
    }

    public Object deleteObject(int id) {
        Equipment eq = equipmentMap.remove(id);
        if (eq != null) {
            if (bagIdMap.containsKey(id)) {
                bagIdMap.remove(id);
                equipmentBag.remove(eq.getName());
            }
            return eq;
        }
        Bottle bt = bottleMap.remove(id);
        if (bagIdMap.containsKey(id)) {
            bagIdMap.remove(id);
            if (bottleBag.containsKey(bt.getName())) {
                bottleBag.get(bt.getName()).remove(id);
                if (bottleBag.get(bt.getName()).isEmpty()) {
                    bottleBag.remove(bt.getName());
                }
            }
        }
        return bt;
    }

    public void take(int id) {
        Bottle bt = bottleMap.get(id);
        if (bt != null) {
            if (bottleBag.containsKey(bt.getName())
                    && bottleBag.get(bt.getName()).size() < this.setCe() / 5 + 1) {
                bottleBag.get(bt.getName()).put(id, bt);
                bagIdMap.put(id, bt);
            } else if (!bottleBag.containsKey(bt.getName())) {
                HashMap<Integer, Bottle> map = new HashMap<>();
                map.put(bt.getId(), bt);
                bottleBag.put(bt.getName(), map);
                bagIdMap.put(id, bt);
            }
            return;
        }
        Equipment eq = equipmentMap.get(id);
        String equipmentName = eq.getName();
        if (equipmentBag.containsKey(equipmentName)) {
            Equipment qe = equipmentBag.remove(equipmentName);
            bagIdMap.remove(qe.getId());
        }
        equipmentBag.put(equipmentName, eq);
        bagIdMap.put(id, eq);
    }

    public boolean useBottle(int id) {
        Object bt = bagIdMap.get(id);
        if (bt == null || bt instanceof Equipment) {
            return false;
        }
        if (((Bottle) bt).getIsEmpty()) {
            deleteObject(id);
            return true;
        }
        if (bt instanceof HpBottle) {
            ((HpBottle) bt).use(this);
        } else if (bt instanceof AtkBottle) {
            ((AtkBottle) bt).use(this);
        } else if (bt instanceof DefBottle) {
            ((DefBottle) bt).use(this);
        }
        return true;
    }

    public int useFragment(String name, int id) {
        if (fragmentMap.get(name) != null) {
            if (fragmentMap.get(name).size() < 5) {
                return 0;
            }
            fragmentMap.get(name).subList(0, 5).clear();
            Bottle bt = bottleMap.get(id);
            if (bt != null) {
                bt.setIsEmpty(false);
                return 1;
            }
            Equipment eq = equipmentMap.get(id);
            if (eq != null) {
                eq.addDurability(1);
                return 2;
            }
            HpBottle hp = new HpBottle(id, name, 100, 0);
            this.addBottle(hp);
            return 3;
        }
        return 0;
    }

    public int useEquipment(String name, Adventurer da) {
        Equipment eq = this.equipmentBag.get(name);
        int loss = 0;
        if (eq instanceof Axe) {
            Axe axe = (Axe) eq;
            loss = axe.battle(this, da);
        } else if (eq instanceof Sword) {
            Sword sword = (Sword) eq;
            loss = sword.battle(this, da);
        } else if (eq instanceof Blade) {
            Blade blade = (Blade) eq;
            loss = blade.battle(this, da);
        }
        return loss;
    }

    public void addFragment(Fragment fragment) {
        if (fragmentMap.containsKey(fragment.getName())) {
            this.fragmentMap.get(fragment.getName()).add(fragment);
        } else {
            ArrayList<Fragment> list = new ArrayList<>();
            list.add(fragment);
            this.fragmentMap.put(fragment.getName(), list);
        }
    }

    public boolean battle(String name, int def) {
        Equipment eq = equipmentBag.get(name);
        if (eq == null) {
            return false;
        } else {
            return this.atk + eq.getCe() > def;
        }
    }

    public void addAll(int add, String op) {
        if (op.equals("Def")) {
            this.addDef(add);
            for (Map.Entry<Integer, Adventurer> entry : employerMap.entrySet()) {
                entry.getValue().addDef(add);
            }
            return;
        }
        this.addAtk(add);
        for (Map.Entry<Integer, Adventurer> entry : employerMap.entrySet()) {
            entry.getValue().addAtk(add);
        }
    }

    public void challenge() {
        if (this.getCom() > 1000) {
            System.out.println("Cloak of Shadows");
            this.addAll(40, "Def");
        }
        if (this.getCom() > 2000) {
            System.out.println("Flamebrand Sword");
            this.addAll(40, "Atk");
        }
        if (this.getCom() > 3000) {
            System.out.println("Stoneheart Amulet");
            this.addAll(40, "Def");
        }
        if (this.getCom() > 4000) {
            System.out.println("Windrunner Boots");
            this.addAll(30, "Def");
        }
        if (this.getCom() > 5000) {
            System.out.println("Frostbite Staff");
            this.addAll(50, "Atk");
        }
    }

    public int getTotal() {
        int total = 0;
        this.chain.clear();
        queue.offer(this);
        for (int i = 0; i < 5; i++) {
            int capacity = queue.size();
            for (int j = 0; j < capacity; j++) {
                Adventurer ad = queue.poll();
                if (ad != null) {
                    this.chain.put(ad.id, ad);
                    total = Math.max(total, ad.def);
                    for (Map.Entry<Integer, Adventurer> entry : ad.employerMap.entrySet()) {
                        queue.offer(entry.getValue());
                    }
                }
            }
        }
        queue.clear();
        return total;
    }

    public int getCom() {
        int ret = this.setCe();
        for (Map.Entry<Integer, Adventurer> entry : this.employerMap.entrySet()) {
            ret += entry.getValue().setCe();
        }
        for (Map.Entry<Integer, Object> entry : this.bagIdMap.entrySet()) {
            Object obj = entry.getValue();
            if (obj instanceof Bottle) {
                ret += ((Bottle) obj).getCe();
            } else if (obj instanceof Equipment) {
                ret += ((Equipment) obj).getCe();
            }
        }
        return ret;
    }

    public void help() {
        Iterator<Map.Entry<Integer, Adventurer>> empIt = this.employerMap.entrySet().iterator();
        while (empIt.hasNext()) {
            Map.Entry<Integer, Adventurer> entry = empIt.next();
            Adventurer emp = entry.getValue();
            Iterator<Map.Entry<String, Equipment>> eqIt = emp.equipmentBag.entrySet().iterator();
            emp.employeeMap.compute(this, (k, current) -> (current == null ? 0 : current) + 1);
            while (eqIt.hasNext()) {
                Map.Entry<String, Equipment> equipmentEntry = eqIt.next();
                Equipment eq = equipmentEntry.getValue();
                eqIt.remove();
                emp.bagIdMap.remove(eq.getId());
                emp.equipmentMap.remove(eq.getId());
                this.addEquipment(eq);
            }
            if (emp.employeeMap.get(this) > 3) {
                empIt.remove();
                emp.employeeMap.remove(this);
            }
        }
    }
}
