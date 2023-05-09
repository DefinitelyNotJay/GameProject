package Item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static constant.Constants.*;
import entity.Entity;
import main.Game;
import main.GamePanel;
import methods.Utilz;

public abstract class SuperObjects {
    protected BufferedImage imageItem;
    protected String name;
    protected boolean collision = false;
    public int mapX, mapY, sizeX, sizeY;
    protected GamePanel gp;
    public BufferedImage bloodItem;
    public int speed, times = 0;
    public double playerX, playerY, playerWidth, playerHeight, playerSolidAreaX, playerSolidAreaY;

    public abstract void effect();

    public SuperObjects(GamePanel gp, int x, int y, int sizeX, int sizeY, String name) {
        this.mapX = x;
        this.mapY = y;
        this.gp = gp;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.name = name;
        loadImages();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void mapX(int mapX) {
        this.mapX = mapX;
    }

    public int mapX() {
        return mapX;
    }

    public void mapy(int mapY) {
        this.mapY = mapY;
    }

    public int mapY() {
        return mapY;
    }

    public BufferedImage getItemImage() {
        return imageItem;
    }

    public void setItemImage(String pathFile) {
        this.imageItem = Utilz.GetImage(pathFile);
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean getCollision() {
        return collision;
    }

    public void loadImages() {

    }

    public void draw(Graphics g2) {

        mapX -= GAMESPEED;
        if (!this.collision)
            g2.drawImage(imageItem, mapX, mapY, gp.tileSize, gp.tileSize, null);

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

    public void crashItem() {
        if (playerX + playerWidth >= this.mapX && playerX + playerWidth <= this.mapX + sizeX) {
            if (playerY + playerHeight >= this.mapY && playerY + playerHeight <= this.mapY + sizeY) {
                setCollision(true);
            }
        }
    }

    public void skillItem() {
        if (collision)
            effect();

    }
}
