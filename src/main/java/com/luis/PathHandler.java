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


    List<String> queryMusicFiles(String query) {

        File dic = new File(rootPath);

        FilenameFilter filter = new MyFileFilter(query.replaceAll("\\s", ""));

        songs = Arrays.stream(Objects.requireNonNull(dic.listFiles(filter))).map(File::getAbsolutePath).collect(Collectors.toList());
        return songs;
    }

    public List<String> addMusicFiles(String songPath, int index) {
        songs.set(index,songPath);
        return songs;
    }
}

class MyFileFilter implements FilenameFilter {

    String query;

    public MyFileFilter(String query) {
        this.query = query;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase(Locale.ROOT).trim().contains(query) && name.endsWith(".mp3");
    }


}