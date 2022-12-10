package day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D10 {
    static int turn = 0;
    static int X = 1;
    static int teil1 = 0;
    static int teil2 = 0;
    
    static int spritePosition = 0;

    public static void main(String[] args){

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day10\\input.txt"));
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" ");
                if (line[0].equals("noop")) {
                    turn++;
                    int period = turn + 1;
                    if (period == 20 || period == 60 || period == 100 || period == 140 || period == 180 || period == 220)
                        teil1 = teil1 + ((period) * X);

                } else if (line[0].equals("addx")) {
                    for (int i = 1; i <= 2; i++) {
                        turn++;
                        if (i == 2) {
                            X = X + Integer.parseInt(line[1]);
                            spritePosition = X % 40;
                        }
                        int during = turn + 1;
                        if (during == 20 || during == 60 || during == 100 || during == 140 || during == 180 || during == 220)
                            teil1 = ((during) * X) + teil1;
                    }
                }
            }

            System.out.format("Teil1: %s \nTeil2: %s ", teil1, teil2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
