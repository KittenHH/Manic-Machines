package com.company;

public class Level5 extends SuperLevel {
        EnemyHandeler enemyHandeler = new EnemyHandeler();
        BlockHandeler blockHandeler = new BlockHandeler();
        Level6 level6 = new Level6();

    BlockHandeler.plank[] LevelData = {
                new BlockHandeler.plank("YMove",1,14,12,24,24,null,new int[] {Set(12) + 100,Set(29) + 100,-1}),
                new BlockHandeler.plank("YMove",1,15,12,24,24,null,new int[] {Set(12) + 100,Set(29) + 100,-1}),
                new BlockHandeler.plank("YMove",1,16,12,24,24,null,new int[] {Set(12) + 100,Set(29) + 100,-1}),
                new BlockHandeler.plank("YMove",1,13,12,24,24,null,new int[] {Set(12) + 100,Set(29) + 100,-1}),
                new BlockHandeler.plank("YMove",1,17,12,24,24,null,new int[] {Set(12) + 100,Set(29) + 100,-1}),
                new BlockHandeler.plank("YMove",1,18,12,24,24,null,new int[] {Set(12) + 100,Set(29) + 100,-1}),
                new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block2,new int[] {}),
                new BlockHandeler.plank("Test",10,19,12,24,24,BlockHandeler.Block2,new int[] {}),
                new BlockHandeler.plank("Test",13,0,12,24,24,BlockHandeler.Block2,new int[] {}),
                new BlockHandeler.plank("Pop",5,14,7,24,24,BlockHandeler.Block2,new int[] {1}),
                new BlockHandeler.plank("Stop",29,0,0,24,24,BlockHandeler.StopBlock,new int[] {}),
                new BlockHandeler.plank("Button",1,26,10,35,35,BlockHandeler.ButtonUp,new int[] {0,1}),

        };
        @Override void StartLevel() {
            System.out.println("this works?");
            Globals.LevelComplete = false;
            Globals.ResetLevelConditions();
            BlockHandeler.SetUpArray = LevelData;
            //this sets up all the enemys that will be on screen
            EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[] {
                    new EnemyHandeler.Enemy("Box",Set(12),Set(27),0),
                    new EnemyHandeler.Enemy("Bomber",Set(12),Set(11),-2),
                    new EnemyHandeler.Enemy("Bomber",Set(20),Set(11),2),
                    new EnemyHandeler.Enemy("Frog",Set(8),Set(10),0),
                    new EnemyHandeler.Enemy("Frog",Set(4),Set(10),0),
            };
            blockHandeler.SetUpTiles();
            PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
        }
        //this checks if the level should be changed
        @Override void LevelCheck() {
            //this loops threw the emeny array
            for (int i = 0; i < EnemyHandeler.EnemyList.length; i++) {
                if (EnemyHandeler.EnemyList[i] == null) {
                    continue;
                }
            }

            Globals.LevelComplete = enemyHandeler.IsThereEnemy();
            if (Player.Y < 100 && Globals.LevelComplete) {
                SuperLevel.NextLevel();
            }}

    }


