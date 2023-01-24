package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TurretScript {
    ColisionCheck colisionCheck = new ColisionCheck();
    GamePannel gamePannel = new GamePannel();

    //this will return all the changed variables
    public EnemyHandeler.Enemy Turret(EnemyHandeler.Enemy CurrEnemy, Graphics g, int i) {
        draw(g,CurrEnemy);
        CurrEnemy.Timer = TimeTillFire(CurrEnemy.Timer,CurrEnemy.X,CurrEnemy.Y);
        CurrEnemy = IsHit(CurrEnemy);
        IsDead(i);
        return CurrEnemy;
    }
    //draws the obj
    public void draw(Graphics g,EnemyHandeler.Enemy CurrEnemy) {
        CurrEnemy.image = Graphics(CurrEnemy);
        g.drawImage(CurrEnemy.image, CurrEnemy.X, CurrEnemy.Y,CurrEnemy.Width, CurrEnemy.Height, null);
    }
    //counts down till fire
    public int TimeTillFire(int CurrTime,int x,int y) {
        CurrTime = CurrTime -1;
        if(CurrTime < 0) {
            Main.enemyHandeler.AddBullet(x,y+25,25);
            Main.enemyHandeler.AddBullet(x,y+25,-25);
            CurrTime = 240;}
        return CurrTime;
    }
    //checks if being hit by player bullet
    public EnemyHandeler.Enemy IsHit(EnemyHandeler.Enemy CurrEnemy) {
        boolean IsHit = colisionCheck.EnemyBulletCollision(CurrEnemy.X, CurrEnemy.Y, CurrEnemy.Width, CurrEnemy.Height);
        if (IsHit) {
            PlayerBullet.Hit += 1;
            CurrEnemy.Health += -1;
            CurrEnemy.Timer = 15;
            return CurrEnemy;
        }
        return CurrEnemy;
    }

    //check if the health is zero and kills the enemy
    public void IsDead(int i) {
        if(EnemyHandeler.EnemyList[i].Health > 0) {return;}
        EnemyHandeler.RemoveEnemy = i;
    }
    //this will decide which direction the turrets eyes should be facing, or if he should be in the firing state
    public BufferedImage Graphics(EnemyHandeler.Enemy CurrEnemy) {
        int Facing = 0;
        int Direction = 0;
        if(CurrEnemy.Timer < 60 || CurrEnemy.Timer > 230) {
            return CurrEnemy.EnemyImgArr[0][0];
        }
        if(Player.X > CurrEnemy.X) {Facing = 1;} else {Facing = 2;}
        if(Player.Y > CurrEnemy.Y + CurrEnemy.Height) {Direction = 0;}
        if(Player.Y < CurrEnemy.Y ) {Direction = 2;}
        if(Player.Y/2 > CurrEnemy.Y + CurrEnemy.Height && Player.Y/2 < CurrEnemy.Y) {Direction = 1;}
        return CurrEnemy.EnemyImgArr[Facing][Direction];
    }
    //turret that lazerbeams hole map for period of time
    public static class T2Script {
        ColisionCheck colisionCheck = new ColisionCheck();
        public EnemyHandeler.Enemy T2(Graphics g, EnemyHandeler.Enemy c, int pos) {
            c = tick(c,g);
            c = IsHit(c,pos);
            Draw(g,c);
            return c;
        }
        public void Draw(Graphics g,EnemyHandeler.Enemy c) {
            //decides what image to draw
            //if in fire mode also draws lazer
            if (c.TrajX > 0) {
                g.drawImage(c.EnemyImgArr[0][0],c.X,c.Y,c.Width,c.Height,null);
            }
            else if(Player.X + Player.width/2 > c.X + c.Width/2)
            {
                g.drawImage(c.EnemyImgArr[1][0],c.X,c.Y,c.Width,c.Height,null);
            }
            else {
                g.drawImage(c.EnemyImgArr[1][1],c.X,c.Y,c.Width,c.Height,null);
            }
        }
        //weapon cool down
        public EnemyHandeler.Enemy tick(EnemyHandeler.Enemy c,Graphics g) {
            c.Timer += -1;
            if(c.Timer < 600 && c.Timer > 100) {c = Lazer(c);}
            else if(c.Timer < 100) {c = ReverseLazer(c);}
            if(c.Timer == 0) {c.Timer = 1100; c.TrajX = 0;}
            DrawLazer(g,c);
            return c;
        }
        //this is what the enemy will shoot, a gaint lazer
        public EnemyHandeler.Enemy Lazer(EnemyHandeler.Enemy c) {
            c.TrajX = c.TrajX + 5;
            return c;
        }
        void DrawLazer (Graphics g,EnemyHandeler.Enemy c) {
            //draws the lazer
            g.setColor(new Color(100,0,0));
            g.fillRect(c.X - c.TrajX,c.Y + 25,c.TrajX,c.Height/5);
            g.fillRect( c.X + c.Width,c.Y + 25, c.TrajX ,c.Height/5);
            g.setColor(new Color(200,0,0));
            g.fillRect(c.X - c.TrajX,c.Y + 30,c.TrajX,c.Height/10);
            g.fillRect( c.X + c.Width,c.Y + 30, c.TrajX ,c.Height/10);
            //colosion detection for said lazer
            if(colisionCheck.Check(c.X - c.TrajX,c.Y + 25,c.TrajX,c.Height/3,
Player.X,Player.Y,Player.width,Player.height)
        ||colisionCheck.Check(c.X + c.Width,c.Y + 25, c.TrajX ,c.Height/3,
       Player.X,Player.Y,Player.width,Player.height)) {
                Main.player.TakeDamage();
            }
        }
        //this is what the enemy will shoot, a gaint lazer
        public EnemyHandeler.Enemy ReverseLazer(EnemyHandeler.Enemy c) {
            c.TrajX = c.TrajX - 40;
            return c;
        }
        //checks if being hit by player bullet
        public EnemyHandeler.Enemy IsHit(EnemyHandeler.Enemy CurrEnemy,int pos) {
            PlayerBullet.Hit += 1;
            boolean IsHit = colisionCheck.EnemyBulletCollision(CurrEnemy.X, CurrEnemy.Y, CurrEnemy.Width, CurrEnemy.Height);
            if (IsHit) {
                CurrEnemy.Health += -1;
                CurrEnemy.Timer += -100;
                if(CurrEnemy.Health <= 0) {EnemyHandeler.RemoveEnemy = pos;}
                return CurrEnemy;
            }
            return CurrEnemy;
        }
    }
}