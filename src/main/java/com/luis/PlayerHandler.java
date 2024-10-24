package com.luis;


import com.luis.components.SearchBar;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class PlayerHandler {

    public int index = 0;
    boolean isPaused = true;
    Clip clip = null;
    List<String> songs = null;
    PathHandler pathHandler;


    public List<String> getSongs(String query) {
        pathHandler = new PathHandler();
        songs = pathHandler.queryMusicFiles(query);
        return songs;
    }

    public List<String> addSong(String songPath) {
        songs = pathHandler.addMusicFiles(songPath, index);
        return songs;
    }

    public void getPlayer() {
        try {
            clip = AudioSystem.getClip();

            LineListener myLineListener = event -> {
                System.out.println(event.getType());
                if (event.getType() == LineEvent.Type.STOP) {
                    if (isPaused) return;
                    clip.close();
                    play();
                }
            };
            clip.addLineListener(myLineListener);
            if (songs == null) {
                getSongs("");
                Collections.shuffle(songs);
            }
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        isPaused = false;
        AudioInputStream in = pathToAudioInput(songs.get(index));
        try {
            clip.open(in);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();
        index += 1;
        if (index == songs.size()) {
            index = 0;
        }
    }


    public void pauseSong() {
        isPaused = !isPaused;
        if (!isPaused) {
            clip.start();
        } else {
            clip.stop();
        }
    }

    public void nextSong() {
        isPaused = !isPaused;
        clip.close();
        play();
    }
    public void searchSong() {
        SearchBar searchBar = new SearchBar(this);
    }

    private AudioInputStream pathToAudioInput(String path) {
        File file = new File(path);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        AudioFormat baseAudioFormat = audioInputStream.getFormat();
        AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                baseAudioFormat.getSampleRate(),
                16,
                baseAudioFormat.getChannels(),
                baseAudioFormat.getChannels() * 2,
                baseAudioFormat.getSampleRate(),
                false);
        AudioInputStream in = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);

        return in;
    }

    public void previousSong() {
        if(index > 1) index += -2 ;
        if(index == 0) index = songs.size() - 2;
        if(index == 1) index = songs.size() - 1;
        System.out.println(index);
        clip.close();
        play();
    }
}
