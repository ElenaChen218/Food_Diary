package persistence;

import model.Food;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSonTest {
    protected void checkFood(String name, int amount, int calorie, Food food) {
        assertEquals(name, food.getFoodName());
        assertEquals(amount, food.getAmount());
        assertEquals(calorie, food.getCalorie());
    }
}
