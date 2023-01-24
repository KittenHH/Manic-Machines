package com.company;

import java.awt.*;

public class Level9 extends SuperLevel {
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    Level10 level10 = new Level10();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("YMove",1,1,6,24,24,null,new int[] {Set(6) + 100,Set(29) + 100,-2}),
            new BlockHandeler.plank("YMove",1,4,6,24,24,null,new int[] {Set(6) + 100,Set(29) + 100,-2}),
            new BlockHandeler.plank("YMove",1,2,6,24,24,null,new int[] {Set(6) + 100,Set(29) + 100,-2}),
            new BlockHandeler.plank("YMove",1,3,6,24,24,null,new int[] {Set(6) + 100,Set(29) + 100,-2}),
            new BlockHandeler.plank("YMove",1,4,6,24,24,null,new int[] {Set(6) + 100,Set(29) + 100,-2}),
            new BlockHandeler.plank("YMove",1,5,6,24,24,null,new int[] {Set(6) + 100,Set(29) + 100,-2}),
            new BlockHandeler.plank("YMove",1,6,6,24,24,null,new int[] {Set(6) + 100,Set(29) + 100,-2}),

            new BlockHandeler.plank("XMove",1,7,9,24,24,null,new int[] {Set(7),Set(19),-2}),
            new BlockHandeler.plank("XMove",1,8,9,24,24,null,new int[] {Set(8),Set(20),-2}),
            new BlockHandeler.plank("XMove",1,9,9,24,24,null,new int[] {Set(9),Set(21),-2}),
            new BlockHandeler.plank("XMove",1,10,9,24,24,null,new int[] {Set(10) ,Set(22) ,-2}),
            new BlockHandeler.plank("XMove",1,11,9,24,24,null,new int[] {Set(11) ,Set(23) ,-2}),

            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block2, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),

            new BlockHandeler.plank("Test",5,24,15 ,24,24,BlockHandeler.Block1,new int[]{}),
            new BlockHandeler.plank("Test",5,24,19 ,24,24,BlockHandeler.Block1,new int[]{}),
            new BlockHandeler.plank("Button",1,26,13,35,35,BlockHandeler.ButtonUp,new int [] {0,0}),

            new BlockHandeler.plank("Pop",3,18,25,24,24,BlockHandeler.PopBlock,new int[] {0}),
            new BlockHandeler.plank("Pop",3,21,19,24,24,BlockHandeler.PopBlock,new int[] {0}),
            new BlockHandeler.plank("Pop",29,0,1,24,24,BlockHandeler.PopBlock,new int[] {1}),

            new BlockHandeler.plank("Pop",1,22,18,24,24,null,new int[] {1}),
            new BlockHandeler.plank("Pop",1,22,17,24,24,null,new int[] {1}),
            new BlockHandeler.plank("Pop",1,22,16,24,24,null,new int[] {1}),


    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Box",Set(14),Set(26),0),
                new EnemyHandeler.Enemy("Bomber",Set(26),Set(18),2),
                new EnemyHandeler.Enemy("Bat",Set(1),Set(2),300),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        if(Globals.LevelConditions[0] == false) {
            Globals.LevelConditions[1] = true;
        } else {
            Globals.LevelConditions[1] = false;
        }
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }
}