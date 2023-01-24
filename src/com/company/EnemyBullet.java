package com.company;

import java.awt.*;

public class EnemyBullet {
    ColisionCheck colisionCheck = new ColisionCheck();
   // EnemyHandeler enemyHandeler = new EnemyHandeler();
    EnemyBullet() {}
    public EnemyHandeler.Enemy enemyBullet(EnemyHandeler.Enemy currObj, Graphics g) {
        draw(g,currObj.X,currObj.Y,currObj.Width,currObj.Height);
        currObj.X = move(currObj.X,currObj.DirX);
        PlayerObjCheck(currObj);
        return currObj;
    }
    public void draw(Graphics g,int x,int y,int w,int h) {
        g.setColor(Color.red);
        g.fillRect(x,y,w,h);
    }
    public int move(int x, int D) {return x + D/2;}

    public void PlayerObjCheck(EnemyHandeler.Enemy CurrEnemy) {
        boolean IsHit = colisionCheck.Check(CurrEnemy.X,CurrEnemy.Y,CurrEnemy.Width,CurrEnemy.Height,Player.X,Player.Y,Player.width,Player.height);
        if(IsHit) {
            Main.player.TakeDamage();
        }
    }

    }

