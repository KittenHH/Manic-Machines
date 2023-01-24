package com.company;

public class Level4 extends SuperLevel {
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    BlockHandeler blockHandeler = new BlockHandeler();

    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Stop",29,0,0,24,24,BlockHandeler.StopBlock,new int[] {}),
            new BlockHandeler.plank("YMove",1,18,24,24,24,BlockHandeler.StopBlock,new int[] {Set(5) + 100,Set(26) + 100,-3}),
            new BlockHandeler.plank("YMove",1,17,24,24,24,BlockHandeler.StopBlock,new int[] {Set(5) + 100,Set(26)+ 100,-3}),
            new BlockHandeler.plank("YMove",1,16,24,24,24,BlockHandeler.StopBlock,new int[] {Set(5) + 100,Set(26)+ 100,-3}),
            new BlockHandeler.plank("YMove",1,20,5,24,24,BlockHandeler.StopBlock,new int[] {Set(5) + 100,Set(26)+ 100,3}),
            new BlockHandeler.plank("YMove",1,21,5,24,24,BlockHandeler.StopBlock,new int[] {Set(5) + 100,Set(26)+ 100,3}),
            new BlockHandeler.plank("YMove",1,22,5,24,24,BlockHandeler.StopBlock,new int[] {Set(5)+ 100,Set(26)+ 100,3}),
            new BlockHandeler.plank("Test",3,0,5,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",3,0,10,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",3,0,15,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",3,0,20,24,24,BlockHandeler.Block2,new int[] {}),
    };
    @Override void StartLevel() {
        Globals.LevelComplete = false;


        Globals.ResetLevelConditions();
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[] {
                new EnemyHandeler.Enemy("Turret",Set(28),Set(25),0),
                new EnemyHandeler.Enemy("Turret",Set(1),Set(2),0),
                new EnemyHandeler.Enemy("Turret",Set(1),Set(7),0),
                new EnemyHandeler.Enemy("Turret",Set(1),Set(12),0),
                new EnemyHandeler.Enemy("Turret",Set(1),Set(17),0),
                new EnemyHandeler.Enemy("healthBox",Set(1),Set(25),0),

        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete ) {
            SuperLevel.NextLevel();
        }}
    public int Set(int Pos) {return Pos*24;}

}
