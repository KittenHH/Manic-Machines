package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnemyHandeler {
    public static boolean SummonDrops = true;
    static FinalBoss finalBoss = new FinalBoss();
    //this imports all the enemy scripts
    BomberScript bomberScript = new BomberScript();
    TurretScript turretScript = new TurretScript();
    EnemyBullet Bullet = new EnemyBullet();
    FrogScript gorillaScript = new FrogScript();
    BoxScript boxScript = new BoxScript();
    BatScript batScript = new BatScript();
    BatScript.Bat2 Bat2 = new BatScript.Bat2();
    TurretScript.T2Script t2 = new TurretScript.T2Script();
    RocketScript rocketScript = new RocketScript();
    Random rn = new Random();

    HealthBoxScript healthBoxScript = new HealthBoxScript();
    //this imports stuff for collision detection
    ColisionCheck colisionCheck = new ColisionCheck();
    //this will handle all indexing of the enemy list, removing, add, and redrawing
    public static Enemy[] EnemyList = {};
    public static int RemoveEnemy = -1;
    EnemyHandeler() {
    }
    //this draws all enemies on screen and runs there functions
    public void draw(Graphics g) {
        if(Player.IsHualt) {return;}
        if(EnemyList.length == 0) {return;}
        for(int i = 0;i < EnemyList.length;i++) {
            Enemy Current = EnemyList[i];
            if(EnemyList[i] != null){EnemyList[i].HasBeenMoved = false;}
            if(Current == null) {continue;}
            switch (Current.Type) {
                //this checks the type and runs specifc scripts for each type
                case "Bomber":
                    EnemyList[i]= bomberScript.Bomber(EnemyList[i],i,g);
                break;
                case "Turret":
                    EnemyList[i] = turretScript.Turret(EnemyList[i],g, i);
                    isHittingPlayer(EnemyList[i]);
                break;
                case "Bullet":
                    EnemyList[i] = Bullet.enemyBullet(EnemyList[i],g);
                break;
                case "Frog":
                    EnemyList[i] = gorillaScript.FrogScript(EnemyList[i], g, i);
                    isHittingPlayer(EnemyList[i]);
                break;
                case "Box":
                    EnemyList[i] = boxScript.boxScript(EnemyList[i],g);
                break;
                case "healthBox":
                    EnemyList[i] = healthBoxScript.HealthBox(EnemyList[i], g, i);
                break;
                case "Bat":
                    EnemyList[i] = batScript.Bat(EnemyList[i],g,i);
                break;
                case "Bat2":
                    EnemyList[i] = Bat2.bat2(EnemyList[i],g,i);
                break;
                case "T2":
                    EnemyList[i] = t2.T2(g,EnemyList[i],i);
                break;
                case"Rocket":
                    EnemyList[i] = rocketScript.Draw(g,EnemyList[i],i);
                break;
                case"Boss":
                    finalBoss.Draw(g);
                break;
            }
        }
    }
    //this will drop a health Item when enemy killed
    public void AddHealthDrop(int x, int y) {
        if(!SummonDrops) {return;}
        Enemy newObj = new Enemy("healthBox",x,y,0);
        Enemy[] newArr = new Enemy[EnemyList.length + 1];
        for(int i = 0; i < EnemyList.length; i++) {newArr[i] = EnemyList[i];}
        newArr[EnemyList.length] = newObj;
        EnemyList = newArr;
    }

    public void AddBullet(int x, int y,int DirX) {
        Enemy newObj = new Enemy("Bullet",x,y,DirX);
        Enemy[] newArr = new Enemy[EnemyList.length + 1];
        for(int i = 0; i < EnemyList.length; i++) {
            newArr[i] = EnemyList[i];
        }
        newArr[EnemyList.length] = newObj;
        EnemyList = newArr;
    }
    public static void SpawnEnemy(int X,int Y,String Type) {
        EnemyHandeler.Enemy New = new Enemy(Type,X,Y,0);
        EnemyHandeler.Enemy[]  NewArr = new Enemy[EnemyList.length + 1];
        for(int i = 0;i < EnemyList.length;i++) {
            NewArr[i] = EnemyList[i];
        }
        NewArr[EnemyList.length] = New;
        EnemyList = NewArr;
    }

    public void removeEnemy(int Removed) {
        if (Removed == -1) {return;}
        if(EnemyList[Removed].Type != "healthBox") {
        //chance of drop health
        int chance = (int) (Math.random() * 10) + 1;
        Effects.CreateEffect(new Death1(EnemyList[Removed].X,EnemyList[Removed].Y,Effects.List.length));
        if(chance > 7) {
            AddHealthDrop(EnemyList[Removed].X,EnemyList[Removed].Y - EnemyList[Removed].Height);
        }}
        EnemyList[Removed] = null;
    }
    //this checks if the player is hitting an enemy and takes damage as a result
    public void isHittingPlayer(EnemyHandeler.Enemy currEnemy) {
       boolean IsHit =  colisionCheck.Check(currEnemy.X,currEnemy.Y,currEnemy.Width,currEnemy.Height,Player.X,Player.Y,Player.width,Player.height);
       if(IsHit) {
           Main.player.TakeDamage();
       }
    }


    //this checks if all the enemies on screen are dead
    public boolean IsThereEnemy() {
        Boolean is = true;
        for(int i = 0;i < EnemyList.length;i++) {
            Enemy curr = EnemyList[i];
            if(curr == null) {continue;}
            if(curr.Type == "Box" || curr.Type == "Bullet" || curr.Type == "healthBox" || curr.Type == "Rocket") {continue;} else {is = false;break;}
        }
        return is;
    }

    //this is the subClass for each enemy
    static class Enemy {
        String Type;
        int X;
        int Y;
        int Health;
        //this is extra vars that will be declaired depending on the enemy type
        int CoolDown;
        int DirX;
        int DirY;
        int Width;
        int Height;
        int Timer;
        int Frame = 0;
        float dirY;
        boolean isJumping = false;
        boolean isResting;
        boolean HasBeenMoved = false;
        boolean isFiring = false;

        //vars exclusivly for the bat used for caling where to fly to
        int TrajX;
        int TrajY;
        int Speical;


        //these are for the enemies graphics
        BufferedImage[][] EnemyImgArr = {{}};
        BufferedImage image;
        int FrameDelay;
        int CurrFrame;

    public Enemy(String type,int x, int y,int dirX) {
        X = x;
        Y = y;
        Type = type;
        if(Type != "Bullet") {Y += 100;}
        DelcaireType(dirX);
    }

        Enemy() {
        }


        //this gets the type and adds variables accordingly
    void DelcaireType(int SpecialVars) {
        switch (Type) {
            case "Bomber":
                DirX = SpecialVars;
                DirY = 0;
                Width = 48;
                Height = 48;
                Health = 4;
                FrameDelay = 12;
                CurrFrame = (int)Math.floor(Math.random()*(5-0+1)+0);
                image = null;
                //0 is right 1 is left
                EnemyImgArr = new BufferedImage[][] {{null,null,null,null,null,null},{null,null,null,null,null,null}};
                try {
                    EnemyImgArr[0][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotRight1.png"));
                    EnemyImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomerBotRight2.png"));
                    EnemyImgArr[0][2] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotRight3.png"));
                    EnemyImgArr[0][3] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotRight4.png"));
                    EnemyImgArr[0][4] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotRight5.png"));
                    EnemyImgArr[0][5] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotRight6.png"));
                    EnemyImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotLeft1.png"));
                    EnemyImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotLeft2.png"));
                    EnemyImgArr[1][2] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotLeft3.png"));
                    EnemyImgArr[1][3] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotLeft4.png"));
                    EnemyImgArr[1][4] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotLeft5.png"));
                    EnemyImgArr[1][5] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BBA\\BomberBotLeft6.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Y = SetUpY(Y,Height/2);
            break;
            case "Turret":
                image = null;
                //0 is right 1 is left
                EnemyImgArr = new BufferedImage[3][3];
                try {
                    EnemyImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\TurretShooting.png"));

                    EnemyImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\TurretDownRight.png"));
                    EnemyImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\TurretStraightRight.png"));
                    EnemyImgArr[1][2] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\TurretUpRight.png"));

                    EnemyImgArr[2][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\TurretLeftDown.png"));
                    EnemyImgArr[2][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\TurretStraightLeft.png"));
                    EnemyImgArr[2][2] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\TurretLeftUp.png"));


                } catch (IOException e) {
                    e.printStackTrace();
                }
                Health = 6;
                Width = 24;
                Height = 72;
                Timer = (int)Math.floor(Math.random()*(240-0+1)+0);
            break;
            case "Frog":

                image = null;
                //0 is right 1 is left
                EnemyImgArr = new BufferedImage[2][2];
                try {
                    EnemyImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\FF\\FrogLeftStill.png"));
                    EnemyImgArr[0][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\FF\\FrogRightStill.png"));
                    EnemyImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\FF\\FrogLeftJump.png"));
                    EnemyImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\FF\\FrogRightJump.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Health = 6;
                Width = 48;
                Height = 48;
                Timer = (int)Math.floor(Math.random()*(270-0+1)+30);
            break;
            case "Bullet":
                DirX = SpecialVars;
                Health = 800832;
                Width = 35;
                Height = 10;
            break;
            case "Box":
                try {
                    image = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\Box.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DirY = 0;
                DirX = 0;
                Width = 72;
                Height = 72;
            break;
            case "healthBox":
                try {
                    image = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\IA\\Heal.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DirX = 0;
                Width = 52;
                Height = 44;
            break;
            case "Bat":
                EnemyImgArr = new BufferedImage[2][2];
                try {
                    EnemyImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BF\\Still.png"));
                    EnemyImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BF\\BatFrame1.png"));
                    EnemyImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\BF\\BatFrame2.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DirY = 0;
                DirX = SpecialVars;
                Width = 48;
                Height = 48;
                Timer = DirX;
                isResting = true;
                Health = 6;
                Frame = 0;
            break;
            case "Bat2":
                EnemyImgArr = new BufferedImage[2][2];
                try {
                    EnemyImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BF\\B22.png"));
                    EnemyImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BF\\B20.png"));
                    EnemyImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\BF\\B21.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DirY = 0;
                DirX = 5;
                Width = 48;
                Height = 48;
                Timer = SpecialVars;
                Health = 6;
                Frame = 0;
                TrajX = 0;
                TrajY = 0;
                Speical = SpecialVars;
            break;
            case "T2":
                image = null;
                //0 is right 1 is left
                EnemyImgArr = new BufferedImage[2][2];
                try {
                    EnemyImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\sprite_2.png"));
                    EnemyImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\TBA\\sprite_0.png"));
                    EnemyImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\TBA\\sprite_1.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Health = 8;
                Width = 24;
                Height = 72;
                Timer = (int)Math.floor(Math.random()*(240-0+1)+0);
                isFiring = false;
                TrajX = 0;
            break;
            case "Rocket":
                Health = 2;
                isJumping = false;
                Width = 30;
                Height = 50;
                DirY = 0;
                dirY = 0;

            try {
                EnemyImgArr = new BufferedImage[2][2];
                EnemyImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\RA\\RocketStill.png"));
                EnemyImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\RA\\R0.png"));
                EnemyImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\RA\\R1.png"));
            } catch (IOException e) {e.printStackTrace();}
            break;
            case "Boss":
                finalBoss.Start();
            break;
        }

    }
    public int SetUpY(int y, int h) {
        y = y - h;
        return y;
    }
    }}