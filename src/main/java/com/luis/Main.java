package com.luis;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.luis.components.Tray;

public class Main {

    public static void main(String[] args) {
        PlayerHandler playerHandler = new PlayerHandler();
        GlobalListener gl = new GlobalListener(playerHandler);
        new Tray(playerHandler, gl);
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(gl);
    }

}

