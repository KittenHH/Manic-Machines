package com.company;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    //arry1 is right arry2 is left
    private BufferedImage[][] PlayerImgArr = {{null,null,null,null,null},{null,null,null,null,null}};
    private BufferedImage image;
    public static int CurrentFrame = 2;
    public static int FrameDelay = 0;

    public static int CoolDown = 0;
    public static int InvisFrames = 0;

    public static int PlayerHealth = 900;
    public static int width = 35;
    public static int height = 65;
    public static int X = 250;
    public static int Y = 650;
    public static int SpeedY;
    public static int SpeedX;
    public static boolean L = false;
    public static boolean R = false;
    public static int Facing = 0;
    public static int[] MovementArray = {0, 0};
    public static boolean IsJumping = false;
    public static boolean SpaceBarDown = false;
    public int Stops = 0;
    public static boolean HasBeenMoved = false;
    public static boolean HasBeenXStop = false;
    public static boolean RenderInfo = false;
    
    public static boolean IsHualt = false;
    public Player() {
        //imports all the images for the player sprite
        try {
            PlayerImgArr[0][0] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\IdleRight.png"));
            PlayerImgArr[0][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\JumpingRight.png"));
            PlayerImgArr[1][0] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\IdleLeft.png"));
            PlayerImgArr[1][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\JumpingLeft.png"));
            //running Animations
            PlayerImgArr[0][2] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\MCA\\RightFrame1.png"));
            PlayerImgArr[0][3] = ImageIO.read(new File( System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\MCA\\RightFrame2.png"));
            PlayerImgArr[0][4] = ImageIO.read(new File( System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\MCA\\RightFrame3.png"));
            PlayerImgArr[1][2] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\MCA\\LeftFrame1.png"));
            PlayerImgArr[1][3] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\MCA\\LeftFrame2.png"));
            PlayerImgArr[1][4] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\MCA\\LeftFrame3.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //this runs all the code for the player
    public void draw(Graphics g) {
        if(IsHualt) {return;}
        PositionUpdate();
        YposUpdate();
        XMovement();
        ImageDecider();
        FireCoolDown();
        g.drawImage(image,X,Y,width,height,null);
        InvisFrames(g);
        DeathCheck(g);
        OffScreenStop();
    }
    //this gets the player image that should be displayed
    public void ImageDecider() {
        image = PlayerImgArr[Facing][0];
        if(IsJumping) {
            image = PlayerImgArr[Facing][1];
        }
        if(SpeedY >= 0 && SpeedX != 0 && IsJumping == false) {
          image = PlayerImgArr[Facing][CurrentFrame];
          CurrentFrame = AnimationUpdate(CurrentFrame);
        }

    }
    //adds SpeedX and SpeedY to the players X,Y
    public void PositionUpdate() {
        X = X + MovementArray[0];
        X = X + MovementArray[1];
        SpeedX = MovementArray[0] + MovementArray[1];
        Y = Y + SpeedY;
        if(SpaceBarDown) {
            Jump();
        }
    }
    //this starts the jumping proses
    public void Jump() {
        if(IsJumping == false && SpeedY == 0) {
            Effects.playSound("Jump.wav");
            SpeedY = -18;
            IsJumping = true;
        }
    }
    //this dose the jumping physics
    public void YposUpdate() {
        if (Stops == 0 && SpeedY < 15) {
            SpeedY = SpeedY + 1;
        }
    }
    //this stops the jumping sequence
    public void HualtY() {
        Stops = Stops + 1;
        SpeedY = 0;
        IsJumping = false;
    }
    //this loops threw the animation if apllyable
    public int AnimationUpdate(int CurrFrame) {
        FrameDelay = FrameDelay + 1;
        if(FrameDelay != 3) {return CurrFrame;}
        if(CurrFrame == 4)
        {
            CurrFrame = 2;
        } else {
            CurrFrame += 1;
        }
        FrameDelay = 0;
        return CurrFrame;
    }
    //this fires a player bullet
    public void Fire() {
        if(CoolDown != 0) return;
        Effects.playSound("PlayerShoot.wav");
        int preDir = 0;
        if(Facing == 1) {
           preDir = 25;
        } else {
           preDir = -25;
        }
        Main.playerBullet.AddBullet(X,Y+15,preDir);
        CoolDown = 15;
    }
    public void FireCoolDown() {
        if(CoolDown == 0) return;
        CoolDown = CoolDown - 1;
    }
    public static void TakeDamage() {
        if(InvisFrames > 0) {return;}
        PlayerHealth = PlayerHealth - 1;
        InvisFrames = 60;
    }
    public void InvisFrames(Graphics g) {
        if(InvisFrames == 0) {return;}
        InvisFrames = InvisFrames - 1;
        if(InvisFrames % 3 == 1) {
            g.setColor(Color.BLACK);
            g.fillRect(X,Y,width,height);
        }
    }
    //this checks if the player's health is zero and stops the game
    public void DeathCheck(Graphics g) {
        if(PlayerHealth > 0) {return;}
        Effects.CreateEffect(new CountDown());
        SuperLevel.LevelArr[Globals.Level].StartLevel();
        EnemyHandeler.finalBoss = new FinalBoss();
        EnemyHandeler.finalBoss.Start();
        PlayerHealth = 3;
    }
    //this checks if the player is about to get off the screen and stop it
    public void OffScreenStop() {
        if(Player.X < 0) {
            Player.X = 1;}
        if(Player.X + Player.width > 695) {
            Player.X = 699 - Player.width;
        }
    }
    //this renders the info about the game at the top of the screen
    public void InfoDisplay(Graphics g) {
        int HealthBarX = 30;
        for(int i = 0;i < PlayerHealth;i++) {
            g.setColor(Color.white);
            g.fillRect(HealthBarX + i*40,20,25,25);
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString(Timer.Min + ":" + Timer.Sec,550,40);
    }
    void XMovement() {
        if(L && MovementArray[1] < 7) {MovementArray[1] += 1;}
        if(R && MovementArray[0] > -7) {MovementArray[0] += -1;}
    }
}