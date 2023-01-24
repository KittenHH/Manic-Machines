package com.company;

public class ColisionCheck {
    int x = 0;
    int y = 1;
    int[][] Obj1;
    int[][] Obj2;

    ColisionCheck() {}
    //this can run any colision detection theoreticly
    public boolean Check(int X1, int Y1, int Width1, int Height1, int X2, int Y2, int Width2, int Height2) {
        //this creates the two hit boxes
        boolean Decider = true;
        int left1 = X1;
        int right1 = X1 + Width1;
        int top1 = Y1;
        int bottom1 = Y1 + Height1;
        int left2 = X2;
        int right2 = X2 + Width2;
        int top2 = Y2;
        int bottom2 = Y2 + Height2;
        if ((bottom1 < top2) ||
                (top1 > bottom2) ||
                (right1 < left2) ||
                (left1 > right2)) {
            Decider = false;
        }
        return Decider;
    }
    //checks if an individual point is inside an obj
    public boolean individualCheck(int pX, int pY, int[][] Obj) {
        if(pX >= Obj[0][0] && pX <= Obj[1][0] && pY >= Obj[0][1] && pY <= Obj[3][1]) {
            return true;
        }
        return false;
    }
    //checks a single point to a obj, but not for an obj array
    public boolean PointCheck(int X1,int Y1,int X2,int Y2,int Width,int Height) {
        if(X1 > X2 && X1 < X2 + Width && Y1 > Y2 && Y1 < Y2 + Height) {
            return true;
        }
        return false;
    }
    //this runs threw all blocks on the screen
    public boolean AllColisionCheck(int ObjX, int ObjY, int ObjW, int ObjH) {
        for(int i = 0;i < BlockHandeler.VisibleBlocks.length;i++) {
            Block currBlock = BlockHandeler.VisibleBlocks[i];
            boolean isHit = Check(
                    currBlock.X, currBlock.Y, currBlock.Width, currBlock.Height, ObjX, ObjY, ObjW, ObjH);
            if(isHit) {
                return true;
            }
        }
        return false;
    }
    //this fixes any objects y so it dosent look weird
    public int ObjectFixY(int ObjY,int ObjHeight,int BlockY) {
        ObjY = BlockY - ObjHeight;
        return ObjY;
    }
    //this checks if an enemy is touching a player bullet
    public  boolean EnemyBulletCollision(int ObjX, int ObjY, int ObjW, int ObjH) {
        int i;
        if(PlayerBullet.VisiblePlayerBullets.length == 0) {return false;}
        for(i = 0; i < PlayerBullet.VisiblePlayerBullets.length; i++) {
            if(PlayerBullet.VisiblePlayerBullets[i] == null) {continue;}
            PlayerBullet.PBullet CurrBullet = PlayerBullet.VisiblePlayerBullets[i];
            boolean isHit = Check(
                    ObjX,ObjY,ObjW,ObjH,CurrBullet.X,CurrBullet.Y, CurrBullet.WIDTH, CurrBullet.HEIGHT);

            if(isHit) {
                PlayerBullet.RemoveBullet = i;
                return true;
            }
        }
        return false;
    }
    //this first checks of your hitting the obj, and which direction you hitting it at
    //0 is right 1 is left 2 is top 3 is down
    public int SpecialCheck(int X1, int Y1, int Width1, int Height1, int X2, int Y2, int Width2, int Height2) {
        //this keeps track of all the points
        String[] TouchingPoints = {};
        boolean isHit = Check(X1,Y1,Width1,Height1,X2,Y2,Width2,Height2);
        if(isHit == false) {return -1;}
        //compares the right half
        isHit = Check(X1,Y1,Width1/2,Height1,X2,Y2,Width2,Height2);
        if(isHit) {return 0;} else {return 1;}
    }
    //this checks if an obj is touching any other obj
    public boolean AllObjCheck(int X,int Y,int W, int H) {
        for(int i = 0;i < EnemyHandeler.EnemyList.length; i++) {
            EnemyHandeler.Enemy CE = EnemyHandeler.EnemyList[i];
            if(CE == null || CE.Type =="Rocket") {continue;}
            boolean IsHit = Check(X,Y,W,H,CE.X,CE.Y,CE.Width,CE.Height);
            if(IsHit) {return true;}
        }
        return false;
    }
    //this returns the block that the obj is touching
    public int BlockCheck(int X, int Y, int H, int W) {
        for(int i = 0;i < BlockHandeler.VisibleBlocks.length;i++) {
            Block curr = BlockHandeler.VisibleBlocks[i];
            if(curr.Type == "Pop" && Globals.LevelConditions[curr.ExtraData[0]] == false) {continue;}
            boolean isHit = Check(X,Y,W,H,curr.X,curr.Y,curr.Width,curr.Height);
            if(isHit) {
                return i;
            }
        }
        return -1;
    }

}