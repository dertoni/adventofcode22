package day.D7;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Search {

    public static int addBy100(Dir dir) {
        AtomicInteger sum = new AtomicInteger();
        int dirSize = dir.getSize();
        if (dirSize <= 100000) {
            sum.addAndGet(dirSize);
        }

        List<Dir> children = dir.getChildren();
        if (children.size() < 1) {
            return sum.get();
        }

        children.forEach(child -> sum.addAndGet(addBy100(child)));

        return sum.get();
    }

    public static void find(Dir actuall, List<Integer> up, int threshold) {
        int currentSize = actuall.getSize();
        if (currentSize >= threshold) {
            up.add(currentSize);
        } else {
            return;
        }

        List<Dir> children = actuall.getChildren();
        if (children.size() < 1) {
            return;
        }

        children.forEach(child -> find(child, up, threshold));
    }

}
