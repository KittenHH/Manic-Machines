package com.company;
//this handels all levels
public abstract class SuperLevel {
    public static SuperLevel[] LevelArr = {
                new TitleScreen(),
                new CutScene(),
                new Level0(),
                new Level1(),
                new Level2(),
                new Level3(),
                new Level4(),
                new Level5(),
                new Level6(),
                new Level7(),
                new Level8(),
                new Level9(),
                new Level10(),
                new Level11(),
                new Level12(),
                new Level13(),
                new Level14(),
                new Level15(),
                new Level16(),
                new Level17(),
                new Level18(),
                new Level19(),
                new BossRoom(),
                new EndScreen()
    };
    public int Set(int pos) {return pos*24;}
    abstract void LevelCheck();
    abstract void StartLevel();
    public static void NextLevel() {
        LevelArr[Globals.Level + 1].StartLevel();
        Globals.Level += 1;
    }
}
