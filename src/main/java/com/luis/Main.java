package com.luis;


import com.luis.components.SettingsScreen;
import com.luis.components.Tray;

public class Main {

    public static void main(String[] args) {
        PlayerHandler playerHandler = new PlayerHandler();
        Tray tray = new Tray(playerHandler);
        GlobalListener gl = new GlobalListener(playerHandler);
        var s = new SettingsScreen();
        s.build();
    }

}

