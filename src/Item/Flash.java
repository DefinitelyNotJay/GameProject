package Item;

import main.GamePanel;
import static constant.Constants.*;

public class Flash extends SuperObjects {
    private int times = 0;

    public Flash(GamePanel gp, int x, int y, int sizeX, int sizeY, String name) {
        super(gp, x, y, sizeX, sizeY, name);
    }

    @Override
    public void effect() {
        if (getName().equals("fast")) {
            // System.out.println(mapX);
            if (mapX > -1500) {
                GAMESPEED = 13;
                gp.getPlayer().setImmune(true);
                System.out.println("fast item");
            } else if (mapX < -1500 && mapX > -1510 && times == 0) {
                times++;
                GAMESPEED = 4;
                if (!gp.getPlayer().isSkillOnUse())
                    gp.getPlayer().setImmune(false);

            }
        } else if (getName().equals("IncreaseHP")) {
            if (mapX() > -1) {
                gp.getPlayer().setHP(gp.getPlayer().getMaxHP());
                System.out.println(gp.getPlayer().getMaxHP());
                System.out.println("IncreaseHP");
            }

        } else if (getName().equals("DecreaseHP")) {
            // System.out.println(mapX());
            if (mapX() == 0) {
                gp.getPlayer().setHP(gp.getPlayer().getHP() - 90);
                System.out.println(gp.getPlayer().getHP());
                System.out.println("DecreaseHP");
            }
        } else if (getName().equals("Giant")) {

        }
    }

}
