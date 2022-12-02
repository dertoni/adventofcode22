package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class D2 {
    private final static Map<String, Integer> possibleScoresStrategy1 = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, Integer>("A X", 4),
            new AbstractMap.SimpleEntry<String, Integer>("A Y", 8),
            new AbstractMap.SimpleEntry<String, Integer>("A Z", 3),

            new AbstractMap.SimpleEntry<String, Integer>("B X", 1),
            new AbstractMap.SimpleEntry<String, Integer>("B Y", 5),
            new AbstractMap.SimpleEntry<String, Integer>("B Z", 9),

            new AbstractMap.SimpleEntry<String, Integer>("C X", 7),
            new AbstractMap.SimpleEntry<String, Integer>("C Y", 2),
            new AbstractMap.SimpleEntry<String, Integer>("C Z", 6)
    );

    private final static Map<String, Integer> possibleScoresStrategy2 = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, Integer>("A X", 3),
            new AbstractMap.SimpleEntry<String, Integer>("A Y", 4),
            new AbstractMap.SimpleEntry<String, Integer>("A Z", 8),

            new AbstractMap.SimpleEntry<String, Integer>("B X", 1),
            new AbstractMap.SimpleEntry<String, Integer>("B Y", 5),
            new AbstractMap.SimpleEntry<String, Integer>("B Z", 9),

            new AbstractMap.SimpleEntry<String, Integer>("C X", 2),
            new AbstractMap.SimpleEntry<String, Integer>("C Y", 6),
            new AbstractMap.SimpleEntry<String, Integer>("C Z", 7)
    );

    public static void main(String[] args) throws IOException {
        d02(Utils.readFile("day02_example.txt"), "example");
        d02(Utils.readFile("day02_1.txt"), "part1");
    }

    private static void d02(List<String> input, String qualifier) {
        final int finalScoreStrategy1 = input.stream()
                .map(possibleScoresStrategy1::get)
                .mapToInt(Integer::valueOf)
                .sum();
        final int finalScoreStrategy2 = input.stream()
                .map(possibleScoresStrategy2::get)
                .mapToInt(Integer::valueOf)
                .sum();
        System.out.println(qualifier + ">>> My final score is with strategy1: " + finalScoreStrategy1);
        System.out.println(qualifier + ">>> My final score is with strategy2: " + finalScoreStrategy2);
    }
}
