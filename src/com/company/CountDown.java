package com.company;

import java.awt.*;

public class CountDown extends effect{
    int Counter = 5;
    CountDown() {
        Player.IsHualt = true;
        Tick = 300;
        pos = Effects.List.length;
    }
    @Override
    void draw(Graphics g) {
        Animation();
        g.setColor(Color.white);
        g.drawString(String.valueOf(Tick),350,400);
    }

    @Override
    void Animation() {
        Tick += -1;
        if(Tick == 0) {
            Player.IsHualt = false;
            Effects.Remove = pos;
        }
        if(Tick % 100 == 0) {
            Counter = Counter -1;
        }
    }
}
