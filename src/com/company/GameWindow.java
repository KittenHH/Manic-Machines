package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameWindow extends JFrame implements KeyListener, MouseListener {
    public static Player player = new Player();

    public static int W;
    public static int H;
    GameWindow() {
        GameWindow.W = 700;
        GameWindow.H = 800;
        Globals.frame = new JFrame("Manic Machines");
        Globals.frame.setSize(GameWindow.W,GameWindow.H);
        Globals.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Globals.frame.setVisible(true);
        Globals.frame.addKeyListener(this);
        Globals.frame.addMouseListener(this);
        Globals.frame.setLocationRelativeTo(null);

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            Player.L = true;
        //    Player.MovementArray[0] = 10;
            Player.Facing = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            Player.R = true;
    //        Player.MovementArray[1] = -10;
            Player.Facing = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Player.SpaceBarDown = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_COMMA) {
            SuperLevel.NextLevel();
        }
        if(e.getKeyCode() == KeyEvent.VK_P) {
            if(Main.IsPause) {Main.IsPause = false;} else {Main.IsPause = true;}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            Player.L = false;
            Player.MovementArray[1] = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            Player.R = false;
            Player.MovementArray[0] = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Player.SpaceBarDown = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            player.Fire();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        player.Fire();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
