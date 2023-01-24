package com.company;

import javax.naming.event.EventContext;

public class Level3 extends SuperLevel {
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    BlockHandeler blockHandeler = new BlockHandeler();
    public static BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Stop",29,0,0,24,24,BlockHandeler.StopBlock,new int[] {}),
            new BlockHandeler.plank("Test",5,0,6,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",18,9,22,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Button",1,22,26,35,35,BlockHandeler.ButtonUp,new int[] {0,0}),
            new BlockHandeler.plank("Pop",6,20,15,24,24,BlockHandeler.Block2,new int[] {0}),
            new BlockHandeler.plank("Pop",6,22,10,24,24,BlockHandeler.Block2,new int[] {0}),
            new BlockHandeler.plank("Pop",4,24,5,24,24,BlockHandeler.Block2,new int[] {0}),
    };
    @Override void StartLevel() {
        Globals.LevelComplete = false;
        Globals.ResetLevelConditions();
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[] {
                new EnemyHandeler.Enemy("Frog",Set(3),Set(4),0),
                new EnemyHandeler.Enemy("healthBox",Set(18),Set(19),0),
                new EnemyHandeler.Enemy("Box",Set(14),Set(17),0)
        };
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
        blockHandeler.SetUpTiles();
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }

}
