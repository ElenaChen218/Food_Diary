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

public class UserInfoCollection extends JFrame implements ActionListener {
    JButton submitButton;
    JTextField firstNameField;
    JTextField lastNameField;
    JTextField weightField;
    JTextField heightField;
    JTextField diaryNameField;
    JLabel firstNameLable;
    JLabel lastNameLable;
    JLabel weightLable;
    JLabel heightLable;
    JLabel diaryNameLabel;
    private UserInfo userInfo;
    private JFrame frame;
    private JPanel panel;
    private Clip clip;


    // EFFECTS: constructs the UserInfoCollection window
    public UserInfoCollection(UserInfo userInfo) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        audioSetUp();

        this.userInfo = userInfo;
        setUpWindow();
        setUpComponents();

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("User Info Collection");
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: sets up the audio
    public void audioSetUp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("data/Button-click-sound-effect.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    // EFFECTS: sets up the window
    private void setUpWindow() {
        frame = new JFrame();
        frame.setSize(100,300);
        //frame.setLayout(new FlowLayout());
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("User Info Collection");
    }

    // EFFECTS: sets up different componets on the window
    private void setUpComponents() {
        setUpButton();
        setUpLabels();
        setUpFields();
        addComponents();
    }

    // EFFECTS: sets up buttonson the window
    private void setUpButton() {
        submitButton = new JButton("Submit");
        submitButton.setLocation(200,100);
        submitButton.addActionListener(this);
    }

    // EFFECTS: sets up labels on the window
    private void setUpLabels() {
        firstNameLable = new JLabel("Enter your first name:");
        lastNameLable = new JLabel("Enter your last name:");
        weightLable = new JLabel("Enter your weight:");
        heightLable = new JLabel("Enter your height:");
        diaryNameLabel = new JLabel("Create a name for your diary:");
    }

    // EFFECTS: sets up fields on the window
    private void setUpFields() {
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        weightField = new JTextField();
        heightField = new JTextField();
        diaryNameField = new JTextField();

        firstNameField.setPreferredSize(new Dimension(250,40));
        lastNameField.setPreferredSize(new Dimension(250,40));
        weightField.setPreferredSize(new Dimension(250,40));
        heightField.setPreferredSize(new Dimension(250,40));
        diaryNameField.setPreferredSize(new Dimension(250,40));
    }

    // EFFECTS: adds different components onto the window
    private void addComponents() {
        panel.add(firstNameLable);
        panel.add(firstNameField);
        panel.add(lastNameLable);
        panel.add(lastNameField);
        panel.add(weightLable);
        panel.add(weightField);
        panel.add(heightLable);
        panel.add(heightField);
        panel.add(diaryNameLabel);
        panel.add(diaryNameField);

        panel.add(submitButton);
    }

    // EFFECTS: captures button clicks and move on to the next phase
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            clip.setFramePosition(0);
            clip.start();
            updateInfo();
            try {
                new PersonalDiary(userInfo);
            } catch (Exception error) {
                System.out.println("Error! Can'run!");
            }
            frame.dispose();
        }
    }

    // MODIFIES: userInfo
    // EFFECTS: updates the userInfo
    private void updateInfo() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        Double weight = Double.parseDouble(weightField.getText());
        Double height = Double.parseDouble(heightField.getText());
        String diaryName = diaryNameField.getText();
        FoodList foodList = new FoodList(diaryName);
        userInfo.setFirstName(firstName);
        userInfo.setLastName(lastName);
        userInfo.setWeight(weight);
        userInfo.setHeight(height);
        userInfo.setFoodList(foodList);
    }

}
