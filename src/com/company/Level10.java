package com.company;

import java.awt.*;

public class Level10 extends SuperLevel {
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block1, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Pop",29,0,0,24,24,BlockHandeler.PopBlock, new int[]{0}),

            new BlockHandeler.plank("Test",5,0,25,24,24, BlockHandeler.Block3,new int[]{}),
            new BlockHandeler.plank("Test",2,27,20,24,24, BlockHandeler.Block3,new int[]{}),
            new BlockHandeler.plank("Test",2,0,15,24,24, BlockHandeler.Block3,new int[]{}),
            new BlockHandeler.plank("Test",2,27,10,24,24, BlockHandeler.Block3,new int[]{}),
            new BlockHandeler.plank("Test",5,0,5,24,24, BlockHandeler.Block3,new int[]{}),


new BlockHandeler.plank("XMove",1,22,20,24,24,null,new int[] {Set(0),Set(22),-6}),
new BlockHandeler.plank("XMove",1,23,20,24,24,null,new int[] {Set(1),Set(23),-6}),
new BlockHandeler.plank("XMove",1,24,20,24,24,null,new int[] {Set(2),Set(24),-6}),
new BlockHandeler.plank("XMove",1,25,20,24,24,null,new int[] {Set(3),Set(25),-6}),
new BlockHandeler.plank("XMove",1,26,20,24,24,null,new int[] {Set(4),Set(26),-6}),

new BlockHandeler.plank("XMove",1,2,15,24,24,null,new int[] {Set(2),Set(24),-6}),
new BlockHandeler.plank("XMove",1,3,15,24,24,null,new int[] {Set(3),Set(25),-6}),
new BlockHandeler.plank("XMove",1,4,15,24,24,null,new int[] {Set(4),Set(26),-6}),
new BlockHandeler.plank("XMove",1,5,15,24,24,null,new int[] {Set(5),Set(27),-6}),
new BlockHandeler.plank("XMove",1,6,15,24,24,null,new int[] {Set(6),Set(28),-6}),

new BlockHandeler.plank("XMove",1,11,10,24,24,null,new int[] {Set(0),Set(11),-3}),
new BlockHandeler.plank("XMove",1,12,10,24,24,null,new int[] {Set(1),Set(12),-3}),
new BlockHandeler.plank("XMove",1,13,10,24,24,null,new int[] {Set(2),Set(13),-3}),

new BlockHandeler.plank("XMove",1,24,10,24,24,null,new int[] {Set(11),Set(24),-3}),
new BlockHandeler.plank("XMove",1,25,10,24,24,null,new int[] {Set(12),Set(25),-3}),
new BlockHandeler.plank("XMove",1,26,10,24,24,null,new int[] {Set(13),Set(26),-3}),

new BlockHandeler.plank("Button",1,23,26,35,35,BlockHandeler.ButtonUp,new int[] {0,1})
    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
            new EnemyHandeler.Enemy("Box",Set(3),Set(2),0),
            new EnemyHandeler.Enemy("Turret",Set(28),Set(17),0),
            new EnemyHandeler.Enemy("Turret",Set(0),Set(12),0),
            new EnemyHandeler.Enemy("Turret",Set(28),Set(7),0),
            new EnemyHandeler.Enemy("Bat",Set(10),Set(1),500),
            new EnemyHandeler.Enemy("healthBox",Set(12),Set(26),0)
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

