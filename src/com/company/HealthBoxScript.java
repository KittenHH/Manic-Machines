package com.company;

import java.awt.*;

public class HealthBoxScript extends Effects {
    ColisionCheck colisionCheck = new ColisionCheck();
    HealthBoxScript() {}
    public EnemyHandeler.Enemy HealthBox(EnemyHandeler.Enemy curr, Graphics g, int i) {
        //draws it
        g.drawImage(curr.image, curr.X,curr.Y,null);
        PlayerCheck(curr,i);
        curr = FloorCheck(curr);
        return curr;
    }
    //checks if player is touching
    public void PlayerCheck(EnemyHandeler.Enemy curr, int i) {
        boolean isHit = colisionCheck.Check(Player.X,Player.Y,Player.width,Player.height,curr.X,curr.Y,curr.Width,curr.Height);
        if(isHit && Player.PlayerHealth < 4) {
            Player.PlayerHealth += 1;
            EnemyHandeler.RemoveEnemy = i;
            Effects.playSound("PickUp.wav");
        }
    }
    //checks if touching a block and sets its y pos accordingly so it dosent look like it fazing threw the floor
    public EnemyHandeler.Enemy FloorCheck(EnemyHandeler.Enemy curr) {
        int i = colisionCheck.BlockCheck(curr.X,curr.Y,curr.Width,curr.Height);
        if(i == -1) {return curr;}
        curr.Y = BlockHandeler.VisibleBlocks[i].Y - curr.Height;
        return curr;
    }
}
