package com.luis.components;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.luis.GlobalListener;
import com.luis.getSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class SettingsScreen extends JPanel{

    getSettings gs = new getSettings();

    public void build(GlobalListener globalListener){


        JFrame settingsWindow = new JFrame();

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FilePicker(false);
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
        JLabel play = new JLabel("play");
        JLabel next = new JLabel("next");
        JLabel search = new JLabel("search");
        JTextField prevInput = new JTextField(20);
        JTextField playInput = new JTextField(20);
        JTextField nextInput = new JTextField(20);
        JTextField searchInput = new JTextField(20);
        gs.scan();
        prevInput.setText(keyCodesToString(gs.options.get(1)));
        playInput.setText(keyCodesToString(gs.options.get(2)));
        nextInput.setText(keyCodesToString(gs.options.get(3)));
        searchInput.setText(keyCodesToString(gs.options.get(4)));
        new InputAdapter(prevInput, globalListener.keyPressed, gs, 1);
        new InputAdapter(playInput, globalListener.keyPressed, gs, 2);
        new InputAdapter(nextInput, globalListener.keyPressed, gs, 3);
        new InputAdapter(searchInput, globalListener.keyPressed, gs, 4);
        JPanel playInputPicker = new JPanel();
        JPanel prevInputPicker = new JPanel();
        JPanel nextInputPicker = new JPanel();
        JPanel searchInputPicker = new JPanel();
        prevInputPicker.add(prev);
        prevInputPicker.add(prevInput);
        playInputPicker.add(play);
        playInputPicker.add(playInput);
        nextInputPicker.add(next);
        nextInputPicker.add(nextInput);
        searchInputPicker.add(search);
        searchInputPicker.add(searchInput);
        settingsWindow.getContentPane().add(text);
        settingsWindow.add(filePicker);

        settingsWindow.add(prevInputPicker);
        settingsWindow.add(playInputPicker);
        settingsWindow.add(nextInputPicker);
        settingsWindow.add(searchInputPicker);
        settingsWindow.setLayout(new GridLayout(6, 1, 2, 2 ));
        settingsWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        settingsWindow.setSize(800, 500);
        settingsWindow.setLocation(width - 297, 200);
        settingsWindow.setVisible(true);

    }

    String keyCodesToString(String keycodes) {
        StringBuilder formatted = new StringBuilder();
        var keycodeList = Arrays.stream(keycodes.split(",")).toList();
        for (String s : keycodeList) {
            if (s.isEmpty()) break;
            int element = Integer.parseInt(s.trim());
            formatted.append(NativeKeyEvent.getKeyText(element)).append(" ");
        }
        return formatted.toString();
    }
}



