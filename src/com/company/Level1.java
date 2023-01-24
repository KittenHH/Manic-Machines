package com.company;

public class Level1 extends SuperLevel {
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    BlockHandeler blockHandeler = new BlockHandeler();
    Level2 level2 = new Level2();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block1,new int[] {}),
            new BlockHandeler.plank("Stop",29,0,0,24,24,BlockHandeler.StopBlock,new int[] {}),
            new BlockHandeler.plank("YMove",1,17,5,24,24,BlockHandeler.Block1, new int[] {Set(5) + 100,Set(26) + 100,-5})
            ,new BlockHandeler.plank("YMove",1,18,5,24,24,BlockHandeler.Block1, new int[] {Set(5) + 100,Set(26) + 100,-5})
            ,new BlockHandeler.plank("YMove",1,19,5,24,24,BlockHandeler.Block1, new int[] {Set(5) + 100,Set(26) + 100,-5}),
            new BlockHandeler.plank("Test",10,0,14,24,24,BlockHandeler.Block1,new int[] {}),};
    @Override void StartLevel() {
        Globals.LevelComplete = false;

        Globals.ResetLevelConditions();
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]
                {new EnemyHandeler.Enemy("Turret",Set(1),Set(11),0)};
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete ) {
            SuperLevel.NextLevel();
        }
    }


}
