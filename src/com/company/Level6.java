package com.company;

public class Level6 extends SuperLevel{
    Level7 level7 = new Level7();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
     BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("YMove",1,21,18,24,24,null,new int[] {Set(18) + 100,Set(29) + 100,-1}),
            new BlockHandeler.plank("YMove",1,22,18,24,24,null,new int[] {Set(18) + 100,Set(29) + 100,-1}),
            new BlockHandeler.plank("YMove",1,23,18,24,24,null,new int[] {Set(18) + 100,Set(29) + 100,-1}),
            new BlockHandeler.plank("YMove",1,24,18,24,24,null,new int[] {Set(18) + 100,Set(29) + 100,-1}),
            new BlockHandeler.plank("YMove",1,25,18,24,24,null,new int[] {Set(18) + 100,Set(29) + 100,-1}),
            new BlockHandeler.plank("YMove",1,26,18,24,24,null,new int[] {Set(18) + 100,Set(29) + 100,-1}),

            new BlockHandeler.plank("YMove",1,11,6,24,24,null,new int[] {Set(6) + 100,Set(18) + 100,-1}),
            new BlockHandeler.plank("YMove",1,10,6,24,24,null,new int[] {Set(6) + 100,Set(18) + 100,-1}),
            new BlockHandeler.plank("YMove",1,9,6,24,24,null,new int[] {Set(6) + 100,Set(18) + 100,-1}),
            new BlockHandeler.plank("YMove",1,8,6,24,24,null,new int[] {Set(6) + 100,Set(18) + 100,-1}),
            new BlockHandeler.plank("YMove",1,7,6,24,24,null,new int[] {Set(6) + 100,Set(18) + 100,-1}),
            new BlockHandeler.plank("YMove",1,6,6,24,24,null,new int[] {Set(6) + 100,Set(18) + 100,-1}),
            new BlockHandeler.plank("YMove",1,5,6,24,24,null,new int[] {Set(6) + 100,Set(18) + 100,-1}),

            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Stop",29,0,0,24,24,BlockHandeler.StopBlock, new int[] {}),

            new BlockHandeler.plank("Test",5,0,6,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",17,12,6,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",5,0,18,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",9,12,18,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",2,27,18,24,24,BlockHandeler.Block2,new int[] {}),

            new BlockHandeler.plank("Button",1,20,4,35,35,BlockHandeler.ButtonUp,new int[] {0,0})
    };

    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Box",Set(12),Set(24),0),
                new EnemyHandeler.Enemy("Bomber",Set(2),Set(27),-2),
                new EnemyHandeler.Enemy("Bomber",Set(14),Set(17),-2),
                new EnemyHandeler.Enemy("Frog",Set(3),Set(4),0),
                new EnemyHandeler.Enemy("Turret",Set(3),Set(15),0),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};

    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        Globals.LevelComplete = Globals.LevelConditions[0];
        if(Player.Y > 100 || Globals.LevelComplete == false) {return;}
        SuperLevel.NextLevel();
    }

}
