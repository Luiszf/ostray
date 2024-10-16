package com.luis;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

public class GlobalListener {

    GlobalListener(PlayerHandler playerHandler) {
        new Thread(() -> {
            GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(false);
            keyboardHook.addKeyListener(new GlobalKeyListener() {
                @Override
                public void keyPressed(GlobalKeyEvent event) {
                    System.out.println(event.getVirtualKeyCode());
                    if (event.getVirtualKeyCode() == 177) {
                        playerHandler.previousSong();
                    }
                    if (event.getVirtualKeyCode() == 179) {
                        playerHandler.pauseSong();
                    }
                    if (event.getVirtualKeyCode() == 176) {
                        playerHandler.nextSong();
                    }
                    if (event.isWinPressed() && event.getVirtualKeyCode() == 164) {
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
