package com.company;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import java.io.File;
import java.io.IOException;



public class Effects {
    public static int Remove = -1;
    Effects() {
    }
    public static effect[] List = {};

    void Draw(Graphics g) {
            Remove = -1;
            for(int i = 0;i < List.length;i++) {
                if(List[i] == null) {continue;}
                List[i].draw(g);
            }
            if(Remove != -1) {List[Remove] = null;}
        }
    static void CreateEffect(effect newEffect) {
        effect[] newArr = new effect[List.length + 1];
        for(int i = 0;i < List.length;i++) {newArr[i] = List[i];}
        newArr[List.length] = newEffect;
        List = newArr;
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();

                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\Sound\\" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e)   {
                    System.err.println(e.getMessage());
                }
            }
        }).start();}}
    abstract class effect {
        int pos;
        int Tick;
        int x;
        int y;
        int w;
        int h;
        String type;

        BufferedImage[] ImgArr;
        BufferedImage image;
        int FrameDelay;
        int CurrFrame;

        abstract void draw(Graphics g);
        abstract void Animation();
    }




