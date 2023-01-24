package com.company;

public class Level19 extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block4,new int[] {}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Pass",8,10,22,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",4,0,17,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",4,25,17,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",8,10,12,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",3,7,8,24,24,null,new int[] {}),
            new BlockHandeler.plank("Pass",3,18,8,24,24,null,new int[] {}),
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
            new EnemyHandeler.Enemy("healthBox",Set(13),Set(21),0),
            new EnemyHandeler.Enemy("Bat2",Set(4),Set(1),100),
            new EnemyHandeler.Enemy("Bat2",Set(25),Set(1),200),
            new EnemyHandeler.Enemy("Bat2",Set(14),Set(1),300),
                new EnemyHandeler.Enemy("Rocket",Set(14),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(12),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(10),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(8),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(6),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(4),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(2),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(16),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(18),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(20),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(22),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(24),Set(26),0),
                new EnemyHandeler.Enemy("Rocket",Set(26),Set(26),0),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
}
}
