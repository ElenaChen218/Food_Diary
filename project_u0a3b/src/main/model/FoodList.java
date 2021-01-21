package model;

import exceptions.InvalidAmountException;
import exceptions.InvalidCalorieException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;



public class FoodList implements Writable {

    private String foodListName;
    private ArrayList<Food> foodList;

    // EFFECTS: Creates an empty list of food
    public FoodList(String foodListName) {
        this.foodListName = foodListName;
        this.foodList = new ArrayList<>();
    }

    // MODIFIES: this, Food
    // EFFECTS: If the food already exists in the foodList, only adds additional amount to the food.
    //          If the food doesn't exist in the foodList, adds the food to the end of the list.
    public void addFood(Food f) throws InvalidAmountException, InvalidCalorieException {
        if ((f.getAmount() >= 0) & (f.getCalorie() >= 0)) {
            if (canBeFound(f.getFoodName())) {
                for (Food food : foodList) {
                    if (food.getFoodName().equals(f.getFoodName())) {
                        food.addAmount(f.getAmount());
                    }
                }
            } else {
                foodList.add(f);
            }
        } else if (f.getAmount() < 0) {
            throw new InvalidAmountException();
        } else if (f.getCalorie() < 0) {
            throw new InvalidCalorieException();
        }
    }

    //EFFECTS: Returns true if a food with the given food name can be found, otherwise false.
    public boolean canBeFound(String foodName) {
        for (Food food : foodList) {
            if (food.getFoodName().equals(foodName)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes the food from the foodlist if the food is already in the foodlist
    public void removeFood(String foodName) {
        Food foodToBeRemoved = new Food("default", 0,0);
        if (canBeFound(foodName)) {
            for (Food food : foodList) {
                if (food.getFoodName().equals(foodName)) {
                    foodToBeRemoved = food;
                }
            }
        }
        foodList.remove(foodToBeRemoved);
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this, Food
    // EFFECTS: Remove certain amount of the food by entering the food name and amount to be removed. If the food
    //          doesn't exist in the list, return false. If the amount to
    //          removed the greater than the amount of this food in the list, return false. If the amount to be removed
    //          is the same as the amount of this food in the list, remove the food from the list and return true. If
    //          the amount to be removed is less than the amount of food in the list, only deduct the amount and return
    //          true.
    public boolean reduceFood(String foodName, int amount) {
        for (Food food : foodList) {
            if (food.getFoodName().equals(foodName)) {
                if (food.getAmount() > amount) {
                    food.reduceAmount(amount);
                    return true;
                } else if (food.getAmount() == amount) {
                    foodList.remove(food);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    // EFFECTS: Calculates the total calories of all food in the list
    public int calculateTotalCalorie() {
        int acc = 0;
        for (Food next : foodList) {
            acc += next.calculateCalorie();
        }
        return acc;
    }

    // EFFECTS: Returns the report of all food eaten so far as a list of strings,  in the format of
    //          "Food name - amount g - calorie cal";
    public ArrayList<String> getFoodList() {
        ArrayList<String> foodEaten = new ArrayList<>();
        for (Food food : foodList) {
            int i = food.getAmount();
            foodEaten.add(
                    food.getFoodName() + " - " + i + "g" + " - " + food.calculateCalorie() + "cal");
        }
        return foodEaten;
    }

    // EFFECTS: Returns the nth food in the food list
    public Food getNthFood(int n) {
        return foodList.get(n);
    }

    // EFFECTS: Returns the number Food in the FoodList
    public int getNumOfFood() {
        return foodList.size();
    }

    // EFFECTS: Returns the name of the FoodList
    public String getDiaryName() {
        return foodListName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodListName", foodListName);
        json.put("foodList", foodListToJson());
        return json;
    }

    protected JSONArray foodListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food : foodList) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }

}
