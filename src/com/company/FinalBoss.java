package com.company;
import com.sun.source.tree.NewArrayTree;

import javax.imageio.ImageIO;
import javax.sound.midi.SysexMessage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EventListener;
import java.util.function.IntConsumer;
import java.awt.*;

public class FinalBoss  {
    boolean ShouldTakeDamage = false;
    boolean isDead = false;
    int TotalThrows = 0;
    ColisionCheck colisionCheck = new ColisionCheck();
    int HE = 500;
    boolean IsOnline = false;
    int OffSetX = 0;
    int Img1;
    int Img2;
    int LFrame = 0;
    int X = 400;
    int Y = 300;
    int Width = 85;
    int Height = 200;
    State state = new NoAi();
    BufferedImage[] LowerBody = new BufferedImage[4];
    BufferedImage[][] UpperBody = new BufferedImage[4][2];

    FinalBoss() {
        EnemyHandeler.SummonDrops = false;
        IsOnline = true;
        try {
            //throwable obj
            //lower body
LowerBody[0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l0_sprite_1.png"));
LowerBody[1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l0_sprite_2.png"));
LowerBody[2] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l0_sprite_3.png"));
LowerBody[3] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l0_sprite_4.png"));
            //upper body
UpperBody[0][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l1_sprite_3.png"));
UpperBody[1][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\ThrowB_0.png"));
UpperBody[1][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\ThrowB_2.png"));
UpperBody[2][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\ThrowB_1.png"));
UpperBody[2][1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\ThrowB_3.png"));
UpperBody[3][0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l1_BOOM_1.png"));

        } catch (Exception e) {e.printStackTrace();}
    }
    public void Draw(Graphics g) {

        if(ShouldTakeDamage) {
            HE = HE - 50;
            ShouldTakeDamage = false;
        }
        Colision();
        LowerBodyAnimation();
        DisplayHealthBar(g);
        death();
        state.Main(g);
        //put in a try catch block so it dosent output an error that dose nothing but waist processing time
        g.drawImage(UpperBody[Img1][Img2],X,Y,null);
        try{g.drawImage(LowerBody[LFrame],X + OffSetX,Y,null);}catch (Exception e) {}
    }
    void Colision() {
        if(colisionCheck.Check(X,Y,Width,Height-40,Player.X,Player.Y,Player.width,Player.height)) {
            Player.TakeDamage();
        }
    }
    public void Start() {
        state = new Move(240);
    }
    void LowerBodyAnimation() {
        if(LFrame == 3) {LFrame = 0;return;}
        if(Globals.Frame % 6 == 0) {LFrame += 1;}
    }
    public void TakeDamage() {
        ShouldTakeDamage = true;
        state = new Thunder();
    }
    void DisplayHealthBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(100,50,HE,25);
    }
    void death() {
        if(isDead) {return;}
        if(HE > 1) {return;}
        state = new Death();
    }

    class Move extends State {
        Move(int tick) {
            OffSetX = 0;
            Tick = tick;
            Img1 = 0;
            Img2 = 0;
        }
        int TrajX = 0;
        @Override
        public void Move() {
            int PlayerCenter = Player.X + (Player.width/2);
            if(PlayerCenter > X + Width || PlayerCenter < X) {
                TrajX = (Player.X - X)/40;
            } else {TrajX = 0;}
            X = X + TrajX;
            if(Y > 150) {Y = Y - 2;}
        }

        @Override
        public void SuperCheck() {
            Tick = Tick - 1;
            if(Tick == 0) {
                state = new Throw();
            }
        }
        @Override
        public void Main(Graphics g) {
            SuperCheck();
            Move();
        }
    }
    class Throw extends State {
        ThrowAble throwAble;
        int TrajX;
        int ThrowDistance = 100;
        int Facing;

        Throw() {
            Tick = 360;
            OffSetX = 15;
            Img1 = 1;
            Img2 = 0;
            Facing = Img2;
            if(TotalThrows % 10 == 0) {throwAble = new ThrowAble(X,Y,"healthBox");return;}
            if(TotalThrows % 3 == 0) {
                throwAble = new ThrowAble(X,Y,"Rocket");
            } else {
                if(TotalThrows % 2 == 0) {throwAble = new ThrowAble(X,Y,"Frog");}
                else {throwAble = new ThrowAble(X,Y,"Turret");}
            }
        }

        @Override
        public void Move() {
            if(Tick < 120) {return;}
            if(Y < 300) {Y = Y + 2;}

            int PlayerCenter = Player.X + (Player.width/2);
            if(PlayerCenter > X + Width + ThrowDistance|| PlayerCenter < X - ThrowDistance) {
            TrajX = (Player.X - X)/60;
            } else {TrajX = 0;}
            X = X + TrajX;
        }

        @Override
        public void SuperCheck() {
            Tick += -1;
            if(Tick == 122) {TotalThrows += 1;}
            if(Tick > 121) {
            int PlayerCenter = Player.X + (Player.width/2);
            int BossCenter = X + (Width/2);
            if(PlayerCenter > BossCenter) {Img2 = 1;} else {Img2 = 0;}
            Facing = Img2;
            } else {
                Img1 = 2;
                Img2 = Facing;
            }


            if(Tick == 121) {
                throwAble.GetTrajectory();
                throwAble.isMoving = true;
            }
            //fixes the throwable to the bosses hand
            if(throwAble == null) {return;}
            if(throwAble.ShouldRemove) {throwAble = null;}
            if(throwAble == null) {return;}
            try{     if(throwAble.isMoving) {return;} }catch (Exception e) {}
            if(Facing == 1) {
                throwAble.X = X-10;
            } else {throwAble.X = X + 80;}
            throwAble.Y = Y;
        }


        @Override
        public void Main(Graphics g) {
            if(throwAble != null) {
            throwAble.Draw(g);}
            SuperCheck();
            Move();
            if(Tick == 0) {
                state = new Move(60);
            }
        }
    }
    class Thunder extends State {
        BufferedImage[] Lighting = new BufferedImage[2];
        ThunderBolt[] ThrowAbles = {null};
        int Frame = 0;

        Thunder() {
            OffSetX = 20;
            Tick = 800;
            try{
                Lighting[0] =  ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l0_BOOM_1.png"));
                Lighting[1] =  ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\l0_BOOM_2.png"));
            } catch (Exception e) {}
            Img1 = 3;
            Img2 = 0;
        }

        @Override
        public void Move() {if(Y < 620) {Y += 1;}}

        @Override
        public void SuperCheck() {
            if(Globals.Frame % 6 == 0) {
                if(Frame == 0) {Frame = 1;} else {Frame = 0;}
            }
        }
        void DrawThunderBolts(Graphics g) {
            for (int i = 0; i < ThrowAbles.length; i++) {
                if(ThrowAbles[i] == null) {continue;}
                ThrowAbles[i].DrawThunder(g);
            }
        }
        void AddThunderBolt() {
            if(Tick % 60 != 0) {return;}

            double ALittleBitOfRandom =  Math.random()*-120 + 40;
            int UseAble = (int) ALittleBitOfRandom;

            ThunderBolt NewTB1 = new ThunderBolt(-5,X,Y + (Height/2) + UseAble);
            ThunderBolt NewTB2 = new ThunderBolt(5,X + Width,Y + (Height/2) + UseAble);
            ThunderBolt[] NewArr = new ThunderBolt[ThrowAbles.length + 2];
            for (int i = 0; i < ThrowAbles.length; i++) {
                NewArr[i] = ThrowAbles[i];
            }
            NewArr[ThrowAbles.length] = NewTB1;
            NewArr[ThrowAbles.length + 1] = NewTB2;
            ThrowAbles = NewArr;
        }
        @Override
        public void Main(Graphics g) {
            Tick = Tick -1;
            if(Tick == 0) {state = new Move(120);}
            Move();
            SuperCheck();
            AddThunderBolt();
            DrawThunderBolts(g);
            g.drawImage(Lighting[Frame],X,Y,null);
        }
        //anouther throwable obj
        class ThunderBolt {
            ColisionCheck colisionCheck = new ColisionCheck();
            BufferedImage ImageArr[] = new BufferedImage[2];
            int Speed;
            int X;
            int Y;
            int W = 30;
            int H = 22;
            ThunderBolt(int speed, int x,int y){
                try{
                    ImageArr[0] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\Lighting_0.png"));
                    ImageArr[1] = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\Lighting_1.png"));
                } catch (Exception e) {}
                Speed = speed;
                X = x;
                Y = y;
        }
        void move() {
                X += Speed;
        }
        void IsTouchingPlayer() {
            if(X > 800 || X < 0) {return;}
            if(colisionCheck.Check(X,Y,W,H,Player.X,Player.Y,Player.width,Player.height)) {
                Player.TakeDamage();
            }
        }
        void DrawThunder(Graphics g) {
                IsTouchingPlayer();
                move();
                if(Speed > 0) {
                g.drawImage(ImageArr[0],X,Y,null);}
                else {
                g.drawImage(ImageArr[1],X,Y,null);
                }
        }
        }
    }
    class Death extends State {

        Death() {
            Tick = 300;
            isDead = true;
        }

        @Override
        public void Move() {

        }

        @Override
        public void SuperCheck() {

        }

        @Override
        public void Main(Graphics g) {
            Tick = Tick -1;
            if(Tick == 0) {
                Globals.LevelComplete = true;
            }
            double RanX = Math.random() * Width + X - 30;
            double RanY = Math.random() * Height + Y - 30;
            Effects.CreateEffect(new Death1((int) RanX,(int) RanY,Effects.List.length));
        }
    }
    class NoAi extends State {

        @Override
        public void Move() {

        }

        @Override
        public void SuperCheck() {

        }

        @Override
        public void Main(Graphics g) {

        }
    }
}

//this is the abstract for the different states the boss will be in
abstract class State{
    int Tick;
    public abstract void Move();
    public abstract void SuperCheck();
    public abstract void Main(Graphics g);
}
//this is the little box the Boss throws at the player
class ThrowAble {
    boolean ShouldRemove = false;
    int X;
    int Y;
    int W = 45;
    int H = 45;
    int TrajX;
    int TrajY;
    EnemyHandeler.Enemy Secret;
    ColisionCheck colisionCheck = new ColisionCheck();
    boolean isMoving = false;
    String Type;
    BufferedImage image;
    ThrowAble(int x,int y,String type) {
        Secret = new EnemyHandeler.Enemy(type,X,Y,2);
        X = x;
        Y = y;
 try {image = ImageIO.read(new File(System.getProperty("user.dir") +  "\\src\\com\\company\\MCart\\BossAnimation\\ThrowAble.png"));} catch (Exception e) {}
    }
    void Draw(Graphics g) {
        move();
        g.drawImage(image,X,Y,null);
    }
    void move() {
        if(!isMoving) {return;}
        X = X + TrajX;
        Y = Y + TrajY;
        if(colisionCheck.Check(X,Y,W,H,Player.X,Player.Y,Player.width,Player.height)) {
            Player.TakeDamage();
            Death();
        }
        if(colisionCheck.BlockCheck(X,Y,H,W) != -1) {
            Death();
        }
    }
    void GetTrajectory() {
        TrajX = (Player.X - X)/40;
        TrajY = (Player.Y - Y)/40;
    }
    //creates enemy
    void SpawnEnemy() {
        for (int i = 0; i < EnemyHandeler.EnemyList.length; i++) {
            EnemyHandeler.Enemy c = EnemyHandeler.EnemyList[i];

            if(c == null || c.Type == "Blank") {
                Secret.X = X;
                Secret.Y = 776 - Secret.Height;
                EnemyHandeler.EnemyList[i] = Secret;
                break;
            }
        }
    }
    //destroyes the box
    void Death() {
        SpawnEnemy();
        Effects.CreateEffect(new Death1(X,Y,Effects.List.length));
        ShouldRemove = true;
    }
}