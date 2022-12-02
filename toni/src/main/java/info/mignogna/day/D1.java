package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class D1 {
    private static Map<Integer, Integer> elvesAndCalories = new HashMap<>();

    public static void main(String[] args) throws IOException, URISyntaxException {
        d01(Utils.readFile("day01_example.txt"));
        reset();
        d01(Utils.readFile("day01_1.txt"));
    }

    private static void reset() {
        elvesAndCalories = new HashMap<>();
    }

    private static void d01(List<String> lines) {
        parseElves(lines);
        getElvesWithHighestCalories();
    }

    private static void getElvesWithHighestCalories() {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(elvesAndCalories.entrySet());
        entries.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        System.out.println("P1: Elf with 1st highest calories (" + entries.get(0).getValue() + ") is: " + entries.get(0).getKey());
        System.out.println("P2: Elf with 2nd highest calories (" + entries.get(1).getValue() + ") is: " + entries.get(1).getKey());
        System.out.println("P3: Elf with 3rd highest calories (" + entries.get(2).getValue() + ") is: " + entries.get(2).getKey());
        System.out.println("Sum(P1:P3) of calories: " + (entries.get(0).getValue() + entries.get(1).getValue() + entries.get(2).getValue()));
    }

    private static void parseElves(List<String> lines) {
        int currentElfNumber = 1;
        int countOfCalories = 0;
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                elvesAndCalories.put(currentElfNumber, countOfCalories);
                currentElfNumber++;
                countOfCalories = 0;
            } else {
                countOfCalories += Integer.parseInt(line.trim());
            }
        }
        elvesAndCalories.put(currentElfNumber, countOfCalories);
    }
}
