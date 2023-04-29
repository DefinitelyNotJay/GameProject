package main;

import screen.MenuGame;
import screen.Result;
import entity.Player;
import obstacles.WallPattern;
import inputs.KeyboardListener;
import inputs.MouseHandler;
import java.awt.*;
import javax.swing.*;
import object.AssetSetter;
import object.SuperObjects;
import static constant.Constants.*;
import methods.Utilz;

public class GamePanel extends JPanel{
    final int originalTileSize = 32;
    public final int scale = 2; 
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 8;
    public final int screenWidth = tileSize * maxScreenCol; // 1280 px
    public final int screenHeight = tileSize * maxScreenRow; // 512 px
    private Player player;
    private WallPattern wp;
    private MenuGame mg;
    private Result result;
    public SuperObjects obj[] = new SuperObjects[10];
    public AssetSetter aSetter = new AssetSetter(this);
    public static int GameState = MENU;
    private static Sound music;
    public GamePanel(){
        // player = new Muscle(this, 100, tileSize*2,Constants.GROUND, tileSize, tileSize);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(new KeyboardListener(this));
        addMouseListener(new MouseHandler(this));
        wp = new WallPattern(this);
        new Utilz(this);
        mg = new MenuGame();
        music = new Sound();
        result = new Result(this);
        // playMusic(0);
    }

public void setUpGame(){
    aSetter.serObject();
}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(GameState == PLAYING){
            player.draw(g2);
            for(int i=0; i<wp.getWallPattern().size(); i++){
                wp.getWallPattern().get(i).draw(g2);
             }
        } 
         else if(GameState == DEAD){
            Utilz.sleep(2);
            GameState = RESULT;
         }
         else if(GameState == MENU){
            mg.paint(g2);
        }
        else if(GameState == RESULT){
            result.paint(g2);
        }
        g2.dispose();
    }

    public void update(){
        if(GameState == PLAYING){
            player.update();
            for (int i=0; i<wp.getWallPattern().size(); i++){
                wp.getWallPattern().get(i).update();
                }
        }
    }

    public void updateEverySec(){
        if(GameState == PLAYING){
            player.updateEverySec();
        }
    }

    public void gameReset(){
        player.setHP(100);
       wp.randomWallSequence(2);
        
        // waiting for reset obstacles method
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void playMusic(int i){
        music.setFiles(i);
        music.playsound();
        //music.setVolume(SettingPanel.getMusicVolume());
        music.loopsound();
    }
}
