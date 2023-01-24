package com.company;

public class Level0 extends SuperLevel {
    Globals globals = new Globals();
    Level1 level1 = new Level1();
    BlockHandeler blockHandeler = new BlockHandeler();
    public static BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block1,new int[] {}),

            new BlockHandeler.plank("Test",4,17,8,24,24,BlockHandeler.Block1,new int[] {}),

           new BlockHandeler.plank("Test",4,17,18,24,24,BlockHandeler.Block1,new int[] {}),

            new BlockHandeler.plank("Test",6,10,23,24,24,BlockHandeler.Block1,new int[] {}),

            new BlockHandeler.plank("Test",6,10,13,24,24,BlockHandeler.Block1,new int[] {}),
    };

    @Override void StartLevel() {
        Main.thread.start();
        Player.RenderInfo = true;
        Globals.LevelComplete = false;
        Globals.ResetLevelConditions();
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{};
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        if(Player.Y > 100) {return;}
        SuperLevel.NextLevel();
    }


}
