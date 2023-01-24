package com.company;

public class TitleScreen extends SuperLevel{
    BlockHandeler BlockHandeler = new BlockHandeler();
    BlockHandeler.plank[] LevelData = {
            new BlockHandeler.plank("Title",1,0,0,0,0,null,new int[] {})
    };
    @Override
    void LevelCheck() {
        Player.X = -100;
        Player.Y = -100;
        if(PlayerBullet.VisiblePlayerBullets.length > 0){SuperLevel.NextLevel();
    }
    }

    @Override
    void StartLevel() {
        Globals.Level = 0;
        EnemyHandeler.EnemyList = new EnemyHandeler.Enemy[]{};
        BlockHandeler.SetUpArray = LevelData;
        BlockHandeler.SetUpTiles();
        PlayerBullet.VisiblePlayerBullets = new PlayerBullet.PBullet[] {};
    }
}
