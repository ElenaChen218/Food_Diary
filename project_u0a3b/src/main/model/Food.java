package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class Food implements Writable {

    public String foodName; //stores the name of the food eaten
    public int amount;  // stores the amount of food eaten in grams
    public int calorie; // the amount of calories per 100g of food, information from the database

    // REQUIRES: amount >= 0
    // EFFECTS: name of the food is set to the foodName; the amount eaten must be greater than 0
    public Food(String foodName, int amount, int calorie) {
        this.foodName = foodName;
        this.amount = amount;
        this.calorie = calorie;
    }

    // EFFECTS: calculate the total calorie of the food eaten, round the answer to the closest integer
    public double calculateCalorie() {
        return Math.round(amount * (calorie / 100.00));
    }

    // MODIFIES: this
    // EFFECTS: Adds additional amount to the food
    public void addAmount(int i) {
        amount += i;
    }

    // MODIFIES: this
    // EFFECTS: If the amount to be reduced is greater the original amount of food, returns false. Otherwise, reduces
    //          the amount from the food and returns true.
    public boolean reduceAmount(int i) {
        if (amount < i) {
            return false;
        } else {
            amount -= i;
            return true;
        }
    }

    //EFFECTS: Returns the name of the food.
    public String getFoodName() {
        return foodName;
    }

    //EFFECTS: Returns the amount of the food.
    public int getAmount() {
        return amount;
    }

    //EFFECTS: Returns the calorie per 100g of food.
    public int getCalorie() {
        return calorie;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodName", foodName);
        json.put("amount", amount);
        json.put("calorie", calorie);
        return json;
    }

}
