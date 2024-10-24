package com.luis.components;

import com.luis.getSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsScreen extends JPanel{
    String key = "" ;
    KeyListener kl;
    JTextField prevInput;
    // TODO: implement settings system
    public void build(){

        getSettings gs = new getSettings();

        JFrame settingsWindow = new JFrame();

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
        kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (key.contains(KeyEvent.getKeyText(e.getKeyCode()))) return;
                key = key + " " + KeyEvent.getKeyText(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                prevInput.setText(key);
                prevInput.setFocusable(false);
            }
        };
        FocusListener fl = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                key = "";
                prevInput.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        };
        MouseListener newml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prevInput.setFocusable(true);
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
        Font font = new Font("Serif", Font.PLAIN, 24);
        settingsWindow.setFont(font);
        settingsWindow.setTitle("OSTray Settings");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth()/2;

        Button filePicker = new Button("Choose new file");
        filePicker.setSize(100, 50);
        filePicker.addMouseListener(ml);

        JLabel text = new JLabel("change root file: ");

        JLabel prev = new JLabel("prev");
        prevInput = new JTextField(10);
        prevInput.addKeyListener(kl);
        prevInput.addFocusListener(fl);
        prevInput.addMouseListener(newml);
        JPanel inputPicker = new JPanel();
        inputPicker.add(prev);
        inputPicker.add(prevInput);

        //settingsWindow.getContentPane().add(text);
        //settingsWindow.add(filePicker);



        settingsWindow.add(inputPicker);
        settingsWindow.setLayout(new FlowLayout(FlowLayout.LEFT));
        settingsWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        settingsWindow.setSize(800, 500);
        settingsWindow.setLocation(width - 297, 200);
        settingsWindow.setVisible(true);

    }
}



