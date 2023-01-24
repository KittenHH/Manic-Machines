package com.company;

import javax.swing.*;
import java.awt.*;

public class BatScript {
    //makes it so only 2 bats are attacking the player at ounce
    public static boolean BatFlyingCount[] = {};
    public static ColisionCheck colisionCheck = new ColisionCheck();
    Player player = new Player();
    BatScript() {}
    public EnemyHandeler.Enemy Bat(EnemyHandeler.Enemy curr, Graphics g, int i) {
            curr = FlyingAnimation(curr);
        if(isFlying(curr) == false){curr.Timer = curr.Timer -1;g.drawImage(curr.EnemyImgArr[0][0], curr.X,curr.Y,null);}else{
            curr = GetTrajectory(curr);
            curr = Fly(curr);
            curr = Collision(curr,i);
            g.drawImage(curr.EnemyImgArr[1][curr.Frame], curr.X,curr.Y,null);
        }
        return curr;
    }
    //checks if the bat should be flying or not
    boolean isFlying(EnemyHandeler.Enemy curr) {
        if(curr.Timer == 0) {return true;}
        return false;
    }
    //gets speeds for X,Y to go to the player
    EnemyHandeler.Enemy GetTrajectory(EnemyHandeler.Enemy curr) {
    int PlayerCenterX = Player.X + Player.width/2;
    int PlayerCenterY = Player.Y + Player.height/2;
    int EnemyCenterX = curr.X + curr.Width/2;
    int EnemyCenterY = curr.Y + curr.Height/2;

    //checks if should move at all
    int EnemyLeft = curr.X;
    int EnemyRight = curr.X + curr.Width;
    int PlayerLeft = Player.X;
    int PlayerRight = Player.X + Player.width;

    boolean runX = false;
    //checks if Xpos Should Change
    if((PlayerRight < EnemyLeft) || (PlayerLeft > EnemyRight)){runX = true;}
    //actully moves Xpos
    if(runX) {if(EnemyCenterX > PlayerCenterX) {curr.TrajX = -3;} else {curr.TrajX = 3;}}
    //moves Ypos
    if(EnemyCenterY > PlayerCenterY) {curr.TrajY = -3;} else {curr.TrajY = 3;}

    return curr;
    }
    //checks if touching player bullet and checks if touching player
    EnemyHandeler.Enemy Collision(EnemyHandeler.Enemy curr, int i) {
        //checks if touching bullet
        if(colisionCheck.EnemyBulletCollision(curr.X,curr.Y,curr.Width,curr.Height)) {
            curr.Health = curr.Health - 1;
            PlayerBullet.Hit += 1;
        }
        if(curr.Health <= 0) {
            EnemyHandeler.RemoveEnemy = i;
        }
        //checks if touching player
        if (colisionCheck.Check(curr.X, curr.Y, curr.Width, curr.Height, Player.X, Player.Y, Player.width, Player.height)) {
        player.TakeDamage();
        }
        return curr;
    }
    //changes enemy x and y
    EnemyHandeler.Enemy Fly(EnemyHandeler.Enemy curr) {
        curr.X = curr.X + curr.TrajX;
        curr.Y = curr.Y + curr.TrajY;
        return curr;
    }
    //flying animation
    EnemyHandeler.Enemy FlyingAnimation(EnemyHandeler.Enemy curr) {
        if(Globals.Frame % 8 == 1) {if(curr.Frame == 0) {curr.Frame = 1;} else {curr.Frame = 0;}}
        return curr;
    }
    //all the code for the second bat type
    public static class Bat2 {
        Player player = new Player();
        EnemyHandeler.Enemy bat2(EnemyHandeler.Enemy c,Graphics g,int p) {
            if(c.Timer > 0){
                g.drawImage(c.EnemyImgArr[0][0],c.X,c.Y,null);
                c.Timer += -1;
                return c;
            }
            c = Animation(c);
            c = Move(c);
            c = Collision(c, p);
            c = Dash(c);
            g.drawImage(c.EnemyImgArr[1][c.Frame],c.X,c.Y,null);
            return c;
        }
        EnemyHandeler.Enemy Animation(EnemyHandeler.Enemy curr) {
        if(Globals.Frame % 8 == 1) {if(curr.Frame == 0) {curr.Frame = 1;} else {curr.Frame = 0;}}
        return curr;
        }
        EnemyHandeler.Enemy Move(EnemyHandeler.Enemy c) {
            if(c.HasBeenMoved) {return c;}
            c.X += c.DirX;
            c.Y += c.DirY;
            if (c.X > 680 - c.Width || c.X < 20) {c.DirX = c.DirX - c.DirX * 2;}
            if((c.Y > Player.Y  && c.Y < Player.height + Player.Y)
            || (c.Y + c.Height > Player.Y  && c.Y + c.Height< Player.height + Player.Y)) {} else
            {if(c.Y + c.Height/2 > Player.Y + Player.height/2) {c.DirY = -2;} else {c.DirY = 2;}}
            return c;
        }
        EnemyHandeler.Enemy Collision(EnemyHandeler.Enemy c, int pos) {
            if(colisionCheck.EnemyBulletCollision(c.X,c.Y,c.Width,c.Height)) {
                PlayerBullet.Hit += 1;
                c.TrajX = 11;
                c.Health += -1;
                if(c.Health <= 0) {
                    EnemyHandeler.RemoveEnemy = pos;
                }
            }
            if(colisionCheck.Check(c.X,c.Y,c.Width,c.Height,
                    Player.X,Player.Y,Player.width,Player.height)){
                player.TakeDamage();
            }
            return c;}
        EnemyHandeler.Enemy Dash(EnemyHandeler.Enemy c) {
            if(c.TrajX == 0) {c.HasBeenMoved = false; return c;}
            if(Player.X + Player.width/2 > c.X) {
                c.X += c.TrajX;
            }     else {c.X += -1*c.TrajX;}
            c.HasBeenMoved = true;
            c.TrajX += -1;
            return c;
        }
    }
}
