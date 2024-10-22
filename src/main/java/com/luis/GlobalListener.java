package com.luis;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GlobalListener {


    GlobalListener(PlayerHandler playerHandler) {

        getSettings gs = new getSettings();
        gs.scan();
        var options = gs.options;

        int[] keybinds = new int[5];
        if (options.get(1).isEmpty()) {
            try {
                File optionsFile = new File("./options.txt");
                FileWriter fw = new FileWriter(optionsFile);
                fw.append(options.get(0)).append("\n");
                fw.append("177\n");
                fw.append("179\n");
                fw.append("176\n");
                fw.append("164\n");
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        gs.scan();

        for (int i = 1; i < keybinds.length; i++) {
            var index = Integer.parseInt(options.get(i));
            keybinds[i - 1] = index;
        }


        new Thread(() -> {
            GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(false);

            keyboardHook.addKeyListener(new GlobalKeyListener() {
                @Override
                public void keyPressed(GlobalKeyEvent event) {
                    if (event.getVirtualKeyCode() == keybinds[0]) {
                        playerHandler.previousSong();
                    }
                    if (event.getVirtualKeyCode() == keybinds[1]) {
                        playerHandler.pauseSong();
                    }
                    if (event.getVirtualKeyCode() == keybinds[2]) {
                        playerHandler.nextSong();
                    }
                    if (event.isWinPressed() && event.getVirtualKeyCode() == keybinds[3]) {
                        playerHandler.searchSong();
                    }
                }

                @Override
                public void keyReleased(GlobalKeyEvent event) {
                }
            });
        }).start();
    }
}
