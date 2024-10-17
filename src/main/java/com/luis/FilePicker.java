package com.luis;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FilePicker {

    String chooseDir;
    FilePicker() {
        try {
            File options = new File("./options.txt");
            if (options.exists()){
                Scanner scanner = new Scanner(options);
                chooseDir= scanner.nextLine();
                scanner.close();
            };
            if (chooseDir != null) return;
        } catch (FileNotFoundException | NoSuchElementException e) {
            System.out.println("Options file not found\n error :" + e);
        }
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        chooseDir = new File(dialog.getDirectory()).getAbsolutePath();
        try {
            File options = new File("./options.txt");
            FileWriter fw = new FileWriter(options);
            fw.write(chooseDir);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dialog.dispose();
    }
}
