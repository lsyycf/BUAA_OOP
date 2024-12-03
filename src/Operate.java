import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Operate {

    public static void addAdventurer(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        String adventurerName = MainClass.inputInfo.get(line).get(2);
        Adventurer adventure = new Adventurer(adventurerId, adventurerName);
        MainClass.adventurerMap.put(adventurerId, adventure);
    }

    public static void addBottle(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int bottleId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        String bottleName = MainClass.inputInfo.get(line).get(3);
        int bottleCapacity = Integer.parseInt(MainClass.inputInfo.get(line).get(4));
        String bottleType = MainClass.inputInfo.get(line).get(5);
        int bottleCE = Integer.parseInt(MainClass.inputInfo.get(line).get(6));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        switch (bottleType) {
            case "HpBottle": {
                Bottle bt = new HpBottle(bottleId, bottleName,
                        bottleCapacity, bottleCE);
                ad.addBottle(bt);
                break;
            }
            case "AtkBottle": {
                Bottle bt = new AtkBottle(bottleId, bottleName,
                        bottleCapacity, bottleCE);
                ad.addBottle(bt);
                break;
            }
            case "DefBottle": {
                Bottle bt = new DefBottle(bottleId, bottleName,
                        bottleCapacity, bottleCE);
                ad.addBottle(bt);
                break;
            }
            default:
                break;
        }
    }

    public static void addEquipment(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int equipmentId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        String equipmentName = MainClass.inputInfo.get(line).get(3);
        int equipmentDurability = Integer.parseInt(MainClass.inputInfo.get(line).get(4));
        String equipmentType = MainClass.inputInfo.get(line).get(5);
        int equipmentCE = Integer.parseInt(MainClass.inputInfo.get(line).get(6));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        switch (equipmentType) {
            case "Axe": {
                Axe axe = new Axe(equipmentId, equipmentName,
                        equipmentDurability, equipmentCE);
                ad.addEquipment(axe);
                break;
            }
            case "Sword": {
                Sword sword = new Sword(equipmentId, equipmentName,
                        equipmentDurability, equipmentCE);
                ad.addEquipment(sword);
                break;
            }
            case "Blade": {
                Blade blade = new Blade(equipmentId, equipmentName,
                        equipmentDurability, equipmentCE);
                ad.addEquipment(blade);
                break;
            }
            default:
                break;
        }
    }

    public static void addDurability(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int equipmentId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        Equipment eq = ad.getEquipment(equipmentId);
        eq.addDurability(1);
        System.out.println(eq.getName() + " " + eq.getDurability());
    }

    public static void deleteObject(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int objectId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        Object obj = ad.deleteObject(objectId);
        if (obj instanceof HpBottle) {
            System.out.println("HpBottle"
                    + " " + ((HpBottle) obj).getName()
                    + " " + ((HpBottle) obj).getCapacity());
        } else if (obj instanceof AtkBottle) {
            System.out.println("AtkBottle"
                    + " " + ((AtkBottle) obj).getName()
                    + " " + ((AtkBottle) obj).getCapacity());
        } else if (obj instanceof DefBottle) {
            System.out.println("DefBottle"
                    + " " + ((DefBottle) obj).getName()
                    + " " + ((DefBottle) obj).getCapacity());
        } else if (obj instanceof Axe) {
            System.out.println("Axe"
                    + " " + ((Equipment) obj).getName()
                    + " " + ((Equipment) obj).getDurability());
        } else if (obj instanceof Sword) {
            System.out.println("Sword"
                    + " " + ((Equipment) obj).getName()
                    + " " + ((Equipment) obj).getDurability());
        } else if (obj instanceof Blade) {
            System.out.println("Blade"
                    + " " + ((Equipment) obj).getName()
                    + " " + ((Equipment) obj).getDurability());
        }
    }

    public static void takeObject(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int objectId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        ad.take(objectId);
    }

    public static void useBottle(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int objectId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        boolean ret = ad.useBottle(objectId);
        if (ret) {
            System.out.println(ad.getName()
                    + " " + ad.getHitPoint()
                    + " " + ad.getAtk()
                    + " " + ad.getDef());
        } else {
            System.out.println(ad.getName() + " fail to use "
                    + ad.getBottle(objectId).getName());
        }
    }

    public static void getFragment(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int fragId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        String fragName = MainClass.inputInfo.get(line).get(3);
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        Fragment fg = new Fragment(fragId, fragName);
        ad.addFragment(fg);
    }

    public static void useFragment(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        String fragName = MainClass.inputInfo.get(line).get(2);
        int welfareId = Integer.parseInt(MainClass.inputInfo.get(line).get(3));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        int ret = ad.useFragment(fragName, welfareId);
        if (ret == 0) {
            System.out.println(ad.getFragment(fragName)
                    + ": Not enough fragments collected yet");
        } else if (ret == 1) {
            System.out.println(ad.getBottle(welfareId).getName()
                    + " " + ad.getBottle(welfareId).getCapacity());
        } else if (ret == 2) {
            System.out.println(ad.getEquipment(welfareId).getName()
                    + " " + ad.getEquipment(welfareId).getDurability());
        } else {
            System.out.println("Congratulations! HpBottle " + fragName + " acquired");
        }
    }

    public static void battle(int line) {
        String atkType = MainClass.inputInfo.get(line).get(3);
        if (atkType.equals("normal")) {
            normalBattle(line);
        } else if (atkType.equals("chain")) {
            chainBattle(line);
        }
    }

    public static void normalBattle(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        String equipmentName = MainClass.inputInfo.get(line).get(2);
        int k = Integer.parseInt(MainClass.inputInfo.get(line).get(4));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        int maxDef = 0;
        for (int i = 0; i < k; i++) {
            int rivalId = Integer.parseInt(MainClass.inputInfo.get(line).get(5 + i));
            Adventurer da = MainClass.adventurerMap.get(rivalId);
            maxDef = Math.max(maxDef, da.getDef());
        }
        if (!ad.battle(equipmentName, maxDef)) {
            System.out.println("Adventurer " + adventurerId + " defeated");
            return;
        }
        LinkedHashMap<Integer, Adventurer> helpMap = new LinkedHashMap<>();
        for (int i = 0; i < k; i++) {
            int rivalId = Integer.parseInt(MainClass.inputInfo.get(line).get(5 + i));
            Adventurer da = MainClass.adventurerMap.get(rivalId);
            int beforeBattle = da.getHitPoint();
            ad.useEquipment(equipmentName, da);
            int afterBattle = da.getHitPoint();
            if (afterBattle <= beforeBattle / 2) {
                helpMap.put(rivalId, da);
            }
            System.out.println(da.getName() + " " + afterBattle);
        }
        Equipment eq = ad.getEquipmentBag(equipmentName);
        eq.subDurability(1);
        if (eq.getDurability() == 0) {
            ad.deleteObject(eq.getId());
        }
        for (Map.Entry<Integer, Adventurer> entry : helpMap.entrySet()) {
            entry.getValue().help();
        }
    }

    public static void chainBattle(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        String equipmentName = MainClass.inputInfo.get(line).get(2);
        int k = Integer.parseInt(MainClass.inputInfo.get(line).get(4));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        HashMap<Integer, Adventurer> atkMap = new HashMap<>();
        int maxDef = 0;
        for (int i = 0; i < k; i++) {
            int rivalId = Integer.parseInt(MainClass.inputInfo.get(line).get(5 + i));
            Adventurer da = MainClass.adventurerMap.get(rivalId);
            maxDef = Math.max(maxDef, da.getTotal());
            atkMap.putAll(da.getChain());
        }
        if (!ad.battle(equipmentName, maxDef)) {
            System.out.println("Adventurer " + adventurerId + " defeated");
            return;
        }
        int loss = 0;
        for (Map.Entry<Integer, Adventurer> adventurerEntry : atkMap.entrySet()) {
            Adventurer adventurer = adventurerEntry.getValue();
            loss += ad.useEquipment(equipmentName, adventurer);
        }
        Equipment eq = ad.getEquipmentBag(equipmentName);
        eq.subDurability(1);
        if (eq.getDurability() == 0) {
            ad.deleteObject(eq.getId());
        }
        System.out.println(loss);
    }

    public static void employ(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        int employerId = Integer.parseInt(MainClass.inputInfo.get(line).get(2));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        Adventurer employer = MainClass.adventurerMap.get(employerId);
        ad.employ(employer);
    }

    public static void challenge(int line) {
        int adventurerId = Integer.parseInt(MainClass.inputInfo.get(line).get(1));
        Adventurer ad = MainClass.adventurerMap.get(adventurerId);
        ad.challenge();
    }
}
