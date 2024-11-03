package com.luis;

import com.luis.components.FilePicker;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PathHandler {

    public String rootPath;
    List<String> songs;

    public PathHandler(){
        FilePicker filePicker = new FilePicker(true);
        rootPath = filePicker.chooseDir;
    }


    public List<String> queryMusicFiles(String query) {

        File dic = new File(rootPath);

        FilenameFilter filter = new MyFileFilter(query);

        return Arrays.stream(Objects.requireNonNull(dic.listFiles(filter))).map(File::getAbsolutePath).collect(Collectors.toList());
    }

    public List<String> addMusicFiles(String songPath, int index, List<String> songs) {
        songs.set(index,songPath);
        return songs;
    }
}

class MyFileFilter implements FilenameFilter {

    String query;

    public MyFileFilter(String query) {
        this.query = query.replaceAll("\\s", "").toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean accept(File dir, String name) {
        String formattedName = name.replaceAll("\\s", "").toLowerCase(Locale.ROOT).replace(".mp3", "");
        if (query.isEmpty()) return true;
        var q = query.split("");
        System.out.println(q.length);
        for (int i = 0; i < q.length; i++) {
            int index = formattedName.indexOf(q[i]);
            if (index < 0 ) return false;
            System.out.println(index + "---" + formattedName);
            formattedName = formattedName.substring(index);
        }
        return true;
    }


}