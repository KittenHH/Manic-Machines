package com.company;

import java.awt.*;

//this contains all the code for the Bomber enemy Script
//they walk around,and if they player touches one, they explode
public class BomberScript {
    Player player = new Player();
    ColisionCheck colisionCheck = new ColisionCheck();
    BomberScript() {}
    //main func
    public EnemyHandeler.Enemy Bomber(EnemyHandeler.Enemy curr, int pos, Graphics g) {
        g.drawImage(curr.image,curr.X,curr.Y,48,48,null);
        curr = MoveX(curr);
        curr = AnimationUpdate(curr);
        curr = MoveY(curr);
        isTouchingPlayer(curr,pos);
        curr = isTouchingBullet(curr,pos);
        return curr;
    }
    //moves on X axis
    public EnemyHandeler.Enemy MoveX(EnemyHandeler.Enemy curr) {
        curr.X = curr.X + curr.DirX;
        return curr;
    }
    //updates animation
    public EnemyHandeler.Enemy AnimationUpdate(EnemyHandeler.Enemy curr) {
        if(Globals.Frame % 10 == 1) {
        if(curr.CurrFrame == 5) {curr.CurrFrame = -1;}
        curr.CurrFrame += 1;}
        int Direction;
        if(curr.DirX > 0) {Direction = 0;} else {Direction = 1;}
        curr.image = curr.EnemyImgArr[Direction][curr.CurrFrame];
        return curr;
    }
    //checks if on block and if not switches direction
    public EnemyHandeler.Enemy MoveY(EnemyHandeler.Enemy curr) {
        int isHit = SpecialBlockCheck(curr.X,curr.Y,curr.Width,curr.Height + 5);
        if(isHit != -1) {
        curr.Y = BlockHandeler.VisibleBlocks[isHit].Y - curr.Height;} else {
            curr.DirX = curr.DirX - curr.DirX*2;
            return curr;
        }
        return curr;
    }
    //checks all block except for moving ones
    public int SpecialBlockCheck(int X, int Y, int H, int W) {
        for(int i = 0;i < BlockHandeler.VisibleBlocks.length;i++) {
            Block curr = BlockHandeler.VisibleBlocks[i];
            if(curr.Type == "YMove") {continue;}
            if(curr.Type == "Pop" && Globals.LevelConditions[curr.ExtraData[0]] == false) {continue;}
            boolean isHit = colisionCheck.Check(X,Y,W,H,curr.X,curr.Y,curr.Width,curr.Height);
            if(isHit) {
                return i;
            }
        }
        return -1;
    }
    public void isTouchingPlayer(EnemyHandeler.Enemy curr, int i) {
        boolean isHit = colisionCheck.Check(curr.X,curr.Y,curr.Width,curr.Height,player.X,player.Y,player.width,player.height);
        if(isHit) {EnemyHandeler.RemoveEnemy = i;player.TakeDamage();}
    }
    public EnemyHandeler.Enemy isTouchingBullet(EnemyHandeler.Enemy curr,int i) {
        boolean isHit = colisionCheck.EnemyBulletCollision(curr.X,curr.Y,curr.Width,curr.Height);
        if(isHit) {curr.Health = curr.Health - 1;curr.DirX += curr.DirX/2;
            PlayerBullet.Hit += 1;
        }
        if(curr.Health <= 0) {
            EnemyHandeler.RemoveEnemy = i;
        }
        return curr;
    }


}
