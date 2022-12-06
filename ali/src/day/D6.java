package day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class D6 {
    
    public static void main(String[] args) throws IOException {

        int teil1 = search(4);
        int teil2 = search(14);
        System.out.format("Teil1: %s \nTeil2: %s ", teil1, teil2);
    }

    public static int search(int afterChars) {
        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day6\\input.txt");
        List<String> all;
        try {
            all = new ArrayList<>(Files.readAllLines(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> finalAll = all;
        return IntStream.range(afterChars, all.get(0).length()).mapToObj(i -> finalAll.get(0).substring(i - afterChars, i))
            .filter(s -> s.chars().distinct().count() == afterChars).mapToInt(all.get(0)::indexOf).findAny().getAsInt()
            + afterChars;
    }
}
