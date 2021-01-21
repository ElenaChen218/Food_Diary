package ui;


import model.Food;
import model.FoodList;
import model.UserInfo;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;


// Template from TellerApp

public class FoodDiaryAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/FoodDiary.json";
    public UserInfo user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame frame;
    private JPanel panel;
    private JButton newInfoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private Clip clip;
    private AudioInputStream audioStream;


    // EFFECTS: Starts the food diary application
    public FoodDiaryAppGUI() throws FileNotFoundException, UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        audioSetUp();

        setUpFirstWindow();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // EFFECTS: saves the food diary to file
    public void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Successfully saved " + user.getFoodList().getDiaryName() + " to " + JSON_STORE,
                    "Note", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write " + user.getFoodList().getDiaryName()
                            + " to file: " + JSON_STORE,
                    "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFile() {
        try {
            user = jsonReader.read();
            JOptionPane.showMessageDialog(frame,
                    "Successfully loaded " + user.getFoodList().getDiaryName() + " from " + JSON_STORE,
                    "Note", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to load " + user.getFoodList().getDiaryName()
                            + " from file: " + JSON_STORE,
                    "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: sets up audio for buttons
    public void audioSetUp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("data/Button-click-sound-effect.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    // EFFECTS: Sets up the first window
    public void setUpFirstWindow() {
        frame = new JFrame();
        frame.setSize(400,800);


        setUpButtons();

        setUpPanel();

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Food Diary");
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: sets up the buttons for the window
    private void setUpButtons() {
        newInfoButton = new JButton("Create a new user info");;
        saveButton = new JButton("Save file");
        loadButton = new JButton("Load file");
        quitButton = new JButton("Quit");
        newInfoButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    // EFFECTS: sets up the panel on the window
    private void setUpPanel() {
        JLabel label = new JLabel();
        ImageIcon foodImage = new ImageIcon("01.png");
        label.setIcon(foodImage); //TODO:?????
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200,100,200,100));
        panel.setLayout(new GridLayout(0,1));
        panel.add(newInfoButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(quitButton);
        panel.add(label, BorderLayout.PAGE_END);
    }

    // EFFECTS: captures the buttons clicks an move on to the next phase
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newInfoButton) {
            clip.setFramePosition(0);
            clip.start();
            runUserInfoCollection();
        } else if ((e.getSource() == saveButton)) {
            clip.setFramePosition(0);
            clip.start();
            saveFile();
        } else if ((e.getSource() == loadButton)) {
            clip.setFramePosition(0);
            clip.start();
            loadFile();
            try {
                new PersonalDiary(user);
            } catch (Exception error) {
                System.out.println("Error! Can'run!");
            }
            clip.stop();
        } else if (e.getSource() == quitButton) {
            clip.setFramePosition(0);
            clip.start();
            System.exit(0);
        }
    }

    // EFFECTS: runs the UserInfoCollection class
    private void runUserInfoCollection() {
        FoodList defaulFoodList = new FoodList("default");
        user = new UserInfo("defalt", "default", 0, 0, defaulFoodList);
        try {
            new UserInfoCollection(user);
        } catch (Exception error) {
            System.out.println("Error! Can'run!");
        }
    }
}

