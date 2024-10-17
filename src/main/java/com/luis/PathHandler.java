package com.luis;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PathHandler {

    String rootPath;
    List<String> songs;

    PathHandler(){

        FilePicker filePicker = new FilePicker();
        rootPath = filePicker.chooseDir;
        System.out.println(rootPath);
    }


    List<String> queryMusicFiles(String query) {

        File dic = new File(rootPath);

        FilenameFilter filter = new MyFileFilter(query.trim());

        songs = Arrays.stream(dic.listFiles(filter)).map(e -> e.getAbsolutePath()).collect(Collectors.toList());

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
        return name.toLowerCase(Locale.ROOT).contains(query) && name.endsWith(".mp3");
    }


}