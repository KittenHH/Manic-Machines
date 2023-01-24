package com.company;

import java.awt.*;

public class CompleteLevelEffect extends effect{
    int pos = Effects.List.length - 1;
    CompleteLevelEffect() {

        Tick = 120;
        Effects.playSound("win.wav");
    }

    @Override
    void draw(Graphics g) {
        if(Tick < 0) {return;}
        Tick = Tick -1;
        if(Globals.Frame % 2 == 0) {return;}
        int X = 0;
        int Y = 100;
        for (int i = 0; i < 29; i++) {
            g.drawImage(BlockHandeler.StopBlock,X,Y,null);
            X = X + 24;
        }
        if(Tick == 0) {
            Effects.Remove = pos;
        }
    }

    @Override
    void Animation() {
    }
}
