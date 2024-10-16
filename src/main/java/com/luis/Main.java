package com.luis;


import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import org.tritonus.share.GlobalInfo;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PlayerHandler playerHandler = new PlayerHandler();
        Tray tray = new Tray(playerHandler);
        GlobalListener gl = new GlobalListener(playerHandler);
    }

}