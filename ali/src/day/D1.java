package day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class D1 {

    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("C:\\Users\\273705\\Desktop\\input.txt");
        var list = new ArrayList<>(Files.readAllLines(filePath));
        int sum = 0;
        int max = Integer.MIN_VALUE;
        List<Integer> each = new ArrayList<>();
        for (String s : list) {
            if (!s.isEmpty()) {
                sum = sum + Integer.parseInt(s);
            } else {
                if (sum > max) {
                    max = sum;
                }
                each.add(sum);
                sum = 0;
            }
        }

        each.sort(Collections.reverseOrder());
        int result = each.get(0) + each.get(1) + each.get(2);

        System.out.println("Max: " + max);
        System.out.println("3: " + result);


    }

}
