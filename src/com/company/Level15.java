package com.company;

public class Level15 extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block2, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),

            new BlockHandeler.plank("Pass",29,0,24,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",24,0,19,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",24,5,14,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",24,0,9,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",24,5,4,24,24,null,new int[] {}),

    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
            new EnemyHandeler.Enemy("healthBox",Set(10),Set(27),0),
            new EnemyHandeler.Enemy("Bat2",Set(14),Set(1),1000)
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }
}