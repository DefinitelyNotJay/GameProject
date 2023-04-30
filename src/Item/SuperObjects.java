package Item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.Game;
import main.GamePanel;
import methods.Utilz;

public class SuperObjects{
    private BufferedImage Image;
    private String name;
    private boolean collision = false;
    public int mapX, mapY, sizeX, sizeY;
    private GamePanel gp;
    public BufferedImage cat;
    public int speed;
    public double playerX, playerY, playerWidth, playerHeight, playerSolidAreaX, playerSolidAreaY;
    
    public SuperObjects(GamePanel gp, int x, int y, int sizeX, int sizeY) {
        this.mapX = x;
        this.mapY = y;
        this.gp = gp;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        loadImages();
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void mapX(int mapX){
        this.mapX = mapX;
    }
    public int mapX(){
        return mapX;
    }
    public void mapy(int mapY){
        this.mapY = mapY;
    }
    public int mapY(){
        return mapY;
    }
    public void setCollision(boolean collision){
        this.collision = collision;
    }
    public boolean getCollision(){
        return collision;
    }
    public void loadImages(){
        cat = Utilz.GetImage("/res/player/capybara/slideCapy.png");
    }
    public void draw(Graphics g2) {
        
        mapX -= 8;
        if(!this.collision)
        g2.drawImage(cat, mapX, mapY, gp.tileSize,gp.tileSize , null);
        // g2.setColor(Color.red);
        // g2.fillRect(mapX(), mapY(), gp.tileSize,gp.tileSize);
    }
    public void update() {
        playerX = gp.getPlayer().getX();
        playerWidth = gp.getPlayer().getWidth();
        playerSolidAreaX = gp.getPlayer().getCrashAreaWidth();
        playerY = gp.getPlayer().getY();
        playerHeight = gp.getPlayer().getHeight();
        crashItem();
        skillItem();
    }
    public void crashItem(){
        if (playerX + playerWidth + playerSolidAreaX >= this.mapX
        && playerX + playerWidth + playerSolidAreaY <= this.mapX+sizeX){
            if (playerY + playerHeight - playerSolidAreaY >= this.mapY){
                setCollision(true);
            }
        }
    }
    public void skillItem(){
        if(collision){
        if (getName().equals("Big")){
            collision = false;
            gp.getPlayer().setHP(gp.getPlayer().getHP()-10);


        }
    }
    }
}