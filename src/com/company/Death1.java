package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Death1 extends effect {

    Death1(int X,int Y,int POS) {
        Effects.playSound("EnemieDeath.wav");
        CurrFrame = 0;
        Tick = 37;
        x = X;
        y = Y;
        pos = POS;
        try {
            ImgArr = new BufferedImage[] {
            ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\sprite_0.png")),
            ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\sprite_1.png")),
            ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\sprite_2.png")),
            ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\sprite_3.png")),
            ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\sprite_4.png")),
            ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\sprite_5.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    void Animation() {
        if(CurrFrame == 5) {Tick = 0;}
        if(Tick % 6 == 1) {
            CurrFrame += 1;
        }}
    @Override
    void draw(Graphics g) {
        Animation();
        Tick = Tick - 1;
        if(Tick <= 0) {
            Effects.Remove = pos;
        }
        g.drawImage(ImgArr[CurrFrame],x,y,50,50,null);
    }
}