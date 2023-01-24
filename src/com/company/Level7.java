package com.company;

public class Level7 extends SuperLevel {
    Level8 level8 = new Level8();
    Globals globals = new Globals();
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    BlockHandeler blockHandeler = new BlockHandeler();
    public static BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Stop",29,0,0,24,24,BlockHandeler.StopBlock, new int[] {}),
            new BlockHandeler.plank("Test",5,5,10,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Test",5,12,22,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Test",5,18,10,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Pop",3,13,16,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",3,0,6,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",3,26,6,24,24,null,new int[] {0}),
    };

    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Bat",Set(7),Set(11),100),
                new EnemyHandeler.Enemy("Bomber",Set(7),Set(27),-2),
                new EnemyHandeler.Enemy("Bat",Set(20),Set(11),400),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};

    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        Globals.LevelConditions[0] = Globals.LevelComplete;
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && globals.LevelComplete) {
            SuperLevel.NextLevel();
        }

    }


}
