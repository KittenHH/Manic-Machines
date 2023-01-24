package com.company;

public class Level12 extends SuperLevel {
    int[] arr =  {Set(4) + 100,Set(28) + 100,-5};
    int xPos1 = 28;
    int xPos2 = 25;
    int xPos3 = 23;
    int xPos4 = 20;
    int xPos5 = 25;

    EnemyHandeler enemyHandeler = new EnemyHandeler();
    Globals globals = new Globals();
    BlockHandeler blockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
  new BlockHandeler.plank("Stop", 29, 0, 0, 24, 24, BlockHandeler.StopBlock, new int[]{}),
  new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block5, new int[]{}),
  new BlockHandeler.plank("YMove", 1,0,xPos1,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),
  new BlockHandeler.plank("YMove", 1,1,xPos1,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),
   new BlockHandeler.plank("YMove", 1,8,xPos2,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),
new BlockHandeler.plank("YMove", 1,9,xPos2,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),
new BlockHandeler.plank("YMove", 1,10,xPos2,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),
new BlockHandeler.plank("YMove", 1,11,xPos2,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),
new BlockHandeler.plank("YMove", 1,13,xPos3,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),
new BlockHandeler.plank("YMove", 1,14,xPos3,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),
new BlockHandeler.plank("YMove", 1,15,xPos3,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),
new BlockHandeler.plank("YMove", 1,16,xPos3,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),
            new BlockHandeler.plank("YMove", 1,19,xPos4,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),
            new BlockHandeler.plank("YMove", 1,20,xPos4,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),
            new BlockHandeler.plank("YMove", 1,21,xPos4,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),
            new BlockHandeler.plank("YMove", 1,22,xPos4,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,5}),

            new BlockHandeler.plank("YMove", 1,28,xPos5,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),
            new BlockHandeler.plank("YMove", 1,29,xPos5,24,24,null,new int[] {Set(4) + 100,Set(28) + 100,-5}),

    };
    @Override void StartLevel() {
        globals.ResetLevelConditions();
        Globals.LevelComplete = false;
        BlockHandeler.SetUpArray = LevelData;

        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{
                new EnemyHandeler.Enemy("Bat",Set(10),Set(1),100),
                new EnemyHandeler.Enemy("Bat2",Set(18),Set(1),500),
                new EnemyHandeler.Enemy("Bat",Set(5),Set(1),1000),
                new EnemyHandeler.Enemy("healthBox",Set(13),Set(13),0)
        };
        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
    //this checks if the level should be changed
    @Override void LevelCheck() {
        if(Globals.LevelConditions[1] == false) {
            Globals.LevelConditions[0] = true;
        } else {
            Globals.LevelConditions[0] = false;
        }
        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if(Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }
}

