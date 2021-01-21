package model;

import org.json.JSONObject;
import persistence.Writable;

public class UserInfo implements Writable {

    private String firstName;  // stores the first name of the user
    private String lastName;   // stores the last name of the user
    private double weight;     // stores the weight of the user in Kg (two decimal places maximum)
    private double height;     // store the height of the user in m (two decimal places maximum)
    private FoodList foodList; // // store the user's foodList

    // Firstname, lastname, age, gender, weight and height in stored in the UserInfo
    public UserInfo(String fn, String ln, double w, double h, FoodList fl) {
        firstName = fn;
        lastName = ln;
        weight = w;
        height = h;
        foodList = fl;

    }

    // EFFECTS: Calculates the BMI of the user base on the UserInfo using the formula BMI = weight(kg)/height^2(m),
    //          round the value to two decimal places.
    //          Return the sentence that tells the user his/her BMI.
    public String calculateBMItoString() {
        double bmi = weight / (height * height);
        String bmiToString = String.format("%.2f", bmi);
        return "Your BMI is " + bmiToString + ".";
    }

    //EFFECTS: Returns the first name of the user.
    public String getFirstName() {
        return firstName;
    }

    //EFFECTS: Returns the last name of the user.
    public String getLastName() {
        return lastName;
    }

    //EFFECTS: Returns the weight of the user.
    public double getWeight() {
        return weight;
    }

    //EFFECTS: Returns the height of the user.
    public double getHeight() {
        return height;
    }

    //EFFECTS: Returns the food list of the user.
    public FoodList getFoodList() {
        return foodList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setFoodList(FoodList foodList) {
        this.foodList = foodList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("weight", weight);
        json.put("height", height);
        json.put("foodList", foodList.toJson());
        return json;
    }
}
