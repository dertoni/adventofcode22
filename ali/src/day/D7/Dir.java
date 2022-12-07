package day.D7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Dir {

    private final String name;
    private final List<File> files = new ArrayList<>();
    private final List<Dir> children = new ArrayList<>();

    private Dir parent = null;

    public Dir(String name) {
        this.name = name;
    }

    public Dir() {
        this("");
    }

    public void addFile(String name, int size) {
        files.add(new File(name, size));
    }

    public void addChild(Dir dir) {
        children.add(dir);
    }

    public void putParent(Dir dir) {
        parent = dir;
    }

    public Dir getChild(String name) {
        for (Dir child : children) {
            if (child.getName().equals(name)) {
                return child;
            }

        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Dir getParent() {
        return parent;
    }

    public List<Dir> getChildren() {
        return children;
    }

    public int getSize() {
        AtomicInteger sum = new AtomicInteger();
        files.forEach(file -> sum.addAndGet(file.getSize()));
        children.forEach(child -> sum.addAndGet(child.getSize()));
        return sum.get();
    }

}
