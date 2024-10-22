package com.luis;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PlayerHandler playerHandler = new PlayerHandler();
        Tray tray = new Tray(playerHandler);
        GlobalListener gl = new GlobalListener(playerHandler);
    }

}

