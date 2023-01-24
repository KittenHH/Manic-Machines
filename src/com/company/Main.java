package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Main {
    public static boolean IsPause = false;
    //yes Ik the code is shit, don't judge me T-T
    public static Player player = new Player();

    public static EnemyHandeler enemyHandeler = new EnemyHandeler();
    public static Effects effects = new Effects();
    public static BlockHandeler blockHandeler = new BlockHandeler();
    public static PlayerBullet playerBullet = new PlayerBullet();
    public static Thread thread = new Thread(new Timer());

    public static void main(String[] args) {

        Globals.Frame = 0;
        new GameWindow();
        Globals.frame.getContentPane().add(new GamePannel());
        Globals.frame.pack();
        Globals.Running = true;
        //this begins the test level
        SuperLevel.LevelArr[0].StartLevel();
        Globals.Level = 0;
        Main.blockHandeler.SetUpTiles();
        while(Globals.Running) {
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Globals.frame.getContentPane().removeAll();
            Globals.frame.getContentPane().add(new GamePannel());
            SuperLevel.LevelArr[Globals.Level].LevelCheck();
            Globals.frame.pack();
            Globals.Frame += 1;

        }
    }
}