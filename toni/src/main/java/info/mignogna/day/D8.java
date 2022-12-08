package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.List;

public class D8 {
    private static int totalForrestWidth;
    private static int totalForrestHeight;
    public static void main(String[] args) throws IOException {
        d08(Utils.readFile("day08_example.txt"), "example");
        d08(Utils.readFile("day08_1.txt"), "original");
    }

    private static void d08(List<String> input, String qualifier) {
        totalForrestWidth = input.get(0).length();
        totalForrestHeight = input.size();

        final int[][] forrest = initializeForrest(input);

        int counterVisibleTrees = 0;
        for (int height = 0; height < totalForrestHeight; height++) {
            for (int width = 0; width < totalForrestWidth; width++) {
                counterVisibleTrees += isVisible(height, width, forrest) ? 1 : 0;
            }
        }

        int maxScenicScore = 0;
        for (int height = 0; height < totalForrestHeight; height++) {
            for (int width = 0; width < totalForrestWidth; width++) {
                maxScenicScore = Math.max(maxScenicScore, calcScenicScore(width, height, forrest));
            }
        }

        System.out.println(qualifier + ">>> Number of visible trees: " + counterVisibleTrees);
        System.out.println(qualifier + ">>> Max scenic score is: " + maxScenicScore);

    }

    private static int calcScenicScore(int x, int y, int[][] forrest) {
        if (x == 0 || y == 0 || x == totalForrestWidth - 1 || y == totalForrestHeight - 1) {
            return 0;
        }

        return calcScenicScoreLeft(x, y, forrest) *
                calcScenicScoreRight(x, y, forrest) *
                calcScenicScoreUp(x, y, forrest) *
                calcScenicScoreDown(x, y, forrest);
    }

    private static int calcScenicScoreLeft(int x, int y, int[][] forrest) {
        int result = 0;
        for (int width = x - 1; width >= 0; width--) {
            if (forrest[y][width] >= forrest[y][x]) {
                return result+1;
            }

            result++;
        }
        return result;
    }

    private static int calcScenicScoreRight(int x, int y, int[][] forrest) {
        int result = 0;
        for (int width = x + 1; width < totalForrestWidth; width++) {
            if (forrest[y][width] >= forrest[y][x]) {
                return result+1;
            }
            result++;
        }
        return result;
    }

    private static int calcScenicScoreUp(int x, int y, int[][] forrest) {
        int result = 0;
        for (int height = y - 1; height >= 0; height--) {
            if (forrest[height][x] >= forrest[y][x]) {
                return result+1;
            }
            result++;
        }
        return result;
    }

    private static int calcScenicScoreDown(int x, int y, int[][] forrest) {
        int result = 0;
        for (int height = y + 1; height < totalForrestHeight; height++) {
            if (forrest[height][x] >= forrest[y][x]) {
                return result;
            }
            result++;
        }
        return result;
    }

    private static boolean isVisible(int y, int x, int[][] forrest) {
        if (isVisibleFromLeft(x, y, forrest) ||
            isVisibleFromRight(x, y, forrest) ||
            isVisibleFromUp(x, y, forrest) ||
            isVisibleFromDown(x, y, forrest)) {
            return true;
        }
        return false;
    }

    private static boolean isVisibleFromLeft(int x, int y, int[][] forrest) {
        // edge of the forrest
        if (x == 0) {
            return true;
        }

        for (int width = 0; width < x; width++) {
            if (forrest[y][width] >= forrest[y][x]) {
                // tree not visible because of other trees
                return false;
            }
        }

        // all trees in line of sight are smaller -> tree is visible
        return true;
    }

    private static boolean isVisibleFromRight(int x, int y, int[][] forrest) {
        // edge of the forrest
        if (x == totalForrestWidth-1) {
            return true;
        }

        for (int width = totalForrestWidth-1; width > x; width--) {
            if (forrest[y][width] >= forrest[y][x]) {
                // tree not visible because of other trees
                return false;
            }
        }

        // all trees in line of sight are smaller -> tree is visible
        return true;
    }

    private static boolean isVisibleFromUp(int x, int y, int[][] forrest) {
        // edge of the forrest
        if (y == 0) {
            return true;
        }

        for (int height = 0; height < y; height++) {
            if (forrest[height][x] >= forrest[y][x]) {
                // tree not visible because of other trees
                return false;
            }
        }

        // all trees in line of sight are smaller -> tree is visible
        return true;
    }

    private static boolean isVisibleFromDown(int x, int y, int[][] forrest) {
        // edge of the forrest
        if (y == totalForrestHeight) {
            return true;
        }

        for (int height = totalForrestHeight-1; height > y; height--) {
            if (forrest[height][x] >= forrest[y][x]) {
                // tree not visible because of other trees
                return false;
            }
        }

        // all trees in line of sight are smaller -> tree is visible
        return true;
    }


    private static int[][] initializeForrest(List<String> input) {
        final int[][] forrest = new int[totalForrestHeight][totalForrestWidth];

        for (int height = 0; height < totalForrestHeight; height++) {
            final String line = input.get(height);
            for (int width = 0; width < totalForrestWidth; width++) {
                forrest[height][width] = Integer.parseInt(line.substring(width, width+1));
            }
        }

        return forrest;
    }
}
