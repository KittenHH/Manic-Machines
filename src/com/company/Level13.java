package com.company;

public class Level13 extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block4, new int[]{}),
new BlockHandeler.plank("Test",7,21,24,24,24,BlockHandeler.Block5, new int[]{}),
new BlockHandeler.plank("Test",8,0,13,24,24,BlockHandeler.Block5, new int[]{}),
new BlockHandeler.plank("Test",5,23,13,24,24,BlockHandeler.Block5,new int[] {}),
new BlockHandeler.plank("Test",4,12,7,24,24,BlockHandeler.Block5,new int[] {}),

new BlockHandeler.plank("XMove",1,0,19,24,24,null,new int[]{Set(0),Set(23),6}),
new BlockHandeler.plank("XMove",1,1,19,24,24,null,new int[]{Set(1),Set(24),6}),
new BlockHandeler.plank("XMove",1,2,19,24,24,null,new int[]{Set(2),Set(25),6}),
new BlockHandeler.plank("XMove",1,3,19,24,24,null,new int[]{Set(3),Set(26),6}),
new BlockHandeler.plank("XMove",1,4,19,24,24,null,new int[]{Set(4),Set(27),6}),
    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("T2",Set(2),Set(10),0),
                new EnemyHandeler.Enemy("Bomber",Set(23),Set(23),-2),
                new EnemyHandeler.Enemy("Turret",Set(14),Set(4),0)
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
