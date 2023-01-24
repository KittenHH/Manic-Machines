package com.company;

public class BossRoom extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block2, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
    };
    @Override
    void LevelCheck() {
        if(Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }

    @Override
    void StartLevel() {
        Player.RenderInfo = true;
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Boss",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
                new EnemyHandeler.Enemy("Blank",0,0,0),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
}

