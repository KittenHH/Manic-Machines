package com.company;

import java.awt.*;

public class RocketScript {
    ColisionCheck colisionCheck = new ColisionCheck();
    RocketScript() {}
    public EnemyHandeler.Enemy Draw(Graphics g,EnemyHandeler.Enemy c,int pos)
    {
        if(c.Health == 0) {
            EnemyHandeler.RemoveEnemy = pos;
        }
        if(c.isJumping) {
            g.drawImage(c.EnemyImgArr[1][c.Frame],c.X,c.Y,null);
        } else {
            g.drawImage(c.EnemyImgArr[0][0], c.X, c.Y,  null);
        }
        c = Collision(c);
        if(c.isJumping) {
            c = fly(c);
            IsTouching(c);
        }
        return c;
    }
    //pre take off check
    public EnemyHandeler.Enemy Collision(EnemyHandeler.Enemy c){
        if(c.isJumping) {return c;}
        //checks if touching bullet
        if(colisionCheck.EnemyBulletCollision(c.X,c.Y,c.Width,c.Height)) {
            PlayerBullet.Hit += 1;
            c.Health = c.Health - 1;
            c.isJumping = true;
        }
        return c;
    }
    public EnemyHandeler.Enemy fly(EnemyHandeler.Enemy c) {
        if(Globals.Frame % 5 == 0) {
            c.Frame += 1;
            if(c.Frame > 1) {
                c.Frame = 0;
            }
        }
        c.Y += c.dirY;
        if(c.dirY > -10){ c.dirY -= 0.1;}

        return c;
    }
    //post take off check
    public EnemyHandeler.Enemy IsTouching(EnemyHandeler.Enemy c) {
        for(int i = 0;i < EnemyHandeler.EnemyList.length; i++) {
            EnemyHandeler.Enemy CE = EnemyHandeler.EnemyList[i];
            if(CE == null || CE.Type == "Rocket" || CE.Type == "Blank" || CE.Type == "healthBox" || CE.Type == "Boss") {continue;}
            boolean IsHit = colisionCheck.Check(c.X,c.Y,c.Width,c.Height,CE.X,CE.Y,CE.Width,CE.Height);
            boolean IsHit2;
            //final boss stuff
            if(EnemyHandeler.finalBoss.IsOnline) {
                        IsHit2 = colisionCheck.Check(c.X, c.Y, c.Width, c.Height,
                        EnemyHandeler.finalBoss.X,
                        EnemyHandeler.finalBoss.Y,
                        EnemyHandeler.finalBoss.Width,
                        EnemyHandeler.finalBoss.Height-80);
                if(IsHit2) {
                    c.Health = 0;
                    EnemyHandeler.finalBoss.TakeDamage();
                }
            }
            if(IsHit) {
                c.Health = 0;
                EnemyHandeler.RemoveEnemy = i;
        }

    }return c;
    }
}
