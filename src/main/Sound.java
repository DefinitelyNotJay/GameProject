package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private long pauseTime;
    private Clip clip;
    private URL soundURL[] = new URL[20];
    private FloatControl gainControl;
    
    public Sound(){
        //sound
        soundURL[0] = getClass().getResource("/res/sound/MENU.wav");
        soundURL[1] = getClass().getResource("/res/sound/ChooseCharacter.wav");
        soundURL[2] = getClass().getResource("/res/sound/Playing_Theme.wav");
        soundURL[3] = getClass().getResource("/res/sound/Ending.wav");
        //effect
        soundURL[4] = getClass().getResource("/res/sound/zombie_jump.wav");
        soundURL[5] = getClass().getResource("/res/sound/zombie_slide.wav");
        soundURL[6] = getClass().getResource("/res/sound/Hit.wav");
    }

    public void setFiles(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound(){
        clip.start();
    }

    public void loopSound(){
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSound(){
        clip.stop();
    }

    public void setVolume(float volume) {
        if(clip != null){
            try {
                volume = volume/100;
                gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
                gainControl.setValue(20f * (float) Math.log10(volume));
            } catch (IllegalArgumentException e) {}
            catch(NullPointerException e){}
        }
    }

    public float getVolume() {
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void pauseSound(){
        pauseTime = clip.getMicrosecondPosition() % clip.getMicrosecondLength();
        clip.stop();
    }

    public void resumeSound(){
        clip.setMicrosecondPosition(pauseTime);
        clip.start();
        loopSound();
    }

}
