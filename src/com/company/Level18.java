package com.company;

public class Level18 extends SuperLevel{
    int[] SpeedData = new int[12];
    boolean PreviousFrame = false;
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {

            new BlockHandeler.plank("XMove",1,13,23,24,24,null,new int[] {Set(3),Set(26),-10}),
            new BlockHandeler.plank("XMove",1,14,23,24,24,null,new int[] {Set(4),Set(27),-10}),
            new BlockHandeler.plank("XMove",1,15,23,24,24,null,new int[] {Set(5),Set(28),-10}),

            new BlockHandeler.plank("XMove",1,13,18,24,24,null,new int[] {Set(0),Set(23),10}),
            new BlockHandeler.plank("XMove",1,14,18,24,24,null,new int[] {Set(1),Set(24),10}),
            new BlockHandeler.plank("XMove",1,15,18,24,24,null,new int[] {Set(2),Set(25),10}),

            new BlockHandeler.plank("XMove",1,13,13,24,24,null,new int[] {Set(3),Set(26),-10}),
            new BlockHandeler.plank("XMove",1,14,13,24,24,null,new int[] {Set(4),Set(27),-10}),
            new BlockHandeler.plank("XMove",1,15,13,24,24,null,new int[] {Set(5),Set(28),-10}),

            new BlockHandeler.plank("XMove",1,13,8,24,24,null,new int[] {Set(0),Set(23),10}),
            new BlockHandeler.plank("XMove",1,14,8,24,24,null,new int[] {Set(1),Set(24),10}),
            new BlockHandeler.plank("XMove",1,15,8,24,24,null,new int[] {Set(2),Set(25),10}),

            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block5,new int[] {}),
            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Test",3,0,23,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Test",3,26,18,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Test",3,0,13,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Test",3,26,8,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Pass",29,0,3,24,24,BlockHandeler.Block3,new int[] {}),
            new BlockHandeler.plank("Button",1,5,26,35,35,BlockHandeler.ButtonUp,new int[] {0,0}),
    };
    @Override
    void LevelCheck() {
        if(Globals.LevelConditions[0] == false && PreviousFrame) {
            StarUpAgain();
        }
        if(Globals.LevelConditions[0]) {
            StopMovers();
        }
        DumpXMoveSpeeds();
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();

        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
        //checks stores the state of Level conditions of previous frame
        PreviousFrame = Globals.LevelConditions[0];
    }

    @Override
    void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Box",Set(18),Set(26),0),
                new EnemyHandeler.Enemy("T2",Set(1),Set(10),0),
                new EnemyHandeler.Enemy("Turret",Set(28),Set(15),0),
                new EnemyHandeler.Enemy("Bat2",Set(14),Set(4),1000),
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //puts all the XMove blocks speed var into an array
    void DumpXMoveSpeeds() {
        if(BlockHandeler.VisibleBlocks.length < 29) {return;}
        if(Globals.LevelConditions[0]) {return;}
        for(int i = 0; i < 12;i++) {
            try {
            SpeedData[i] = BlockHandeler.VisibleBlocks[i].ExtraData[2];}
            catch (Exception e) {}
        }
    }
    //hualts all XMove Blocks
    void StopMovers() {

        for (int i = 0; i < 12; i++) {
            BlockHandeler.VisibleBlocks[i].ExtraData[2] = 0;
        }
    }
    //puts back the XMove speed
    void StarUpAgain(){
        for (int i = 0; i < 12; i++) {
            BlockHandeler.VisibleBlocks[i].ExtraData[2] = SpeedData[i];
        }
    }
}

