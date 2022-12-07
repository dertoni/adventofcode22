package day.D7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSystem {


    private final String[] cmds;
    private Dir dir = new Dir();

    public FileSystem() throws IOException {
        Path path = Paths.get("C:\\Users\\273705\\Desktop\\AdventOfCode\\Doku\\Day7\\input.txt");
        List<String> all = new ArrayList<>(Files.readAllLines(path));

        String input = String.join(",", all);
        cmds = input.split("\\$ ");
    }

    public void run() {

        Arrays.asList(cmds).forEach(cmd -> {
            if (!cmd.isBlank()) {
                if (cmd.charAt(0) == 'c') {
                    cd(cmd);
                } else {
                    ls(cmd);
                }
            }
        });
    }

    private void cd(String command) {
        if (command.charAt(3) == '/') {
            goToRoot();
        } else if (command.charAt(3) == '.') {
            goToParent();
        } else {
            goToChild(command.substring(3, command.length() - 1));
        }

    }

    private void ls(String command) {
        String[] localFiles = command.split(",");

        Arrays.asList(localFiles).forEach(localFile -> {

            if (!localFile.equals("ls")) {
                put(localFile);
            }
        });
    }

    public void goToRoot() {
        while (dir.getParent() != null) {
            dir = dir.getParent();
        }

    }

    public void goToParent() {
        dir = dir.getParent();
    }

    public void goToChild(String child) {
        dir = dir.getChild(child);
    }

    public void put(String local) {
        if (local.charAt(0) == 'd') {
            Dir child = new Dir(local.substring(4));
            child.putParent(dir);
            dir.addChild(child);
        } else {
            String[] file = local.split(" ");
            dir.addFile(file[1], Integer.parseInt(file[0]));
        }
    }

    public Dir getCurrent() {
        return dir;
    }

}
