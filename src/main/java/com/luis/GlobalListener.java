package com.luis;

import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class GlobalListener implements NativeKeyListener {

    public ArrayList<Integer> keyPressed = new ArrayList<>();
    getSettings gs = new getSettings();
    PlayerHandler playerHandler;

    public GlobalListener(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (keyPressed.contains(e.getKeyCode())) return;
        keyPressed.add(e.getKeyCode());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        gs.scan();
        if (optionsToKeybind(gs.options.get(1)).toString().equals(keyPressed.toString())){
            playerHandler.previousSong();
        }
        if (optionsToKeybind(gs.options.get(2)).toString().equals(keyPressed.toString())){
            playerHandler.play();
        }
        if (optionsToKeybind(gs.options.get(3)).toString().equals(keyPressed.toString())){
            playerHandler.nextSong();
        }
        if (optionsToKeybind(gs.options.get(4)).toString().equals(keyPressed.toString())){
            playerHandler.searchSong();
        }
        keyPressed.clear();
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    private ArrayList<Integer> optionsToKeybind(String options) {
        if (options.isEmpty()) return new ArrayList<>(5);
        List<String> s = Arrays.stream(options.split(",")).toList();
        var y = s.stream().map(Integer::parseInt).toList();
        return new ArrayList<>(y);

    }
}