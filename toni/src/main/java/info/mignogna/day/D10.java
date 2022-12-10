package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.List;

public class D10 {
    public static void main(String[] args) throws IOException {
        d10part1(Utils.readFile("day10_example.txt"), "example_part1");
        d10part1(Utils.readFile("day10_1.txt"), "original_part1");
        d10part2(Utils.readFile("day10_example.txt"), "example_part2");
        d10part2(Utils.readFile("day10_1.txt"), "original_part2");
    }

    private static void d10part2(List<String> input, String qualifier) {
        int regX = 1;
        int commandIdx = 0;
        boolean secondTick = false;
        int beganInTickNr = 0;
        for (int i = 1; i < 241; i++) {
            String command = input.get(commandIdx);
            if (command.startsWith("addx") && !secondTick) {
                beganInTickNr = i;
                secondTick = true;
            }

            int pos = i%40-1;
            if (pos == regX - 1 || pos == regX || pos == regX + 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }

            if (i == 40 || i == 80 || i == 120 || i == 160 || i == 200 || i == 240) {
                System.out.println();
            }

            if (command.startsWith("noop")) {
                commandIdx++;
            }

            if (command.startsWith("addx") && beganInTickNr != i) {
                secondTick = false;
                regX += Integer.parseInt(command.split(" ")[1]);
                commandIdx++;
            }
        }
    }

    private static void d10part1(List<String> input, String qualifier) {
        int cycle = 1;
        int regX = 1;
        int sum = 0;
        for (String line : input) {
            if (line.equals("noop")) {
                cycle++;
                if (isPeekTime(cycle)) {
                    sum += cycle * regX;
                }
                continue;
            }

            cycle++;
            if (isPeekTime(cycle)) {
                sum += cycle * regX;
            }

            cycle++;
            regX += Integer.parseInt(line.split(" ")[1]);
            if (isPeekTime(cycle)) {
                sum += cycle * regX;
            }
        }

        System.out.println(qualifier + ">>> Sum is: " + sum);
    }

    private static boolean isPeekTime(int cycle) {
        return cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220;
    }
}
