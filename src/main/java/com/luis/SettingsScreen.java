package com.luis;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class SettingsScreen extends Frame {
    // TODO: implement settings system
    SettingsScreen(){

        getSettings gs = new getSettings();

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

        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FilePicker filePicker = new FilePicker(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        settingsWindow.setTitle("OSTray Settings");
        settingsWindow.addWindowListener(wl);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth()/2;

        Button filePicker = new Button("Choose new file");
        filePicker.setSize(100, 50);
        filePicker.setBounds(400, 200, 100, 50);
        filePicker.addMouseListener(ml);

        settingsWindow.add(filePicker);
        settingsWindow.setLocation(width - 297, 200);
        settingsWindow.setLayout(null);
        settingsWindow.setVisible(true);
    }
}