package com.company;

public class Level17 extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block2, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Pass",2,5,9,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",2,23,9,24,24,null,new int[] {}),

            new BlockHandeler.plank("Test",7,0,26,24,24,BlockHandeler.Block3, new int[] {}),
            new BlockHandeler.plank("Test",7,0,27,24,24,BlockHandeler.Block3, new int[] {}),
            new BlockHandeler.plank("Test",7,0,25,24,24,BlockHandeler.Block3, new int[] {}),

            new BlockHandeler.plank("Test",7,22,26,24,24,BlockHandeler.Block3, new int[] {}),
            new BlockHandeler.plank("Test",7,22,27,24,24,BlockHandeler.Block3, new int[] {}),
            new BlockHandeler.plank("Test",7,22,25,24,24,BlockHandeler.Block3, new int[] {}),

            new BlockHandeler.plank("Pop",3,7,20,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",3,19,15,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",3,7,10,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",3,19,5,24,24,null,new int[] {0}),
    };
    @Override
    void LevelCheck() {
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        Globals.LevelConditions[0] = Globals.LevelComplete;

        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }

    @Override
    void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Rocket",Set(5),Set(23),0),
                new EnemyHandeler.Enemy("Bomber",Set(5),Set(8),0),
                new EnemyHandeler.Enemy("Rocket",Set(23),Set(23),0),
                new EnemyHandeler.Enemy("Bomber",Set(23),Set(8),0),
                new EnemyHandeler.Enemy("Frog",Set(27),Set(23),0),
                new EnemyHandeler.Enemy("Frog",Set(4),Set(23),0),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
}
