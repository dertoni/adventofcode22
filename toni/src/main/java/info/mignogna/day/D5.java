package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.List;

public class D5 {
    private static StringBuilder[] exampleStacks;
    private static StringBuilder[] part1Stacks;


    public static void initalize() {
        exampleStacks = new StringBuilder[]{
                new StringBuilder("ZN"),
                new StringBuilder("MCD"),
                new StringBuilder("P")
        };

        part1Stacks = new StringBuilder[]{
                new StringBuilder("VCDRZGBW"),
                new StringBuilder("GWFCBSTV"),
                new StringBuilder("CBSNW"),
                new StringBuilder("QGMNJVCP"),
                new StringBuilder("TSLFDHB"),
                new StringBuilder("JVTWMN"),
                new StringBuilder("PFLCSTG"),
                new StringBuilder("BDZ"),
                new StringBuilder("MNZW")
        };
    }

    public static void main(String[] args) throws IOException {
        initalize();
        d04(Utils.readFile("day05_example.txt"), exampleStacks, "example part1", true);
        d04(Utils.readFile("day05_1.txt"), part1Stacks, "original part1", true);

        initalize();
        d04(Utils.readFile("day05_example.txt"), exampleStacks, "example part2", false);
        d04(Utils.readFile("day05_1.txt"), part1Stacks, "original part2", false);
    }

    private static void d04(List<String> input, StringBuilder[] stacks, String qualifier, boolean partOne) {
        for (String line : input) {
            if (!line.isEmpty()) {
                String[] instructions = line.split(" ");
                var count = Integer.parseInt(instructions[0]);
                var origin = stacks[Integer.parseInt(instructions[1]) - 1];
                var destination = stacks[Integer.parseInt(instructions[2]) - 1];
                if (partOne) {
                    moveCratesPart1(count, origin, destination);
                } else {
                    moveCratesPart2(count, origin, destination);
                }
            }
        }

        System.out.print(qualifier + ">>> Top crates are: ");
        for (StringBuilder stack : stacks) {
            System.out.print(stack.charAt(stack.length() - 1));
        }
        System.out.println();
    }

    private static void moveCratesPart2(int count, StringBuilder origin, StringBuilder destination) {
        var beginOfCratesToMove = origin.length() - count;
        var endOfCratesToMove = origin.length();
        final var cratesToMove = origin.subSequence(beginOfCratesToMove, endOfCratesToMove);

        for (int i = 0; i < count; i++) {
            origin.deleteCharAt(origin.length() - 1);
        }

        destination.append(cratesToMove);
    }

    private static void moveCratesPart1(int count, StringBuilder origin, StringBuilder destination) {
        for (int i = 0; i < count; i++) {
            final char crateToMove = origin.charAt(origin.length() - 1);
            origin.deleteCharAt(origin.length() - 1);
            destination.append(crateToMove);
        }
    }

}
