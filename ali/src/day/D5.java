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
    public static List<String> four = new ArrayList<>();
    public static List<String> five = new ArrayList<>();
    public static List<String> six = new ArrayList<>();
    public static List<String> seven = new ArrayList<>();
    public static List<String> eight = new ArrayList<>();
    public static List<String> neun = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        first.add("W");             second.add("J");                third.add("L");
        first.add("D");             second.add("N");                third.add("S");
        first.add("G");             second.add("G");                third.add("F");
        first.add("B");             second.add("C");                third.add("H");
        first.add("H");             second.add("R");                third.add("D");
        first.add("R");             second.add("F");                third.add("N");
        first.add("V");


        four.add("J");             five.add("S");                   six.add("P");
        four.add("D");             five.add("H");                   six.add("G");
        four.add("S");             five.add("D");                   six.add("H");
        four.add("V");             five.add("R");                   six.add("C");
        five.add("Q");                   six.add("M");
        five.add("W");
        five.add("N");
        five.add("V");


        seven.add("F");            eight.add("S");                  neun.add("L");
        seven.add("J");            eight.add("J");                  neun.add("G");
        seven.add("B");            eight.add("R");                  neun.add("S");
        seven.add("G");                                             neun.add("R");
        seven.add("L");                                             neun.add("B");
        seven.add("Z");                                             neun.add("N");
        seven.add("H");                                             neun.add("V");
        seven.add("C");                                             neun.add("M");


        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day5\\input.txt");
        List<String> all = new ArrayList<>(Files.readAllLines(path));



        System.out.println(
            first.get(first.size() - 1) + "" + second.get(second.size() - 1) + "" + third.get(third.size() - 1));
        for (int i = 5; i < all.size(); i++) {
            int step = Integer.parseInt(all.get(i).replace(" ", "").split("move")[1].split("from")[0]);
            int from = Integer.parseInt(all.get(i).replace(" ", "").split("move")[1].split("from")[1].split("to")[0]);
            int to = Integer.parseInt(all.get(i).replace(" ", "").split("move")[1].split("from")[1].split("to")[1]);
            move(step, from, to, first, second, third, four, five, six, seven, eight, neun);
        }
        System.out.format("%s%s%s", first.get(first.size()-1), second.get(second.size()-1), third.get(third.size()-1));
    }


    public static void move(int step, int from, int to, List<String> first, List<String> second, List<String> third, List<String> four, List<String> five, List<String> six, List<String> seven, List<String> eight, List<String> neun) {
        List<String> fromList = new ArrayList<>();
        List<String> toList = new ArrayList<>();
        switch (from) {
            case 1 -> fromList = first;
            case 2 -> fromList = second;
            case 3 -> fromList = third;
            case 4 -> fromList = four;
            case 5 -> fromList = five;
            case 6 -> fromList = six;
            case 7 -> fromList = seven;
            case 8 -> fromList = eight;
            case 9 -> fromList = neun;
        }

        switch (to) {
            case 1 -> toList = first;
            case 2 -> toList = second;
            case 3 -> toList = third;
            case 4 -> toList = four;
            case 5 -> toList = five;
            case 6 -> toList = six;
            case 7 -> toList = seven;
            case 8 -> toList = eight;
            case 9 -> toList = neun;
        }

        List<String> toBeMoved = fromList.subList(Math.max(fromList.size() - step, 0), fromList.size());


        toList.addAll(toBeMoved);
        fromList.removeAll(toBeMoved);


    }
}
