package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.List;

public class D4 {
    public static void main(String[] args) throws IOException {
        d04_1(Utils.readFile("day04_example.txt"), "example");
        d04_1(Utils.readFile("day04_1.txt"), "example");

        d04_2(Utils.readFile("day04_example.txt"), "example");
        d04_2(Utils.readFile("day04_1.txt"), "example");
    }

    private static void d04_2(List<String> input, String qualifier) {
        int countOverlappingAssignments = 0;
        for (String line : input) {
            // check for last line (which is empty)
            if (!line.trim().isEmpty()) {
                String[] sectionAssignments = line.split(",");

                String[] beginEndOfAssignment1 = sectionAssignments[0].split("-");
                int b1 = Integer.parseInt(beginEndOfAssignment1[0]);
                int e1 = Integer.parseInt(beginEndOfAssignment1[1]);

                String[] beginEndOfAssignment2 = sectionAssignments[1].split("-");
                int b2 = Integer.parseInt(beginEndOfAssignment2[0]);
                int e2 = Integer.parseInt(beginEndOfAssignment2[1]);

                if ((((b1 >= b2) && (b1 <= e2)) || ((e1 >= b2) && (e1 <= e2))) ||
                                (((b2 >= b1) && (b2 <= e1)) || ((e2 >= b1) && (e2 <= e1)))) {
                    countOverlappingAssignments++;
                }
            }
        }

        System.out.println(qualifier + ">>> Count of overlapping assignments is: " + countOverlappingAssignments);
    }

    private static void d04_1(List<String> input, String qualifier) {
        int countRedundantAssignments = 0;
        for (String line : input) {
            // check for last line (which is empty)
            if (!line.trim().isEmpty()) {
                String[] sectionAssignments = line.split(",");

                String[] beginEndOfAssignment1 = sectionAssignments[0].split("-");
                int b1 = Integer.parseInt(beginEndOfAssignment1[0]);
                int e1 = Integer.parseInt(beginEndOfAssignment1[1]);

                String[] beginEndOfAssignment2 = sectionAssignments[1].split("-");
                int b2 = Integer.parseInt(beginEndOfAssignment2[0]);
                int e2 = Integer.parseInt(beginEndOfAssignment2[1]);

                if (((b1 <= b2) && (e1 >= e2)) || ((b2 <= b1) && (e2 >= e1))) {
                    countRedundantAssignments++;
                }
            }
        }

        System.out.println(qualifier + ">>> Count of redundant assignments is: " + countRedundantAssignments);
    }
}
