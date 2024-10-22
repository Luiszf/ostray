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

    FilePicker(boolean scan) {

        getSettings gs = new getSettings();
        if (scan) gs.scan();
        if (!gs.options.getFirst().isEmpty()) {
            chooseDir = gs.options.getFirst();
            return;
        }


        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        chooseDir = new File(dialog.getDirectory()).getAbsolutePath();

        gs.scan();
        gs.options.set(0, chooseDir);
        gs.write();

        dialog.dispose();
    }
}
