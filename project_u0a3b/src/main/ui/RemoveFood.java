package ui;

import model.UserInfo;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RemoveFood extends JFrame implements ActionListener {
    private UserInfo userInfo;
    private JFrame frame;
    private JPanel panel;
    JButton removeButton;
    JButton cancelButton;
    JTextField foodNameField;
    JLabel foodNameLabel;
    private Clip clip;

    // EFFECTS: constructs the window to add food
    public RemoveFood(UserInfo userInfo) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
        frame.setSize(200,150);
        frame.setLayout(new FlowLayout());
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        frame.add(panel, BorderLayout.CENTER);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Remove Food");

        setUpComponents();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(foodNameLabel);
        panel.add(foodNameField);
        panel.add(removeButton);
        panel.add(cancelButton);
        cancelButton.setLocation(150,150);

        frame.setVisible(true);
    }

    // EFFECTS: sets up components on the window
    private void setUpComponents() {
        removeButton = new JButton("Remove");
        cancelButton = new JButton("Cancel");

        removeButton.addActionListener(this);
        cancelButton.addActionListener(this);

        foodNameLabel = new JLabel("Enter the food name:");

        foodNameField = new JTextField();

        foodNameField.setPreferredSize(new Dimension(100,40));
    }

    // EFFECTS: captures button clicks and move onto the next phase
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            clip.setFramePosition(0);
            clip.start();
            removeFood();
            frame.dispose();
        } else if (e.getSource() == cancelButton) {
            clip.setFramePosition(0);
            clip.start();
            frame.dispose();
        }
    }

    // MODIFIES: UserInfo
    // EFFECTS: remove the food from the foodList
    private void removeFood() {
        String foodName = foodNameField.getText();
        userInfo.getFoodList().removeFood(foodName);
    }
}
