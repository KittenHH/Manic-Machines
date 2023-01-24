package com.company;

public class Level2 extends SuperLevel {
    EnemyHandeler enemyHandeler = new EnemyHandeler();
    BlockHandeler blockHandeler = new BlockHandeler();

   public static BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Test",29,0,28,24,24,BlockHandeler.Block1,new int[] {}),
            new BlockHandeler.plank("Stop",29,0,0,24,24,BlockHandeler.StopBlock,new int[] {}),
            new BlockHandeler.plank("Test",8,10,21,24,24,BlockHandeler.Block1,new int[] {}),
            new BlockHandeler.plank("Test",6,2,15,24,24,BlockHandeler.Block1,new int[] {}),
            new BlockHandeler.plank("Test",6,20,15,24,24,BlockHandeler.Block1,new int[] {}),
            new BlockHandeler.plank("Test",4,12,9,24,24,BlockHandeler.PopBlock,new int[] {0}),
            new BlockHandeler.plank("Test",3,0,5,24,24,BlockHandeler.Block1,new int[] {}),
            new BlockHandeler.plank("Test",3,26,5,24,24,BlockHandeler.Block1,new int[] {}),
};
    public void StartLevel() {
        Globals.LevelComplete = false;
        Globals.ResetLevelConditions();
        BlockHandeler.SetUpArray = LevelData;
        //this sets up all the enemys that will be on screen
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[] {
                new EnemyHandeler.Enemy("Bomber",Set(4),Set(14),-2),
                new EnemyHandeler.Enemy("Bomber",Set(20),Set(14),-2),
                new EnemyHandeler.Enemy("Turret",Set(1),Set(2),0),
                new EnemyHandeler.Enemy("Turret",Set(27),Set(2),0),
        };

        blockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};

    }
    //this checks if the level should be changed
    @Override
    public void LevelCheck() {
        //this loops threw the emeny array
        int bomberCount = 0;
        for (int i = 0; i < EnemyHandeler.EnemyList.length; i++) {
            if (EnemyHandeler.EnemyList[i] == null) {
                continue;
            }
            if (EnemyHandeler.EnemyList[i].Type == "Bomber") {
                bomberCount += 1;
            }
        }
        if (bomberCount > 0) {
            Globals.LevelConditions[0] = false;
        } else {
            Globals.LevelConditions[0] = true;
        }

        Globals.LevelComplete = enemyHandeler.IsThereEnemy();
        if (Player.Y < 100 && Globals.LevelComplete) {
            SuperLevel.NextLevel();
        }
    }


}
