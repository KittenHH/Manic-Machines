package com.company;

public class EndScreen extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("End",1,0,0,0,0,null,new int[] {})
    };
    @Override
    void LevelCheck() {
        Player.X = -100;
        Player.Y = -100;
    }

    @Override
    void StartLevel() {
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[]{};
        //stops timer
        Timer.IsRunning = false;
        Globals.ResetLevelConditions();
        Globals.LevelComplete = false;

        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{};
        blockHandeler.SetUpTiles();
    }
}
