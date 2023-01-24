package com.company;

import javax.swing.*;

public class Globals extends JFrame {
    //these are responsible for keeping track of the level
    public static Boolean[] LevelConditions = {false,false,false,false,false,false,false,false,false};
    public static Boolean LevelComplete = false;
    public static int Level = 0;
    public static int[][] LevelData;
    public static boolean IsLoading = false;
    public static int Frame;
    //this for the window n stuff
    public static JFrame frame;
    public static boolean Running;
    //this resests all level conditions
    public static void ResetLevelConditions() {
        for(int i = 0;i < LevelConditions.length;i++) {
            LevelConditions[i] = false;
        }
    }
    public static boolean isBarf = false;
    //this stops player movement for a certain amount of time
    public static void GameStop() {
        if(Globals.Frame < 120) {
            Globals.IsLoading = true;
        } else {Globals.IsLoading = false;}
    }
}
