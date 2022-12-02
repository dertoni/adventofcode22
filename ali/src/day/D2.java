package day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class D2 {


    public static int teil1(List<String> me, List<String> opp) {
        int teil1 = 0;
        for (int i = 0; i < me.size(); i++) {

            if (me.get(i).equals("X")) {
                teil1 += 1;
            } else if (me.get(i).equals("Y")) {
                teil1 += 2;
            } else {
                teil1 += 3;
            }

            switch (me.get(i)) {
                case "X":
                    switch (opp.get(i)) {
                        case "A" -> teil1 += 3;
                        case "C" -> teil1 += 6;
                    }
                    break;
                case "Y":
                    switch (opp.get(i)) {
                        case "A" -> teil1 += 6;
                        case "B" -> teil1 += 3;
                    }
                    break;
                case "Z":
                    switch (opp.get(i)) {
                        case "B" -> teil1 += 6;
                        case "C" -> teil1 += 3;
                    }
                    break;
            }
        }
        return teil1;
    }

    public static int teil2(Path path) throws IOException {
        Stream<String> stream = Files.lines(path);
        Map<String, Integer> guied = new HashMap<>() {{
            put("C X", 2);
            put("C Y", 6);
            put("C Z", 7);
            put("A X", 3);
            put("A Y", 4);
            put("A Z", 8);
            put("B X", 1);
            put("B Y", 5);
            put("B Z", 9);
        }};
        return stream.map(guied::get).mapToInt(integer -> integer).sum();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day2\\input.txt");
        List<String> all = new ArrayList<>(Files.readAllLines(path));

        List<String> opp = new ArrayList<>();
        List<String> me = new ArrayList<>();
        int teil1;
        int teil2;

        for (String s : all) {
            opp.add(String.valueOf(s.charAt(0)));
            me.add(String.valueOf(s.charAt(2)));
        }

        teil1 = teil1(me, opp);
        teil2 = teil2(path);

        System.out.format("1. Teil: %s \n" + "2. Teil: %s", teil1, teil2);
    }

}