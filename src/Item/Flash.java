package Item;

import main.GamePanel;
import static constant.Constants.*;

public class Flash extends Obj_Item {

    public Flash(GamePanel gp, int x, int y, int sizeX, int sizeY, String name) {
        super(gp, x, y, sizeX, sizeY, name);
    }

    @Override
    public void effect() {
        if(getName().equals("fast")){
            if (mapX() > -1500) {
                GAMESPEED = 13;
                gp.getPlayer().setImmune(true);
                System.out.println("fast item");
            } else {
                GAMESPEED = 6;
                gp.getPlayer().setImmune(false);
                gp.getPlayer().getImmune();
            }
        }
        else if(getName().equals("IncreaseHP")){
            if (mapX() == 0) {
                gp.getPlayer().setHP(gp.getPlayer().getMaxHP());
                System.out.println("IncreaseHP");
            }
        }
        else if(getName().equals("DecreaseHP")){
            // System.out.println(mapX());
            if (mapX() == 0) {
                gp.getPlayer().setHP(gp.getPlayer().getHP()-20);
                System.out.println(gp.getPlayer().getHP());
                System.out.println("DecreaseHP");
            }
        }
    }

}
