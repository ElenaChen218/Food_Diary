package ui;

import model.FoodList;
import model.UserInfo;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Color.*;

public class PersonalDiary extends JFrame implements ActionListener {
    private UserInfo userInfo;
    private JFrame frame;
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private JButton addFoodButton;
    private JButton removeFoodButton;
    private JButton refreshButton;
    private JTextArea textArea;
    private ArrayList<String> foodStringToBePrinted;
    private Clip clip;


    // EFFECTS: constructs the personal diary window
    public PersonalDiary(UserInfo userInfo) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        audioSetUp();
        this.userInfo = userInfo;
        setUpWindow();
    }

    // EFFECTS: sets up the audio for button clicks
    public void audioSetUp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("data/Button-click-sound-effect.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    // EFFECTS: sets up the window
    private void setUpWindow() {
        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(450,760);

        setUpPanels();

        frame.add(displayPanel);
        frame.add(buttonPanel);
        frame.setTitle(userInfo.getFoodList().getDiaryName());
        frame.setVisible(true);
    }

    // EFFECTS: sets up the panel
    private void setUpPanels() {
        setUpDisplayPanel();

        setUpButtonPanel();

        setUpButtons();

        buttonPanel.add(addFoodButton);
        buttonPanel.add(removeFoodButton);
        buttonPanel.add(refreshButton);
    }

    private void setUpButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        buttonPanel.setBounds(0,660,450,50);
        buttonPanel.setBackground(WHITE);
    }

    // EFFECTS: sets up the panel
    private void setUpDisplayPanel() {
        displayPanel = new JPanel();
        displayPanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        displayPanel.setBounds(0,0,450,600);
        foodStringToBePrinted = new ArrayList<>();

        displayDiary(userInfo.getFoodList());
    }

    // EFFECTS: constructs the text area to display the diary
    private void displayDiary(FoodList foodlist) {
        textArea = new JTextArea();
        textArea.setSize(500,650);
        textArea.setEditable(false);
        textArea.setBackground(white);
        textArea.append("Here is your food diary: \n");

        displayText(userInfo.getFoodList());

        displayPanel.add(textArea);
    }

    // EFFECTS: sets up buttons on the window
    private void setUpButtons() {
        addFoodButton = new JButton("Add Food");
        removeFoodButton = new JButton("Remove Food");
        refreshButton = new JButton("Refresh");

        addFoodButton.addActionListener(this);
        removeFoodButton.addActionListener(this);
        refreshButton.addActionListener(this);
    }

    // EFFECTS: captures button clicks and move on to the next phase
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFoodButton) {
            clip.setFramePosition(0);
            clip.start();
            runAddFood();
        }  else if (e.getSource() == removeFoodButton) {
            clip.setFramePosition(0);
            clip.start();
            runRemoveFood();
        } else if (e.getSource() == refreshButton) {
            clip.setFramePosition(0);
            clip.start();
            displayText(userInfo.getFoodList());
        }
    }

    // MODIFIES: foodStringAlreadyPrinted
    // EFFECTS: prints the user's diary onto the screen
    private void displayText(FoodList foodList) {
//        foodStringToBePrinted = foodList.getFoodList();
//        for (String s : foodStringToBePrinted) {
//            if (!foodStringAlreadyPrinted.contains(s)) {
//                textArea.append("\n" + s);
//                foodStringAlreadyPrinted.add(s);
//            }
//        }
        foodStringToBePrinted = foodList.getFoodList();
        textArea.setText(null);
        textArea.append("Here is your food diary: ");
        for (String s : foodStringToBePrinted) {
            textArea.append("\n" + s);
        }

    }

    // EFFECTS: runs the AddFood class
    private void runAddFood() {
        try {
            new AddFood(userInfo);
        } catch (Exception error) {
            System.out.println("Error! Can't run!");
        }
    }

    // EFFECTS: runs the Remove Food class
    private void runRemoveFood() {
        try {
            new RemoveFood(userInfo);
        } catch (Exception error) {
            System.out.println("Error! Can't run!");
        }
    }
}
