package day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class D3 {


    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day3\\input.txt");
        List<String> all = new ArrayList<>(Files.readAllLines(path));

        AtomicInteger teil1 = new AtomicInteger();
        int teil2 = 0;

        all.forEach(line -> {
            int mid = line.length() / 2;
            String firstPart = line.substring(0, mid);
            String secondPart = line.substring(mid);
            char commonChar = commonChar(firstPart, secondPart);
            teil1.addAndGet(weight(commonChar));
        });

        for (int i = 0; i < all.size(); i = i + 3) {
            teil2 += weightAdvanced(all.get(i), all.get(i + 1), all.get(i + 2));
        }

        System.out.format("Teil1: %s \nTeil2: %s", teil1, teil2);
    }


    public static char commonChar(String str1, String str2) {
        char commonChars = 0;

        if (str1.length() > 0 & str2.length() > 0) {
            String toBeIterated = (str1.length() > str2.length() ? str2 : str1);
            String toBeChecked = toBeIterated.equals(str1) ? str2 : str1;

            for (int i = 0; i < toBeIterated.length(); i++) {
                if (toBeChecked.contains(Character.toString(toBeIterated.charAt(i)))) {
                    commonChars = toBeIterated.charAt(i);
                }
            }
            return commonChars;
        } else {
            return 0;
        }
    }

    private static int weightAdvanced(String s1, String s2, String s3) {
        int prop = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s2.contains(s1.substring(i, i + 1)) && s3.contains(s1.substring(i, i + 1))) {
                prop = weight(s1.charAt(i));
            }
        }
        return prop;
    }

    public static int weight(char ch) {
        int result;
        String s = "" + ch;
        if (Character.isLowerCase(ch)) {
            result = s.codePointAt(0) - 96;
        } else {
            result = s.codePointAt(0) - 38;

        }

        return result;
    }

}
