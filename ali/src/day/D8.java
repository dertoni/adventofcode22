package day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class D8 {
    
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day8\\input.txt");
        List<String> input = new ArrayList<>(Files.readAllLines(path));

        int teil1 = teil1(input);
        int teil2 = teil2(input);

        System.out.format("Teil1: %s \nTeil2: %s ", teil1, teil2);
    }

    public static int teil1(List<String> all) {
        int teil1 = 0;
        String[] firstPart;
        String[] secondPart;
        int north, south, west, east, center = 0;

        for (int x = 0; x < all.size(); x++) {
            firstPart = all.get(x).split("");
            if (x == 0 || x == all.size() - 1)
                teil1 += firstPart.length;
            else {
                for (int y = 0; y < firstPart.length; y++) {
                    if (y == 0 || y == firstPart.length - 1)
                        teil1 += 1;
                    else {
                        north = 0;
                        south = 0;
                        west = 0;
                        east = 0;

                        center = Integer.parseInt(firstPart[y]);

                        for (int count = x - 1; count >= 0; count--) {
                            secondPart = all.get(count).split("");
                            if (north < Integer.parseInt(secondPart[y]))
                                north = Integer.parseInt(secondPart[y]);
                        }

                        for (int count = x + 1; count < all.size(); count++) {
                            secondPart = all.get(count).split("");
                            if (south < Integer.parseInt(secondPart[y]))
                                south = Integer.parseInt(secondPart[y]);
                        }

                        for (int count = 0; count < firstPart.length; count++) {
                            if (count < y)
                                if (west < Integer.parseInt(firstPart[count]))
                                    west = Integer.parseInt(firstPart[count]);

                                else if (count > y)
                                    if (east < Integer.parseInt(firstPart[count]))
                                    east = Integer.parseInt(firstPart[count]);


                        }

                        if (center > north || center > south || center > west || center > east)
                            teil1 += 1;
                    }
                }
            }
        }
        return teil1;
    }

    public static int teil2(List<String> all) {

        int teil2 = 0;
        String[] firstPart;
        String[] secondPart;

        int north, south, west, east, center = 0;

        for (int x = 0; x < all.size(); x++) {
            firstPart = all.get(x).split("");

            if (!(x == 0 || x == all.size() - 1)) {
                for (int y = 0; y < firstPart.length; y++) {
                    if (!(y == 0 || y == firstPart.length - 1)) {
                        north = 0;
                        south = 0;
                        east = 0;
                        west = 0;

                        center = Integer.parseInt(firstPart[y]);

                        for (int count = x - 1; count >= 0; count--) {
                            secondPart = all.get(count).split("");
                            if (center <= Integer.parseInt(secondPart[y])) {
                                north += 1;
                                break;
                            } else
                                north += 1;
                        }

                        for (int count = x + 1; count < all.size(); count++) {
                            secondPart = all.get(count).split("");
                            if (center <= Integer.parseInt(secondPart[y])) {
                                south += 1;
                                break;
                            } else
                                south += 1;
                        }

                        for (int count = y - 1; count >= 0; count--) {
                            if (center <= Integer.parseInt(firstPart[count])) {
                                east += 1;
                                break;
                            } else
                                east += 1;
                        }

                        for (int count = y + 1; count < firstPart.length; count++) {
                            if (center <= Integer.parseInt(firstPart[count])) {
                                west += 1;
                                break;
                            } else
                                west += 1;
                        }

                        if (teil2 < (north * south * east * west)) {
                            teil2 = north * south * east * west;
                        }
                    }
                }
            }
        }
        return teil2;
    }
}

