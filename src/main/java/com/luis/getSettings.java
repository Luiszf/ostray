package com.luis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class getSettings {

    public ArrayList<String> options = new ArrayList<>();
    File optionsFile = new File("./options.txt");

    public getSettings() {
        options.addAll(List.of("","","","", ""));

        if(!optionsFile.exists()) {
            try {
                optionsFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void scan() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(optionsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int index = 0;
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            options.set(index, s);
            ++index;
        }
        scanner.close();
    }
    public void write() {
        try {
            File optionsFile = new File("./options.txt");
            FileWriter fw = new FileWriter(optionsFile);
            options.forEach(e -> {
                try {
                    fw.append(e).append("\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
