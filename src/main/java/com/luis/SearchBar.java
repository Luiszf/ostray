package com.luis;

import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Locale;

public class SearchBar extends Frame {

    SearchBar(PlayerHandler playerHandler) {

        PathHandler pathHandler = new PathHandler();

        Frame frame = new Frame();

        TextField textField = new TextField();
        List textComponent = new List();

        textField.setBounds(10, 10, 580,80);
        textComponent.setBounds(10,90,580,150);

        Font font = new Font("default", 1,24);

        textField.setFont(font);
        textComponent.setFont(font);
        frame.add(textField);
        frame.add(textComponent);
        frame.setSize(600,250);
        frame.setUndecorated(true);
        frame.setOpacity(0.8f);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth()/2;
        int height = (int) screenSize.getHeight();

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
                textComponent.removeAll();
                String query = textField.getText().toLowerCase(Locale.ROOT);
                var list = playerHandler.getSongs(query);
                for(int i = 0; i < list.size();  i++){
                    if (list.size() <= playerHandler.index + i) return;
                    String formatedPath = Arrays.stream(list.get(playerHandler.index + i).split("\\\\")).toList().getLast();
                    textComponent.add(formatedPath);
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    textComponent.requestFocus();
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
                    String path = pathHandler.rootPath + textComponent.getSelectedItem();
                    playerHandler.addSong(path);
                    playerHandler.nextSong();
                    frame.dispose();
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    String path = pathHandler.rootPath + textComponent.getSelectedItem();
                    playerHandler.addSong(path);
                }
            }
            public void keyReleased(KeyEvent e) {

            }
        };

        textComponent.addKeyListener(selectSong);
        textField.addKeyListener(keyListener);

        textField.requestFocus();
        frame.setLayout(null);

        frame.setVisible(true);
    }
}
