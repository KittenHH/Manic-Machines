package com.company;

public class Level14 extends SuperLevel{
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("YMove",1,15,14,24,24,null,new int[] {Set(6) + 100,Set(14) + 100,-3}),
            new BlockHandeler.plank("YMove",1,16,14,24,24,null,new int[] {Set(6) + 100,Set(14) + 100,-3}),
            new BlockHandeler.plank("YMove",1,17,14,24,24,null,new int[] {Set(6) + 100,Set(14) + 100,-3}),
            new BlockHandeler.plank("YMove",1,18,14,24,24,null,new int[] {Set(6) + 100,Set(14) + 100,-3}),
            new BlockHandeler.plank("YMove",1,19,14,24,24,null,new int[] {Set(6) + 100,Set(14) + 100,-3}),

            new BlockHandeler.plank("XMove",1,0,14,24,24,null,new int[] {Set(0),Set(18),-3}),
            new BlockHandeler.plank("XMove",1,1,14,24,24,null,new int[] {Set(1),Set(19),-3}),
            new BlockHandeler.plank("XMove",1,2,14,24,24,null,new int[] {Set(2),Set(20),-3}),
            new BlockHandeler.plank("XMove",1,3,14,24,24,null,new int[] {Set(3),Set(21),-3}),
            new BlockHandeler.plank("XMove",1,4,14,24,24,null,new int[] {Set(4),Set(22),-3}),
            new BlockHandeler.plank("XMove",1,5,14,24,24,null,new int[] {Set(5),Set(23),-3}),

            new BlockHandeler.plank("YMove",1,1,27,24,24,null,new int[] {Set(14) + 100,Set(27) + 100,-3}),
            new BlockHandeler.plank("YMove",1,2,27,24,24,null,new int[] {Set(14) + 100,Set(27) + 100,-3}),
            new BlockHandeler.plank("YMove",1,3,27,24,24,null,new int[] {Set(14) + 100,Set(27) + 100,-3}),
            new BlockHandeler.plank("YMove",1,4,27,24,24,null,new int[] {Set(14) + 100,Set(27) + 100,-3}),
            new BlockHandeler.plank("YMove",1,5,27,24,24,null,new int[] {Set(14) + 100,Set(27) + 100,-3}),

            new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block4, new int[]{}),

            new BlockHandeler.plank("Pop",4,25,24,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",1,25,27,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",1,25,26,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",1,25,25,24,24,null,new int[] {0}),

            new BlockHandeler.plank("Pop",4,25,10,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",1,25,11,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",1,25,12,24,24,null,new int[] {0}),
            new BlockHandeler.plank("Pop",1,25,13,24,24,null,new int[] {0}),

            new BlockHandeler.plank("Test", 5,24,14,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Test",9,20,7,24,24,BlockHandeler.Block2,new int[] {}),
            new BlockHandeler.plank("Button",1,26,5,35,35,BlockHandeler.ButtonUp,new int[] {0,1})
    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Turret",Set(27),Set(25),0),
                new EnemyHandeler.Enemy("Turret",Set(27),Set(11),0),
                new EnemyHandeler.Enemy("Box",Set(16),Set(25),0),
                new EnemyHandeler.Enemy("Bat",Set(2),Set(1),500),
                new EnemyHandeler.Enemy("healthBox", Set(25),Set(22),0)
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        Globals.LevelConditions[0] = true;
        if(Globals.LevelConditions[1]) {
            Globals.LevelConditions[0] = false;
        }
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }
}
