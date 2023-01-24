package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class GamePannel extends JPanel {
    GamePannel() {this.setPreferredSize(new Dimension(GameWindow.W,GameWindow.H));}
    public void paint(Graphics g) {
        if(Main.IsPause) {return;}
        //clears the screen
        g.setColor(Color.BLACK);
        if (Globals.isBarf == false) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (Player.RenderInfo) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), 100);
            Main.player.InfoDisplay(g);
        }

        PlayerBullet.RemoveBullet = -1;
        EnemyHandeler.RemoveEnemy = -1;

        Main.enemyHandeler.draw(g);
        Main.blockHandeler.RenderTiles(g);
        Main.playerBullet.draw(g);
        Main.effects.Draw(g);

        Main.player.Stops = 0;
        Main.player.draw(g);
        //this removes an enemy and bullet if it was sleceted that frame
        Main.enemyHandeler.removeEnemy(EnemyHandeler.RemoveEnemy);
        Main.playerBullet.RemoveBullet(PlayerBullet.RemoveBullet);
        Player.HasBeenMoved = false;
        Player.HasBeenXStop = false;
        //clears the ui and redraws the ui
    }
}
