package com.company;

import java.awt.*;
import java.util.function.BooleanSupplier;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block extends  ColisionCheck{
    int X;
    int Y;
    int Width;
    int Height;
    int[] ExtraData;
    boolean isExistance;
    BufferedImage image;
    String Type;
    ColisionCheck colisionCheck = new ColisionCheck();
    Player player = new Player();
    ButtonBlock buttonBlock = new ButtonBlock();
    MoveYBlock moveYBlock = new MoveYBlock();
    public static BufferedImage ButtonDown;
    public static boolean PreviousFrame;


    public Block(int x, int y, int width, int height, String type,int[] specialVars,BufferedImage Image) {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        Type = type;
        ExtraData = specialVars;
        image = Image;
        isExistance = true;
        try {
          ButtonDown = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\BA\\ButtonDown.png"));
        } catch (IOException e) {e.printStackTrace();}
        switch (type) {
            case "Button":
                Y = Y + 15;
            break;
            case "PButton":
                Y = Y + 15;
                break;
        }
    }

    public void draw(Graphics g,Block currBlock) {
        if(Globals.LevelComplete && PreviousFrame == false) {
            Effects.CreateEffect(new CompleteLevelEffect());
        }
        switch(currBlock.Type) {
            case "Test":
                g.drawImage(image,X,Y,null);
                //checks if player is hitting it
                SpecialCollision(X,Y,Width,Height);
                PBCheck(currBlock);
                break;
            case "Stop":
                if(Globals.LevelComplete) {return;}
                g.drawImage(BlockHandeler.StopBlock,X,Y,null);
                //checks if player is hitting it
                StopBlockCheck();
                PBCheck(currBlock);

                break;
            case "Pop":
                if(Globals.LevelConditions[ExtraData[0]] == false) {return;}
                    g.drawImage(BlockHandeler.PopBlock,X,Y,null);
                    //checks if player is hitting it
                    PlayerColsisionCheck();
         //       SpecialCheck(X,Y,Width,Height,Player.X,Player.Y,Player.width,Player.height);
                SpecialCollision(X,Y,Width,Height);
                PBCheck(currBlock);
                break;
            case "Button":
                if(ExtraData[0] == 1) {
                    Globals.LevelConditions[ExtraData[1]] = true;
                    g.drawImage(ButtonDown,X,Y,null);
                } else {
                    Globals.LevelConditions[ExtraData[1]] = false;
                    g.drawImage(image,X,Y,null);}
                ExtraData[0] = buttonBlock.IsDown(X,Y,Width,Height);
            break;
            case "YMove":
                g.drawImage(BlockHandeler.MoveBlock,X,Y,null);
                Y = moveYBlock.Move(currBlock);
                ExtraData[2] = moveYBlock.ReverseMovement(currBlock);
                PlayerColsisionCheck();
                SpecialCollision(X,Y,Width,Height);
                break;
            case "PButton":
                if(ExtraData[0] == 1) {
                    Globals.LevelConditions[ExtraData[1]] = true;
                    g.drawImage(ButtonDown,X,Y,null);
                } else {
                    g.drawImage(image,X,Y,null);
                    ExtraData[0] = buttonBlock.PIsDown(X,Y,Width,Height);}
            break;
            case "XMove":
                SpecialCollision(X,Y,Width,Height);
                g.drawImage(BlockHandeler.MoveBlock,X,Y,null);
                moveYBlock.ForceX(currBlock);

                X = moveYBlock.MoveX(currBlock);
                ExtraData[2] = moveYBlock.ReverseMovementX(currBlock);
                break;
            case "Pass":
                PlayerColsisionCheck();
                g.drawImage(BlockHandeler.pass,X,Y,null);
                break;
            case "Title":
                g.drawImage(BlockHandeler.TitleScreen,0,0,null);
                break;
            case "End":

                int Total = PlayerBullet.Hit + PlayerBullet.Miss;
                double Percent =  ((double) PlayerBullet.Hit/ (double) Total)*100;

                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
                g.drawString("Game Completed In " + Timer.Min + ":" + Timer.Sec,100,250);
                g.drawString("Total Hits " + PlayerBullet.Hit,150,450);
                g.drawString("Total Misses " + PlayerBullet.Miss,140,550);
                g.drawString("Hit/Miss " + String.format("%.0f", Percent) + "%",140,650);
                break;
            case "Panel":
                if(PlayerBullet.VisiblePlayerBullets.length < ExtraData[0]) {return;}
                g.drawImage(image,X,Y,null);
                break;
            case "Text":
                if(PlayerBullet.VisiblePlayerBullets.length < ExtraData[0]) {return;}
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.drawString(BlockHandeler.TextTable[ExtraData[1]], X,Y);
                break;
        }
        PreviousFrame = Globals.LevelComplete;
    }
    public void PlayerColsisionCheck() {
            boolean isHit = colisionCheck.Check(X,Y,Width,1,Player.X,Player.Y+50,Player.width,Player.height-50);
            if(isHit && Player.SpeedY > 0) {
            player.HualtY();
            PlayerFixY();
            }
        }
    public void StopBlockCheck() {
        //if touches this block, player is sent down
        boolean isHit = colisionCheck.PointCheck(Player.X,Player.Y,X,Y,Width,Height);
        if(isHit) {Player.SpeedY = 5;}
    }
    //this fixes the players y pos so it dosent look weird
    public void PlayerFixY() {
        if(Player.IsJumping) return;
        Player.Y = Y - Player.height;
    }
    //checks if player is touching the block, what side, if what
    public void SpecialCollision(int x, int y,int w,int h) {
        if(Check(x,y,w,h,Player.X,Player.Y,Player.width,Player.height) == false) {return;}
        int Top = y;
        int Bottom = y + h;
        if(Player.Y +Player.height > Bottom  && Player.SpeedY != 0) {Player.SpeedY = 10;} else
        if(Player.Y + Player.height> Top -1 && Player.SpeedY > 0) {player.HualtY();PlayerFixY();Player.SpeedY = 0;}
        SideToSideCheck(x,y,w,h);
}
    //checks if player is touching side
    public void SideToSideCheck(int x,int y,int w,int h) {
        int top = y;
        int low = y + h;
        int pTop = Player.Y;
        int PLow = Player.Y + Player.width;
        int left = x;
        int rih = x + w;
        int PLeft = Player.X;
        int PRigh = Player.X + Player.width;
        if((top > pTop && top < PLow) || (low > Player.Y && low < PLow)) {
            if(PLeft < left) {
                Player.X += -10;
            } else if (PRigh > rih) {
                Player.X += 10;
            }
        }
    }
    //checks if player bullet is touching block
    public void PBCheck(Block c) {
        if(EnemyBulletCollision(c.X,c.Y,c.Width,c.Height)) {
            PlayerBullet.Miss += 1;
        }
    }
}
