package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class D7 {
    public static void main(String[] args) throws IOException {
        d07(Utils.readFile("day07_example.txt"), "example");
        d07(Utils.readFile("day07_1.txt"), "original");
    }

    private static void d07(List<String> input, String qualifier) {
        final int limitDirSize = 100000;
        final int filesystemSize = 70000000;
        final int neededUnusedSpaceSize = 30000000;

        FileTreeNode root = parseInput(input);
        List<FileTreeNode> nodes = root.filterDirectories(node -> node.getSize() < limitDirSize);

        var sumOfDirsWithinLimit = nodes.stream().mapToInt(FileTreeNode::getSize).sum();
        System.out.println(qualifier + ">>> Sum of Directories within limit: " + sumOfDirsWithinLimit);

        final int freeSpaceSize = filesystemSize - root.getSize();
        final int spaceSizeStillNeeded = neededUnusedSpaceSize - freeSpaceSize;
        List<FileTreeNode> candidatesForDeletion = root.filterDirectories(node -> node.getSize() > spaceSizeStillNeeded);
        candidatesForDeletion.sort(Comparator.comparingInt(FileTreeNode::getSize));
        System.out.println(qualifier + ">>> Size of the directory to delete is: "+ candidatesForDeletion.get(0).getSize());
    }

    private static FileTreeNode parseInput(List<String> input) {
        FileTreeNode root = FileTreeNode.createDirectory("/");
        FileTreeNode current = root;

        for (String line : input) {
            if (line.startsWith("$")) {
                // command
                current = handleCommand(line, current);
            } else if (line.startsWith("dir")) {
                // directory entry
                current.addChildNode(handleDirectory(line));
            } else {
                // file entry
                current.addChildNode(handleFile(line));
            }
        }

        return root;
    }

    private static FileTreeNode handleFile(String file) {
        String[] parts = file.split(" ");

        return FileTreeNode.createFile(parts[1], Integer.parseInt(parts[0]));
    }

    private static FileTreeNode handleDirectory(String directory) {
        return FileTreeNode.createDirectory(directory.substring(4));
    }

    private static FileTreeNode handleCommand(String command, FileTreeNode current) {
        if (command.equals("$ cd /")) {
            // skip, is already handled
            return current;
        }

        if (command.equals("$ ls")) {
            // skip, does nothing
            return current;
        }

        return current.getDirectoryWithName(command.substring(5)).orElseThrow();
    }

    private static class FileTreeNode {
        private String name;
        private int size;
        private List<FileTreeNode> nodes;
        private FileTreeNodeType type;
        private FileTreeNode parent;

        public static FileTreeNode createDirectory(String name) {
            return new FileTreeNode(name);
        }

        public static FileTreeNode createFile(String name, int size) {
            return new FileTreeNode(name, size);
        }
        private FileTreeNode(String name) {
            this.name = name;
            this.type = FileTreeNodeType.DIRECTORY;
            this.nodes = new ArrayList<>();
        }

        private FileTreeNode(String name, int size) {
            this.name = name;
            this.size = size;
            this.type = FileTreeNodeType.FILE;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<FileTreeNode> getNodes() {
            return nodes;
        }

        public void setNodes(List<FileTreeNode> nodes) {
            this.nodes = nodes;
        }

        public FileTreeNodeType getType() {
            return type;
        }

        public void setType(FileTreeNodeType type) {
            this.type = type;
        }

        public FileTreeNode getParent() {
            return parent;
        }

        public void setParent(FileTreeNode parent) {
            this.parent = parent;
        }

        public void addChildNode(FileTreeNode node) {
            node.setParent(this);
            this.nodes.add(node);
        }

        public int calcSize() {
            if (type != FileTreeNodeType.FILE) {
                size = nodes.stream().mapToInt(FileTreeNode::calcSize).sum();
            }

            return size;
        }

        public List<FileTreeNode> filterDirectories(Predicate<FileTreeNode> filter) {
            if (size == 0) {
                calcSize();
            }

            if (type == FileTreeNodeType.FILE) {
                return Collections.emptyList();
            } else {
                List<FileTreeNode> result = new ArrayList<>();
                if (filter.test(this)) {
                    result.add(this);
                }
                result.addAll(nodes.stream().map(node -> node.filterDirectories(filter)).flatMap(List::stream).toList());
                return result;
            }
        }

        public Optional<FileTreeNode> getDirectoryWithName(String directoryName) {
            if (directoryName.equals("..")) {
                return Optional.of(parent);
            }

            for (FileTreeNode node : nodes) {
                if (node.getName().equals(directoryName)) {
                    return Optional.of(node);
                }
            }

            return Optional.empty();
        }

        @Override
        public String toString() {
            return "FileTreeNode{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    ", nodes=" + nodes +
                    ", type=" + type +
                    ", parent=" + parent +
                    '}';
        }
    }

    private enum FileTreeNodeType {
        DIRECTORY,
        FILE;
    }
}
