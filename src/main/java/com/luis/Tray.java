package com.luis;

import java.awt.*;
import java.awt.event.*;

public class Tray extends Component {


    Tray(PlayerHandler playerHandler) {
        SystemTray tray = SystemTray.getSystemTray();
        MediaTracker tracker = new MediaTracker(this);
        Image image = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\ostrayIcon.png");
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        playerHandler.getPlayer(); ActionListener playListener = e -> playerHandler.play(); ActionListener nextButtonListener = e -> playerHandler.nextSong();
        ActionListener prevButtonListener = e -> playerHandler.previousSong();
        ActionListener pauseButtonListener = e -> playerHandler.pauseSong();
        ActionListener closeButtonListener = e -> System.exit(0);
        ActionListener searchButtonListener = e -> {
            playerHandler.searchSong();
        };
        ActionListener settingsButtonListener = e -> {SettingsScreen s = new SettingsScreen();};

        String[] buttonList = {"next", "play", "prev", "pause", "close", "search", "settings"};

        PopupMenu popup = new PopupMenu();

        for (String s : buttonList) {
            MenuItem iItem = new MenuItem(s);
            popup.add(iItem);
        }
        popup.getItem(0).addActionListener(nextButtonListener);
        popup.getItem(1).addActionListener(playListener);
        popup.getItem(2).addActionListener(prevButtonListener);
        popup.getItem(3).addActionListener(pauseButtonListener);
        popup.getItem(4).addActionListener(closeButtonListener);
        popup.getItem(5).addActionListener(searchButtonListener);
        popup.getItem(6).addActionListener(settingsButtonListener);
        TrayIcon trayIcon = new TrayIcon(image, "OSTray", popup);
        trayIcon.addActionListener(playListener);
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println(e);
        }
    }


}
