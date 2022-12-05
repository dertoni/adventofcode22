package day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class D4 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day4\\input.txt");
        List<String> all = new ArrayList<>(Files.readAllLines(path));

        int teil1 = 0;
        int teil2 = 0;

        List<String> first = new ArrayList<>();
        List<String> second = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            first.add(all.get(i).split(",")[0]);
            second.add(all.get(i).split(",")[1]);

            int firstStart = Integer.parseInt(first.get(i).split("-")[0]);
            int firstEnd = Integer.parseInt(first.get(i).split("-")[1]);

            int secondStart = Integer.parseInt(second.get(i).split("-")[0]);
            int secondEnd = Integer.parseInt(second.get(i).split("-")[1]);

            ArrayList<String> value1 = new ArrayList<>();
            ArrayList<String> value2 = new ArrayList<>();
            for (int j = firstStart; j <= firstEnd; j++) {
                value1.add(String.valueOf(j));
            }

            for (int j = secondStart; j <= secondEnd; j++) {
                value2.add(String.valueOf(j));
            }

            if (value1.containsAll(value2)) {
                teil1++;
            } else if (value2.containsAll(value1)) {
                teil1++;
            }

            List<String> co = new ArrayList<>(value2);
            co.retainAll(value1);
            if(!co.isEmpty())
                teil2++;


        }

        System.out.format("Teil1: %s \nTeil2: %s ", teil1, teil2);




    }


}
