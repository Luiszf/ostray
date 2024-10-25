package com.luis.components;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.luis.getSettings;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class InputAdapter {

    String key = "";
    String keyCodes = "";

    InputAdapter(JTextField textField, List<Integer> keyPressed, getSettings gs, int bind){

        MouseListener inputMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setFocusable(true);
                textField.requestFocus();
            }
        };
        KeyListener kl = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressed.forEach(element -> {
                    String keyName = NativeKeyEvent.getKeyText(element);
                    if (key.contains(keyName)) return;
                    keyCodes = keyCodes + element + ",";
                    key = key + " " + keyName;
                });
                textField.setText(key);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                textField.setFocusable(false);
                gs.scan();
                gs.options.set(bind, keyCodes);
                gs.write();
                keyCodes = "";
                key = "";
            }
        };
        textField.addMouseListener(inputMouseListener);
        textField.addKeyListener(kl);
    }
}