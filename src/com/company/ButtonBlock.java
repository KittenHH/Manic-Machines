package com.company;

public class ButtonBlock {
    ColisionCheck colisionCheck = new ColisionCheck();

    ButtonBlock() {}
    //this checks if a player or obj is touching the button
    public int IsDown(int x,int y,int w,int h) {
        boolean isHit = colisionCheck.Check(x,y,w,h,Player.X,Player.Y,Player.width,Player.height);
        boolean isHit2 = colisionCheck.AllObjCheck(x,y,w,h);
        if(isHit || isHit2) {
            return 1;
        }
        return 0;

    }
    //runs the same check but without the AllObjCheck
    public int PIsDown(int x,int y,int w,int h) {
        boolean isHit = colisionCheck.Check(x,y,w,h,Player.X,Player.Y,Player.width,Player.height);
        if(isHit) {return 1;} else {return 0;}
    }
        }
