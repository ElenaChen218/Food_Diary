package ui;
// Template from TellerApp

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            //new FoodDiaryAppConsole();
            new FoodDiaryAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!! Unable to run application.");
        } catch (LineUnavailableException e) {
            System.out.println("Audio error!");
        } catch (IOException e) {
            System.out.println("Audio error!");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio error!");
        }
    }
}

