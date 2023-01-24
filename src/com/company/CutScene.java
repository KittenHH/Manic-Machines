package com.company;

import com.sun.source.doctree.SeeTree;

public class CutScene extends SuperLevel{
    Globals globals = new Globals();
    Level1 level1 = new Level1();
    BlockHandeler blockHandeler = new BlockHandeler();
    public static BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Panel",1,0,2,0,0,BlockHandeler.Panel1,new int[] {0}),
            new BlockHandeler.plank("Text",1,1,0,0,0,null,new int[] {0,0}),
            new BlockHandeler.plank("Text",1,1,1,0,0,null,new int[] {0,1}),
            new BlockHandeler.plank("Panel",1,15,2,0,0,BlockHandeler.Panel2,new int[] {1}),
            new BlockHandeler.plank("Text",1,15,0,0,0,null,new int[] {1,2}),
            new BlockHandeler.plank("Text",1,15,1,0,0,null,new int[] {1,3}),
            new BlockHandeler.plank("Panel",1,0,17,0,0,BlockHandeler.Panel3,new int[] {2}),
            new BlockHandeler.plank("Text",1,1,16,0,0,null,new int[] {2,4}),
            new BlockHandeler.plank("Text",1,1,17,0,0,null,new int[] {2,5}),
            new BlockHandeler.plank("Panel",1,15,17,0,0,BlockHandeler.Panel4,new int[] {3}),
            new BlockHandeler.plank("Text",1,15,16,0,0,null,new int[] {3,6}),
            new BlockHandeler.plank("Text",1,15,17,0,0,null,new int[] {3,7}),
    };

    @Override void StartLevel() {
        Player.Y = -100;
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
        if(PlayerBullet.VisiblePlayerBullets.length >= 4) {SuperLevel.NextLevel();}
    }


}
