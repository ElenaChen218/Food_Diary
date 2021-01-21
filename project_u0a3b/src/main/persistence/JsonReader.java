package persistence;


import exceptions.InvalidAmountException;
import exceptions.InvalidCalorieException;
import model.Food;
import model.FoodList;
import model.UserInfo;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;
// Represents a reader that reads foodList from JSON data stored in file

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user info from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserInfo read() throws IOException  {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserInfo(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Parses user info from JSon object and returns it
    private UserInfo parseUserInfo(JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        double weight = jsonObject.getDouble("weight");
        double height = jsonObject.getDouble("height");
        FoodList foodList = parseFoodList(jsonObject);
        UserInfo userInfo = new UserInfo(firstName, lastName, weight, height, foodList);
        return userInfo;
    }

    // EFFECTS: parses food list from JSON object and adds them to the user info
    private FoodList parseFoodList(JSONObject jsonObject) {
        JSONObject diaryObject = (JSONObject) jsonObject.get("foodList");
        String foodListName = (String) diaryObject.get("foodListName");
        FoodList fl = new FoodList(foodListName);
        addFoods(fl, jsonObject);
        return fl;
    }

    // MODIFIES: fl
    // EFFECTS: parses food from JSON object and adds them to food list
    private void addFoods(FoodList fl, JSONObject jsonObject) {
        JSONObject diaryObject = (JSONObject) jsonObject.get("foodList");
        JSONArray jsonArray = diaryObject.getJSONArray("foodList");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fl, nextFood);
        }
    }


    // MODIFIES: fl
    // EFFECTS: parses food from JSON object and adds it to food list
    private void addFood(FoodList fl, JSONObject jsonObject) {
        String foodName = jsonObject.getString("foodName");
        int amount = jsonObject.getInt("amount");
        int calorie = jsonObject.getInt("calorie");
        Food food = new Food(foodName, amount, calorie);
        try {
            fl.addFood(food);
            //pass
        } catch (Exception e) {
            System.out.println("Please enter a valid amount!");
        }
    }


}
