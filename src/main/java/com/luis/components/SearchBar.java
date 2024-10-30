package com.luis.components;

import com.luis.PathHandler;
import com.luis.PlayerHandler;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Locale;

public class SearchBar extends Frame {
    public SearchBar(PlayerHandler playerHandler, PathHandler pathHandler) {

        Frame frame = new Frame();
        Font font = new Font("default", Font.BOLD,24);
        frame.setFont(font);

        TextField textField = new TextField();
        List songList = new List();
        Label label = new Label("Looping");
        Checkbox checkbox = new Checkbox();
        checkbox.setState(playerHandler.isLooping);
        textField.setBounds(10, 10, 580,80);
        label.setBounds(10, 90, 110, 50);
        checkbox.setBounds(120, 90, 30, 50);
        songList.setBounds(10,130,580,150);

        frame.add(textField);
        frame.add(songList);
        frame.add(label);
        frame.add(checkbox);
        frame.setSize(600,300);
        frame.setUndecorated(true);
        frame.setOpacity(0.8f);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth()/2;

        frame.setLocation(width - 297, 200);
        System.out.println(frame.getMaximizedBounds());


        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 27) {
                    frame.dispose();
                }


                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    songList.removeAll();
                    String query = textField.getText().replaceAll("\\s", "").toLowerCase(Locale.ROOT);
                    var list = playerHandler.getSongs(query);
                    for(int i = 0; i < list.size();  i++){
                        if (list.size() <= playerHandler.index + i) return;
                        String formatedPath = Arrays.stream(list.get(playerHandler.index + i).split("\\\\")).toList().getLast();
                        songList.add(formatedPath);
                    }
                    playerHandler.getSongs("");
                }
                if (e.getKeyCode() == KeyEvent.VK_ALT) {
                    checkbox.setState(!checkbox.getState());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        KeyListener selectSong = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String path = pathHandler.rootPath + "\\" + songList.getSelectedItem();
                    playerHandler.addSong(path);
                    playerHandler.nextSong();
                    frame.dispose();
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    String path = pathHandler.rootPath + "\\" + songList.getSelectedItem();
                    playerHandler.addSong(path);
                }
                if (e.getKeyCode() == KeyEvent.VK_ALT) {
                    checkbox.setState(!checkbox.getState());
                }
            }
            public void keyReleased(KeyEvent e) {

            }
        };
        ItemListener checkboxListener = e -> playerHandler.isLooping = checkbox.getState();

        checkbox.addItemListener(checkboxListener);
        songList.addKeyListener(selectSong);
        textField.addKeyListener(keyListener);
        textField.requestFocus();
        frame.setLayout(null);

        frame.setVisible(true);
        textField.requestFocus();
    }
}
