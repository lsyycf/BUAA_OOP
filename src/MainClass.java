import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {
    public static final HashMap<Integer, Adventurer> adventurerMap = new HashMap<>();
    public static final Scanner sc = new Scanner(System.in);
    public static final ArrayList<ArrayList<String>> inputInfo = new ArrayList<>();

    public static void main(String[] args) {
        int numOfInstruction = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < numOfInstruction; i++) {
            String nextLine = sc.nextLine();
            String[] strings = nextLine.trim().split(" +");
            inputInfo.add(new ArrayList<>(Arrays.asList(strings)));
        }
        for (int i = 0; i < numOfInstruction; i++) {
            int type = Integer.parseInt(inputInfo.get(i).get(0));
            if (type == 1) {
                Operate.addAdventurer(i);
            } else if (type == 2) {
                Operate.addBottle(i);
            } else if (type == 3) {
                Operate.addEquipment(i);
            } else if (type == 4) {
                Operate.addDurability(i);
            } else if (type == 5) {
                Operate.deleteObject(i);
            } else if (type == 6) {
                Operate.takeObject(i);
            } else if (type == 7) {
                Operate.useBottle(i);
            } else if (type == 8) {
                Operate.getFragment(i);
            } else if (type == 9) {
                Operate.useFragment(i);
            } else if (type == 10) {
                Operate.battle(i);
            } else if (type == 11) {
                Operate.employ(i);
            } else if (type == 12) {
                Operate.challenge(i);
            }
        }
    }
}
