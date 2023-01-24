package com.company;

public class Level8 extends SuperLevel{
    Level9 level9 = new Level9();
    Globals globals = new Globals();
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block3, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Test",7,3,14,24,24,BlockHandeler.Block3,new int[]{}),
            new BlockHandeler.plank("Test",7,18,14,24,24,BlockHandeler.Block3,new int[]{}),
            new BlockHandeler.plank("Pop",3,12,23,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Test", 10, 0, 4, 24, 24, BlockHandeler.Block3, new int[]{}),
            new BlockHandeler.plank("Test", 10, 19, 4, 24, 24, BlockHandeler.Block3, new int[]{}),

            new BlockHandeler.plank("Button",1,26,26,35,35,BlockHandeler.ButtonUp,new int[] {0,0}),

            new BlockHandeler.plank("XMove",1,14, 18,24,24,null,new int[] {Set(2),Set(22),2}),
            new BlockHandeler.plank("XMove",1,15, 18,24,24,null,new int[] {Set(3),Set(23),2}),
            new BlockHandeler.plank("XMove",1,16, 18,24,24,null,new int[] {Set(4),Set(24),2}),
            new BlockHandeler.plank("XMove",1,17, 18,24,24,null,new int[] {Set(5),Set(25),2}),
            new BlockHandeler.plank("XMove",1,18, 18,24,24,null,new int[] {Set(6),Set(26),2}),

            new BlockHandeler.plank("XMove",1,14, 9,24,24,null,new int[] {Set(2),Set(22),2}),
            new BlockHandeler.plank("XMove",1,15, 9,24,24,null,new int[] {Set(3),Set(23),2}),
            new BlockHandeler.plank("XMove",1,16, 9,24,24,null,new int[] {Set(4),Set(24),2}),
            new BlockHandeler.plank("XMove",1,17, 9,24,24,null,new int[] {Set(5),Set(25),2}),
            new BlockHandeler.plank("XMove",1,18, 9,24,24,null,new int[] {Set(6),Set(26),2}),
    };

    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("healthBox",Set(14),Set(26),0),
                new EnemyHandeler.Enemy("Box",Set(6),Set(25),0),
                new EnemyHandeler.Enemy("Bat",Set(19),Set(5),1000),
                new EnemyHandeler.Enemy("Frog",Set(25),Set(2),0),
                new EnemyHandeler.Enemy("Bomber",Set(7),Set(13),-2),
                new EnemyHandeler.Enemy("Bomber",Set(21),Set(13),2),

        };
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

