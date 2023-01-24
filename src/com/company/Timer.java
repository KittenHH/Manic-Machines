package com.company;

import java.time.LocalTime;

public class Timer extends Thread{
    public static boolean IsRunning = true;
    public static int Sec;
    public static int Min;
    Timer() {
        Sec = 0;
        Min = 0;
    }
    @Override
    public void run() {
        while(IsRunning) {

            try{Thread.sleep(1000);} catch (Exception e) {System.out.println(e);}
            if(Main.IsPause == false) {
            Sec += 1;
            if(Sec == 60) {
                Sec = 0;
                Min += 1;
            }
        }}
    }
}
