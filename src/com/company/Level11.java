package com.company;

import java.awt.*;

public class Level11 extends SuperLevel {
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    public static BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block5, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Test",5,3,23,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",5,18,20,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",5,3,17,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",5,18,14,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",5,3,11,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",5,18,9,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",5,3,6,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",5,18,3,24,24,BlockHandeler.Block5,new int[] {}),
    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
            new EnemyHandeler.Enemy("Bat",Set(5),Set(1),100),
            new EnemyHandeler.Enemy("Bat2",Set(14),Set(1),1000),
            new EnemyHandeler.Enemy("healthBox",Set(4),Set(20),0),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        if(Globals.LevelConditions[1] == false) {
            Globals.LevelConditions[0] = true;
        } else {
            Globals.LevelConditions[0] = false;
        }
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }

}

