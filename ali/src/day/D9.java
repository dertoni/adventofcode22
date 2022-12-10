package day;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class D9  {
    public static int[][] pattern = new int['U' + 1][];

    public static void main(String[] args) {

        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day9\\input.txt");

        pattern['U'] = new int[] {+1, +0};
        pattern['D'] = new int[] {-1, +0};
        pattern['L'] = new int[] {+0, -1};
        pattern['R'] = new int[] {+0, +1};

        int teil1 = day9(2, path);
        int teil2 = day9(10, path);

        System.out.format("Teil1: %s \nTeil2: %s ", teil1, teil2);
    }


    public static int day9(int numNub, Path p) {
        int[][] nub = new int[numNub][2];
        Set<String> Positions = new HashSet<>();
        Positions.add(Arrays.toString(nub[numNub - 1]));
        try (Scanner s = new Scanner(p)) {
            while (s.hasNext()) {
                int n = s.nextInt();
                for (int[] dir = pattern[s.next("[UDLR]").charAt(0)]; n > 0; n--) {
                    increase(nub[0], dir);
                    for (int k = 1; k < numNub; k++)
                        tracking(nub[k], nub[k - 1]);
                    Positions.add(Arrays.toString(nub[numNub - 1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Positions.size();
    }

    private static void increase(int[] coordinator, int[] delta) {
        coordinator[0] += delta[0];
        coordinator[1] += delta[1];
    }

    private static void tracking(int[] tail, int[] head) {
        int row = head[0] - tail[0];
        int Col = head[1] - tail[1];
        int distance = Math.abs(row) + Math.abs(Col);
        boolean same = tail[0] == head[0] || tail[1] == head[1];
        if ((same && distance > 1) || distance > 2)
            increase(tail, new int[] {Integer.signum(row), Integer.signum(Col)});
    }
}
