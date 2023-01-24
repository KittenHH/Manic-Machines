package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SmallBoom extends effect{
    SmallBoom(int X,int Y,int POS) {
        x = X;
        y = Y;
        pos = POS;
        CurrFrame = 0;
        Tick = 24;
        try {
            ImgArr = new BufferedImage[] {
       ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\SB_0.png")),
       ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\SB_1.png")),
     ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\SB_2.png")),
      ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\EF\\SB_3.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    void draw(Graphics g) {
        Animation();
        Tick = Tick - 1;
        if(Tick <= 0) {
            Effects.Remove = pos;
        }
        try {
        g.drawImage(ImgArr[CurrFrame],x,y,25,25,null);}
        catch (Exception e) {}
    }

    @Override
    void Animation() {
        if(CurrFrame == 4) {Tick = 0;}
        if(Tick % 6 == 0) {
            CurrFrame += 1;
        }
    }
}

