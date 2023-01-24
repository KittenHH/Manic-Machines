package com.company;

import java.awt.*;
import java.util.IllegalFormatWidthException;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public  class BlockHandeler extends ColisionCheck {
    public static BlockHandeler.plank[] SetUpArray;
    public static Block[] VisibleBlocks;
    public static BufferedImage StopBlock;
    public static BufferedImage ButtonUp;
    public static BufferedImage MoveBlock;
    public static BufferedImage PopBlock;
    public static BufferedImage PopBlock2;
    public static BufferedImage Block1;
    public static BufferedImage Block2;
    public static BufferedImage Block3;
    public static BufferedImage Block4;
    public static BufferedImage Block5;
    public static BufferedImage pass;

    //for cutscene
    public static BufferedImage Panel1;
    public static BufferedImage Panel2;
    public static BufferedImage Panel3;
    public static BufferedImage Panel4;


    //this is a list of all the text being displayed in the cutscene
    public static String[] TextTable = {
            "You a scientist who plans",
            "on taking over the entire world",
            "With an army of genetically ",
            "engineered monsters and robots",
            "But due to a coding error",
            "they are alive & want to kill you",
            "Armed with a pair of laser goggles",
            "you must destroy these monsters"
    };

    //for pre game stuff
    public static BufferedImage TitleScreen;

    BlockHandeler() {
        //this imports all the blocks images
        try {
            StopBlock = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\FuncBlock.png"));
            ButtonUp = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\ButtonUp.png"));
            MoveBlock = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\Ymove.png"));
            PopBlock = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\PopBlock.png"));
            Block1 = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\Block1.png"));
            Block2 = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\Block2.png"));
            Block3 = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\Block3.png"));
            Block4 = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\Block4.png"));
            Block5 = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BA\\Block5.png"));
            PopBlock2 = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\BA\\PopBlock2.png"));
            pass = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\BA\\Pass.png"));

            Panel1 = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\intro\\Frame1.png"));
            Panel2 = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\intro\\Frame2.png"));
            Panel3 = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\intro\\Frame3.png"));
            Panel4 = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\intro\\Frame4.png"));


            TitleScreen = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\company\\MCart\\BIGTitleScreen.png"));
        }
        catch(IOException e) {
        e.printStackTrace();
    }
        VisibleBlocks = new Block[] {new Block(0,0,1,1,"test",null,null)};
    }
    //this converts the numbers into objects in the VisibleBlocks array
    public void SetUpTiles() {
        Globals.Frame = 0;
        VisibleBlocks = new Block[] {};
        for(int i = 0;i < SetUpArray.length;i++) {
            Player.Y = 25*24;
            BlockHandeler.plank plank = SetUpArray[i];
            int ii = 0;
            while(ii < plank.length) {
                AddBlock(plank.X + ii,plank.Y,plank.width,plank.height,plank.type,plank.Image,plank.Data);
                ii++;
            }
        }Player.Y = 25*24 + 100;
    }
    //this adds a blocks into the array
    public void AddBlock(int X, int Y,int width,int height, String Type,BufferedImage bImage, int[] data) {
        Block newObj = new Block(X*24,Y*24 + 100,width,height,Type,data,bImage);
        Block[] newArr = new Block[VisibleBlocks.length + 1];
        for(int i = 0; i < VisibleBlocks.length; i++) {
            newArr[i] = VisibleBlocks[i];
        }
        newArr[VisibleBlocks.length] = newObj;
        VisibleBlocks = newArr;
    }
    //loops threw the list
    public void RenderTiles(Graphics g) {
        for(int i = 0; i < VisibleBlocks.length;i++) {
            if(VisibleBlocks[i] != null) {
            VisibleBlocks[i].draw(g,VisibleBlocks[i]);}
        }
    }
    static class plank {
        int length;
        int X;
        int Y;
        int width;
        int height;
        String type;
        BufferedImage Image;
        int[] Data;
        public plank(String Type,int Length, int x,int y,int Width,int Height, BufferedImage image, int[] data) {
            length = Length;
            X = x;
            Y = y;
            type = Type;
            Image = image;
            Data = data;
            width = Width;
            height = Height;
        }
    }
}
