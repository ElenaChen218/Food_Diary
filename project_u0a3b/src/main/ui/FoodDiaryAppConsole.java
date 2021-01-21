package ui;

import exceptions.InvalidAmountException;
import model.Food;
import model.FoodList;
import model.UserInfo;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// Template from TellerApp

public class FoodDiaryAppConsole {
    private static final String JSON_STORE = "./data/FoodDiary.json";
    private Scanner input;
    private String firstName;
    private String lastName;
    private double weight;
    private double height;
    public UserInfo user;
    private FoodList foodDiary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: Starts the food diary application
    public FoodDiaryAppConsole() throws FileNotFoundException {
        input = new Scanner(System.in);
        foodDiary = new FoodList("My Diary");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // EFFECTS: Runs the food diary application
    public void runApp() {



        boolean onGoing = true;
        String command = null;

        init();

        while (onGoing) {

            getStarted();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("c")) {
                createUserInfo();
                System.out.println("You've successfully created your user info!!!");
                nextPhase();
            } else if (command.equals("l")) {
                loadFile();
                nextPhase();
            } else if (command.equals("q")) {
                System.out.println("\nThanks for using Food Diary!");
                onGoing = false;
            } else {
                System.out.println("Invalid input!!! Please try again.");
            }

        }
    }


    // EFFECTS: Runs the next phase of food dairy application after creating user info.
    public void nextPhase() {
        boolean onGoing = true;
        String command = null;

        init();

        while (onGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                onGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for using Food Diary!");

    }

    // EFFECTS: Prints out the opening menu
    public void getStarted() {
        System.out.println("\nWelcome come to Food Diary. Let's get Started!!!");
        System.out.println("c -> Create new user info.");
        System.out.println("l -> Load user info.");
        System.out.println("q -> Quit");
    }


    // EFFECTS: Gets the inputs of user info from user
    public void createUserInfo() {
        displayFirstNameInput();
        displayLastNameInput();
        displayWeightInput();
        displayHeightInput();
        displayFoodDiaryInput();

        user = new UserInfo(firstName, lastName,weight, height, foodDiary);

    }

    // EFFECTS: Displays the menu of operations on food diary.
    public void displayMenu() {
        System.out.println("\nPlease select from: ");
        System.out.println("1 -> Create a new diary. ");
        System.out.println("2 -> Get my BMI. ");
        System.out.println("3 -> Add food. ");
        System.out.println("4 -> reduce food. ");
        System.out.println("5 -> Get my total calorie intake. ");
        System.out.println("6 -> Print my food diary. ");
        System.out.println("s -> Save Food Diary to file.");
        System.out.println("q -> Go back to main and start over. ");
    }

    // EFFECTS: Processes to the next phase of the app base on command input.
    public void processCommand(String command) {
        if (command.equals("1")) {
            createNewDiary();
        } else if (command.equals("2")) {
            getBMI();
        } else if (command.equals("3")) {
            addFood();
        } else if (command.equals("4")) {
            reduceFood();
        } else if (command.equals("5")) {
            calculateCalorie();
        } else if (command.equals("6")) {
            printDiary();
        } else if (command.equals("s")) {
            saveFile();
        } else {
            System.out.println("Invalid input!!! Please try again.");
        }
    }


    // EFFECTS: Creates a new diary that overwrites the previous one.
    public void createNewDiary() {
        System.out.println("Please input the name of your food diary:");
        String foodListName = input.next();
        foodDiary = new FoodList(foodListName);
        System.out.println("You've successfully created a new food diary!");
    }

    // MODIFIES: Foodlist
    // EFFECTS: Adds food to the diary.
    public void addFood() {
        Food food;
        System.out.print("Please enter the food name: ");
        String foodName = input.next();
        System.out.println("Please enter the amount(in grams with no decimals): ");
        int amount = input.nextInt();
        System.out.println("Please enter the calories per 100 grams as an integer: ");
        int calorie = input.nextInt();

        food = new Food(foodName,amount,calorie);
        try {
            foodDiary.addFood(food);
        } catch (Exception e) {
            System.out.println("Please enter a valid value!");
        }
        System.out.println("You've successfully added " + foodName + " to your food diary!");
    }

    // MODIFIES: Foodlist
    // EFFECTS: Reduces food from diary.
    public void reduceFood() {
        System.out.print("Please enter the food name: ");
        String foodName = input.next();
        if (foodDiary.canBeFound(foodName)) {
            System.out.println("Please enter the amount as an integer: ");
            int amount = input.nextInt();
            foodDiary.reduceFood(foodName,amount);
            System.out.println("You've successfully reduced " + foodName + " by " + amount + "g.");
        } else {
            System.out.println("Food cannot be found!!! Please try another food.");
        }
    }


    // EFFECTS: Calculates the total calorie intake
    public void calculateCalorie() {
        int calSoFar;
        calSoFar = foodDiary.calculateTotalCalorie();
        System.out.println("Your total calorie intake so far is: " + calSoFar + "cal");
    }

    // EFFECTS: Prints out the diary
    private void printDiary() {
        ArrayList<String> foodList = user.getFoodList().getFoodList(); //EDITED
        System.out.println("\nHere is your food Diary: ");
        for (String foodItem : foodList) {
            System.out.println("\n" + foodItem);
        }
    }

    // EFFECTS: Displays user info input instruction
    private void displayFirstNameInput() {
        System.out.println("\nPlease input your first Name:");
        firstName = input.next();
    }

    // EFFECTS: Displays user info input instruction
    private void displayLastNameInput() {
        System.out.println("Please input your last Name:");
        lastName = input.next();
    }

    // EFFECTS: Displays user info input instruction
    private void displayWeightInput() {
        System.out.println("Please input your weight (in kilograms with two decimal places):");
        weight = input.nextDouble();
    }

    // EFFECTS: Displays user info input instruction
    private void displayHeightInput() {
        System.out.println("Please input your height (in meters with two decimal places):");
        height = input.nextDouble();
    }

    // EFFECTS: Displays food diary name input instruction
    private void displayFoodDiaryInput() {
        System.out.println("Please input the name of your first food diary:");
        String foodListName = input.next();
        foodDiary = new FoodList(foodListName);

    }


    // EFFECTS: Prints the BMI of the user
    public void getBMI() {
        System.out.println(user.calculateBMItoString());
    }


    // EFFECTS: saves the food diary to file
    private void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Successfully saved " + user.getFirstName() + "'s " + user.getFoodList().getDiaryName()
                    + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write " + user.getFirstName() + "'s " + user.getFoodList().getDiaryName()
                    + " to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFile() {
        try {
            user = jsonReader.read();
            System.out.println("Successfully loaded " + user.getFirstName() + "'s " + user.getFoodList().getDiaryName()
                    + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load " + user.getFirstName() + "'s " + user.getFoodList().getDiaryName()
                    + " from file: " + JSON_STORE);
        }
    }

    // Initiates the system
    private void init() {
        input = new Scanner(System.in);
    }





//    public static void main(String[] args) {
//        try {
//            new FoodDiaryApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found!!! Unable to run application.");
//        }
//    }


}
