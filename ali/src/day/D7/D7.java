package day.D7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class D7 {

    static int teil1;
    static int teil2;

    public static void main(String[] args) throws IOException {
        FileSystem fileSystem = new FileSystem();
        fileSystem.run();
        fileSystem.goToRoot();
        teil1 = Search.addBy100(fileSystem.getCurrent());

        int totalSpaceAvailable = 70000000;
        int minimumSpaceNeeded = 30000000;

        Dir dir = fileSystem.getCurrent();
        int rootSize = dir.getSize();
        int unusedSpace = totalSpaceAvailable - rootSize;
        List<Integer> above = new ArrayList<>();
        Search.find(dir, above, minimumSpaceNeeded - unusedSpace);
        Collections.sort(above);
        teil2 = above.get(0);

        System.out.format("Teil1: %s \nTeil2: %s", teil1, teil2);
    }

}
