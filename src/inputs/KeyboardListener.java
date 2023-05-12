
package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardListener implements KeyListener {
    private GamePanel gp;

    public KeyboardListener(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                GamePanel.playSE(4);
                gp.getPlayer().setJump(true);
                break;
            case KeyEvent.VK_S:
                //GamePanel.playSE(5);
                gp.getPlayer().setDown(true);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_UP:
                GamePanel.playMusic(4);
                // GamePanel.stopMusic();
                gp.getPlayer().setJump(true);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                GamePanel.playMusic(5);
                // GamePanel.stopMusic();
                gp.getPlayer().setDown(false);
                break;

        }
    }
}
