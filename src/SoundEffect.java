import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media. * ;

/**
public class SoundEffect{ 
	public Player player;
    private String bgmPath;
    
    public SoundEffect(String path) {
    	bgmPath = path;
    }

    public void prepare() {
        try {
			File playFile = new File(bgmPath);
			player = Manager.createRealizedPlayer(playFile.toURI().toURL());
        } catch(javax.media.CannotRealizeException ex) {
            System.out.println("���ܴ���������");
        } catch(NoPlayerException ex) {
            System.out.println("���ܲ����ļ�");
        } catch(IOException ex) {
        }
        if (player == null) {
            return;
        }
        player.prefetch();
    }
    
     
}*/

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 

public class SoundEffect{
	public Clip player;
	public FloatControl gainControl;
	private String audioFilePath;
    public SoundEffect(String path) {
    	audioFilePath = path;
    }
	
    void prepare() {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            player = (Clip) AudioSystem.getLine(info);
 
            player.open(audioStream);
            
            gainControl = (FloatControl) player.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(-10.0f);
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
         
    }
    
 
 
}