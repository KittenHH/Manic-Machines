package com.company;

import java.awt.*;
import java.awt.image.DirectColorModel;

public class PlayerBullet {

    public static int Hit = 0;
    public static int Miss = -3;

    public static PBullet[] VisiblePlayerBullets;
    public static int RemoveBullet;

    PlayerBullet() {VisiblePlayerBullets = new PBullet[] {};}
    public void AddBullet(int X, int Y, int dir) {
        int Length = VisiblePlayerBullets.length;
        PBullet NewBlock = new PBullet(X,Y,dir);
        PBullet[] newArr = new PBullet[Length + 1];
        for(int i = 0; i < Length; i++) {
            newArr[i] = VisiblePlayerBullets[i];
        }
        newArr[Length] = NewBlock;
        VisiblePlayerBullets = newArr;
    }
    //this loops thew the PB array and draws each
    public void draw(Graphics g) {
        for (int i = 0; i < VisiblePlayerBullets.length; i++) {
            if(VisiblePlayerBullets[i] == null) {continue;}
            if(VisiblePlayerBullets[i].X > 700 || VisiblePlayerBullets[i].X < -35) {
                RemoveBullet = i;
                Miss += 1;
                continue;
            }
            VisiblePlayerBullets[i].draw(g);
        }
    }
    public void RemoveBullet(int Removed) {
        if(Removed == -1) {return;}
        Effects.CreateEffect(new SmallBoom(VisiblePlayerBullets[Removed].X + (VisiblePlayerBullets[Removed].WIDTH/2) ,VisiblePlayerBullets[Removed].Y,Effects.List.length));
        Effects.playSound("Hit.wav");
        VisiblePlayerBullets[Removed] = null;
    }
//this is the sub object this is each player bullet
class PBullet {
    int Direction;
    int X;
    int Y;
    final int WIDTH = 35;
    final int HEIGHT = 5;
    public PBullet(int x, int y, int Dir) {
        X = x;
        Y = y;
        Direction = Dir;
    }
    //draws the bullet and runs all if its respective functions
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(X,Y,WIDTH,HEIGHT);
        X = Move(X,Direction);
    }
    //this moves the bullet
    public int Move(int Xpos, int Dir) {return Xpos - Dir;}
}}
