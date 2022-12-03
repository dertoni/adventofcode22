package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class D3 {

    public static void main(String[] args) throws IOException {
        d03(Utils.readFile("day03_example.txt"), "example");
        d03(Utils.readFile("day03_1.txt"), "part1");
    }

    private static void d03(List<String> input, String qualifier) {
        int sumPriority = 0;

        for (String line : input) {
            sumPriority += getPriorityFor(line);
        }

        System.out.println(qualifier + ">>> Sum of priorities: " + sumPriority);

        int sumOfThree = 0;
        for (int i = 0; i < input.size(); i = i + 3) {
            sumOfThree += getPriorityFor3(input.get(i), input.get(i+1), input.get(i+2));
        }

        System.out.println(qualifier + " >>> Sum of Threes: " + sumOfThree);
    }

    private static int getPriorityFor3(String s1, String s2, String s3) {
        for (int i = 0; i < s1.length(); i++) {
            if (s2.contains(s1.substring(i, i + 1)) && s3.contains(s1.substring(i, i + 1))) {
                return c2P(s1.charAt(i));
            }
        }

        throw new IllegalStateException("No common badge found");
    }

    private static int getPriorityFor(String line) {
        final var chars = new HashSet<Character>();

        int half = line.length() / 2;
        String firstHalf = line.substring(0, half);
        String secondHalf = line.substring(half);

        for (int i = 0; i < firstHalf.length(); i++) {
            chars.add(firstHalf.charAt(i));
        }

        for (int i = 0; i < secondHalf.length(); i++) {
            char character = secondHalf.charAt(i);
            if (chars.contains(character)) {
                return c2P(character);
            }
        }

        throw new IllegalStateException("No duplicate item found");
    }

    private static int c2P(char character) {
        int priority = character;
        if (Character.isLowerCase(character)) {
            priority = priority - 'a' + 1;
        } else {
            priority = priority - 'A' + 27;
        }
        return priority;
    }
}
