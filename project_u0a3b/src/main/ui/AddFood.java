package ui;

import exceptions.InvalidAmountException;
import exceptions.InvalidCalorieException;
import model.Food;
import model.FoodList;
import model.UserInfo;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AddFood extends JFrame implements ActionListener {
    private UserInfo userInfo;
    private JFrame frame;
    private JPanel panel;
    JButton addButton;
    JButton cancelButton;
    JTextField foodNameField;
    JTextField amountField;
    JTextField calorieField;
    JLabel foodNameLabel;
    JLabel amountLabel;
    JLabel calorieLabel;
    private Clip clip;

    // EFFECTS: constructs the window to add food
    public AddFood(UserInfo userInfo) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
        frame.setSize(200,250);
        frame.setLayout(new FlowLayout());
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        frame.add(panel, BorderLayout.CENTER);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Add Food");

        setUpComponents();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(foodNameLabel);
        panel.add(foodNameField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(calorieLabel);
        panel.add(calorieField);
        panel.add(addButton);
        panel.add(cancelButton);
        cancelButton.setLocation(150,150);

        frame.setVisible(true);
    }

    // EFFECTS: sets up components on the window
    private void setUpComponents() {
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        foodNameLabel = new JLabel("Enter the food name:");
        amountLabel = new JLabel("Enter the amount:");
        calorieLabel = new JLabel("Enter the calorie:");

        foodNameField = new JTextField();
        amountField = new JTextField();
        calorieField = new JTextField();

        foodNameField.setPreferredSize(new Dimension(100,40));
        amountField.setPreferredSize(new Dimension(100,40));
        calorieField.setPreferredSize(new Dimension(100,40));
    }

    // EFFECTS: captures button clicks and move onto the next phase
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            clip.setFramePosition(0);
            clip.start();
            storeFood();
        } else if (e.getSource() == cancelButton) {
            clip.setFramePosition(0);
            clip.start();
            frame.dispose();
        }
    }

    // MODIFIES: UserInfo
    // EFFECTS: adds new Food to FoodList
    private void storeFood() {
        String foodName = foodNameField.getText();
        String amountString  = amountField.getText();
        int amountField = Integer.parseInt(amountString);
        String calorieString  = calorieField.getText();
        int calorieField = Integer.parseInt(calorieString);
        Food food = new Food(foodName,amountField,calorieField);
        try {
            userInfo.getFoodList().addFood(food);
            frame.dispose();
        } catch (InvalidAmountException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid value!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidCalorieException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid calorie!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
