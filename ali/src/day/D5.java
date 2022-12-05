package day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class D5 {

    public static List<String> first = new ArrayList<>();
    public static List<String> second = new ArrayList<>();
    public static List<String> third = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day5\\example.txt");
        List<String> all = new ArrayList<>(Files.readAllLines(path));

        first.add("Z");
        first.add("N");

        second.add("M");
        second.add("C");
        second.add("D");

        third.add("P");

        System.out.println(
            first.get(first.size() - 1) + "" + second.get(second.size() - 1) + "" + third.get(third.size() - 1));
        for (int i = 5; i < all.size(); i++) {
            int step = Integer.parseInt(all.get(i).replace(" ", "").split("move")[1].split("from")[0]);
            int from = Integer.parseInt(all.get(i).replace(" ", "").split("move")[1].split("from")[1].split("to")[0]);
            int to = Integer.parseInt(all.get(i).replace(" ", "").split("move")[1].split("from")[1].split("to")[1]);
            move(step, from, to, first, second, third);
        }
    }


    public static void move(int step, int from, int to, List<String> first, List<String> second, List<String> third) {
        List<String> fromList = new ArrayList<>();
        List<String> toList = new ArrayList<>();
        switch (from) {
            case 1 -> fromList = first;
            case 2 -> fromList = second;
            case 3 -> fromList = third;
        }

        switch (to) {
            case 1 -> toList = first;
            case 2 -> toList = second;
            case 3 -> toList = third;
        }

        List<String> toBeMoved = fromList.subList(Math.max(fromList.size() - step, 0), fromList.size());
        System.out.println(toBeMoved);

        toList.addAll(toBeMoved);
        fromList.removeAll(toBeMoved);


    }
}
