package com.company;

public class Level16 extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("XMove",1,7,23,24,24,null,new int[] {Set(7),Set(17),-3}),
            new BlockHandeler.plank("XMove",1,8,23,24,24,null,new int[] {Set(8),Set(18),-3}),
            new BlockHandeler.plank("XMove",1,9,23,24,24,null,new int[] {Set(9),Set(19),-3}),
            new BlockHandeler.plank("XMove",1,10,23,24,24,null,new int[] {Set(10),Set(20),-3}),
            new BlockHandeler.plank("XMove",1,11,23,24,24,null,new int[] {Set(11),Set(21),-3}),

            new BlockHandeler.plank("YMove",1,4,14,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),
            new BlockHandeler.plank("YMove",1,5,14,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),
            new BlockHandeler.plank("YMove",1,6,14,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),
            new BlockHandeler.plank("YMove",1,7,14,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),

            new BlockHandeler.plank("YMove",1,21,23,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),
            new BlockHandeler.plank("YMove",1,22,23,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),
            new BlockHandeler.plank("YMove",1,23,23,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),
            new BlockHandeler.plank("YMove",1,24,23,24,24,null,new int[]{Set(7)+100,Set(23)+100,-3}),

            new BlockHandeler.plank("Test", 29, 0, 28, 24, 24, BlockHandeler.Block2, new int[]{}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Pop",29,0,1,24,24,null,new int[] {0}),

            new BlockHandeler.plank("Button",1,3,5,35,35,BlockHandeler.ButtonUp,new int[] {0,3}),
            new BlockHandeler.plank("Button",1,27,12,35,35,BlockHandeler.ButtonUp,new int[] {1,1}),
            new BlockHandeler.plank("Button",1,2,26,35,35,BlockHandeler.ButtonUp,new int[]{2,5}),

            new BlockHandeler.plank("Test",5,12,14,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Test",4,0,7,24,24,BlockHandeler.Block4,new int[] {}),
            new BlockHandeler.plank("Test",4,25,14,24,24,BlockHandeler.Block4,new int[] {}),

            new BlockHandeler.plank("Pop",5,12,9,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,12,10,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,12,11,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,12,12,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,12,13,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,16,10,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,16,11,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,16,12,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,16,13,24,24,null,new int[] {2}),

            new BlockHandeler.plank("Pop",4,25,11,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,25,12,24,24,null,new int[] {2}),
            new BlockHandeler.plank("Pop",1,25,13,24,24,null,new int[] {2}),
    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Box",Set(13),Set(12),0),
                new EnemyHandeler.Enemy("Box",Set(26),Set(8),0),
                new EnemyHandeler.Enemy("T2",Set(14),Set(11),0),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        if(Globals.LevelConditions[1]) {Globals.LevelConditions[0] = false;} else {Globals.LevelConditions[0] = true;}
        if(Globals.LevelConditions[3]) {Globals.LevelConditions[2] = false;} else {Globals.LevelConditions[2] = true;}
        if(Globals.LevelConditions[5]) {
            EnemyHandeler.EnemyList[1].X = Set(26);
            EnemyHandeler.EnemyList[1].Y = Set(8);
            EnemyHandeler.EnemyList[0].X = Set(13);
            EnemyHandeler.EnemyList[0].Y = Set(16);
        }
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }
}
