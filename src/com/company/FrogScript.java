package com.company;

import java.awt.*;
import java.util.Currency;
import java.lang.Integer;

public class FrogScript {
    private ColisionCheck colisionCheck = new ColisionCheck();
    FrogScript() {}
    public EnemyHandeler.Enemy FrogScript(EnemyHandeler.Enemy currEnemy, Graphics g, int i) {
        draw(g,currEnemy);
        currEnemy = Tick(currEnemy);
        currEnemy = JumpingPhysics(currEnemy);
        currEnemy = PbCheck(currEnemy, i);
        currEnemy = BlockJumpCheck(currEnemy);

        return currEnemy;
    }
    //this subtracts the enemy's timer by 1
    public EnemyHandeler.Enemy Tick(EnemyHandeler.Enemy currEnemy) {
        currEnemy.Timer = currEnemy.Timer - 1;
        //if 0 then jump
        if(currEnemy.Timer == 0) {
            //jump
            currEnemy = Jump(currEnemy);
            currEnemy.Frame = 0;
            currEnemy.Timer = 120;
        }
        return currEnemy;
    }
    //draws the sprite on screen
    public void draw(Graphics g,EnemyHandeler.Enemy currEnemy) {
        int image1;
        int image2;
        if(currEnemy.isJumping) {
            image1 = 1;
            if(currEnemy.DirX > 0) {image2 = 0;} else {image2 = 1;}
        } else {
            image1 = 0;
            if(currEnemy.X > Player.X) {image2 = 0;} else {image2 = 1;}
        }
        currEnemy.image= currEnemy.EnemyImgArr[image1][image2];
        g.drawImage(currEnemy.image,currEnemy.X,currEnemy.Y,currEnemy.Width,currEnemy.Height,null);
    }
    //innits the jumping prosses
    public EnemyHandeler.Enemy Jump(EnemyHandeler.Enemy currEnemy) {
        //calculates the x distance the Obj needs to jump every frame
        if(Player.X > currEnemy.X) {
            int Distance = Player.X - currEnemy.X;
            currEnemy.DirX = Distance/52;
        } else {
            int Distance = currEnemy.X - Player.X;
            currEnemy.DirX = Distance/52;
            currEnemy.DirX = currEnemy.DirX - currEnemy.DirX*2;
        }
        currEnemy.isJumping = true;
        currEnemy.DirY = 25;
        return currEnemy;
    }
    public EnemyHandeler.Enemy JumpingPhysics(EnemyHandeler.Enemy currEnemy) {
        if(currEnemy.isJumping == false) {return currEnemy;}
        currEnemy.Y = currEnemy.Y - currEnemy.DirY;
        currEnemy.Frame += 1;
        currEnemy.DirY = currEnemy.DirY - 1;
        currEnemy.X += currEnemy.DirX;
        if(currEnemy.Y > 29*24 +100 - currEnemy.Height) {
            currEnemy.DirY = 0;
            currEnemy.Y = 29*24 +100 - currEnemy.Height;
            currEnemy.isJumping = false;
        }
        return currEnemy;
    }
    //checks if the player bullet is hitting the obj
    public EnemyHandeler.Enemy PbCheck(EnemyHandeler.Enemy currEnemy, int i) {
        boolean isHit = colisionCheck.EnemyBulletCollision(currEnemy.X,currEnemy.Y,currEnemy.Width,currEnemy.Height);
        if(isHit) {
            PlayerBullet.Hit += 1;
            currEnemy.Health = currEnemy.Health - 1;
            currEnemy = Jump(currEnemy);
            currEnemy.Frame = 0;
            currEnemy.Timer = 120;
            if(currEnemy.Health <= 0) {
            EnemyHandeler.RemoveEnemy = i;}
        }
        return currEnemy;
    }
    //checks if hitting block and stops jumping
    public EnemyHandeler.Enemy BlockJumpCheck(EnemyHandeler.Enemy currEnemy) {
        if(currEnemy.DirY > 0 || Player.Y > currEnemy.Y) {return currEnemy;}
        boolean isHit = SpecialBlockCheck(currEnemy);
        if(isHit) {
            int Touching = colisionCheck.BlockCheck(currEnemy.X,currEnemy.Y,currEnemy.Width,currEnemy.Height);
            currEnemy.DirY = 0;
            currEnemy.Y = SetY(Touching,currEnemy);
            currEnemy.isJumping = false;
        }
        return currEnemy;
    }
    //sets the enemies Y position
    public int SetY(int block,EnemyHandeler.Enemy curr) {
        curr.Y = BlockHandeler.VisibleBlocks[block].Y - curr.Height;
        return curr.Y;
    }
    //checks every block except for invisible ones
    boolean SpecialBlockCheck(EnemyHandeler.Enemy curr) {
        for(int i = 0;i < BlockHandeler.VisibleBlocks.length;i++) {
            Block currBlock = BlockHandeler.VisibleBlocks[i];
            if(currBlock.Type == "Pop" && Globals.LevelConditions[currBlock.ExtraData[0]] == false) {continue;}
            boolean isHit = colisionCheck.Check(
                    currBlock.X, currBlock.Y, currBlock.Width, currBlock.Height, curr.X, curr.Y, curr.Width, curr.Height);
            if(isHit) {
                return true;
            }
        }
        return false;
    }
    }

