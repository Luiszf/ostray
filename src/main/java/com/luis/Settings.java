package com.luis;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class Settings extends Frame {
    // TODO: implement settings system
    Settings(){

        Frame settingsWindow = new Frame();
        settingsWindow.setSize(800, 500);

        WindowListener wl = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                settingsWindow.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };

        settingsWindow.setTitle("OSTray Settings");
        settingsWindow.addWindowListener(wl);

        settingsWindow.setLayout(null);
        settingsWindow.setVisible(true);
    }
}