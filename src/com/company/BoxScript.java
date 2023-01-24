package com.company;

import java.awt.*;

//this handles the wooden crate obj
public class BoxScript {
    ColisionCheck colisionCheck = new ColisionCheck();
    BoxScript() {}
    public EnemyHandeler.Enemy boxScript(EnemyHandeler.Enemy currEnemy, Graphics g) {
        draw(g,currEnemy);
        currEnemy = ColisionDetection(currEnemy);
        currEnemy = PhysicsX(currEnemy);
        currEnemy = PhysicsY(currEnemy);
        currEnemy = OffScreenCheck(currEnemy);
        return currEnemy;
    }
    public void draw(Graphics g, EnemyHandeler.Enemy currEnemy) {
        g.drawImage(currEnemy.image,currEnemy.X,currEnemy.Y,currEnemy.Width,currEnemy.Height,null);
    }
    public EnemyHandeler.Enemy ColisionDetection(EnemyHandeler.Enemy currEnemy) {
        //checks if player is touching
        int isHit = colisionCheck.SpecialCheck(
                currEnemy.X,currEnemy.Y,currEnemy.Width,currEnemy.Height,Player.X,Player.Y,Player.width,Player.height);
        if(isHit == -1) {return currEnemy;}
        Player.SpeedX = 0;
        switch(isHit) {
            case 0:
                currEnemy.DirX = 11;
            break;
            case 1:
                currEnemy.DirX = -11;
            break;
        }
        return currEnemy;
    }
    //dose the hole push thingy
    public EnemyHandeler.Enemy PhysicsX(EnemyHandeler.Enemy currEnemy) {
        if(currEnemy.DirX == 0) {return currEnemy;}
        currEnemy.X = currEnemy.X + currEnemy.DirX;
        if(currEnemy.DirX > 0) {currEnemy.DirX = currEnemy.DirX -1;} else {currEnemy.DirX += 1;}
        return currEnemy;
    }
    //dose the falling n stuff
    public EnemyHandeler.Enemy PhysicsY(EnemyHandeler.Enemy curr) {
        int isHit = colisionCheck.BlockCheck(curr.X,curr.Y,curr.Width,curr.Height);
        if(isHit == -1) {curr.DirY += 1; curr.Y += curr.DirY; return curr;}
        curr.DirY = 0;curr.Y = BlockHandeler.VisibleBlocks[isHit].Y - curr.Height;
        return curr;
    }
    //checks if offscreen and dose something abt it
    public EnemyHandeler.Enemy OffScreenCheck(EnemyHandeler.Enemy curr) {
        if(curr.DirX == 0) {return curr;}
        //if about to be pushed offscreen it reverses the motion
        if(curr.X + curr.Width > GameWindow.W) {curr.X = Player.X - curr.Width;}
        else if(curr.X < 0) {curr.X = Player.X + curr.Width;}

        return curr;
    }
}
