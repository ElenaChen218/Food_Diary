package model;

import exceptions.InvalidAmountException;
import exceptions.InvalidCalorieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FoodListTest {
    FoodList foodList;
    Food apple;
    Food beef1;
    Food beef2;
    Food banana;
    Food invalidAmount;
    Food invalidCalorie;

    @BeforeEach
    public void setup() {
        foodList = new FoodList("List1");
        apple = new Food("apple", 250,51);
        beef1 = new Food("beef", 80, 213);
        beef2 = new Food("beef", 100, 213);
        banana = new Food("banana", 10, 10);
        invalidAmount = new Food("invalidAmount", -200, 10);
        invalidCalorie = new Food("invalidCalorie", 200, -10);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, foodList.getNumOfFood());
        assertEquals("List1", foodList.getDiaryName());
    }

    @Test
    public void testAddFoodNoException() {
        try {
            foodList.addFood(apple);
            foodList.addFood(beef1);
            assertEquals(2, foodList.getNumOfFood());
            // adds a food that already exists
            foodList.addFood(beef2);
            assertEquals(2, foodList.getNumOfFood());
            assertEquals(180, beef1.getAmount());
            //pass
        } catch (InvalidAmountException e) {
            fail("Exception not expected!");
        } catch (InvalidCalorieException e) {
            fail("Exception not expected!");
        }
    }

    @Test
    public void testAddFoodInvalidAmountException() {
        try {
            foodList.addFood(invalidAmount);
            fail("Exception should have been thrown!");
        } catch (InvalidAmountException e) {
            // pass
        } catch (InvalidCalorieException e) {
            fail("Exception not expected!");
        }
    }

    @Test
    public void testAddFoodInvalidCalorieException() {
        try {
            foodList.addFood(invalidCalorie);
            fail("Exception should have been thrown!");
        } catch (InvalidAmountException e) {
            fail("Exception not expected!");
        } catch (InvalidCalorieException e) {
            //pass
        }
    }

    @Test
    public void testCanBeFound() {
        try {
            foodList.addFood(apple);
            foodList.addFood(beef1);
            assertTrue(foodList.canBeFound("beef"));
            assertFalse(foodList.canBeFound("egg"));
            // pass
        } catch (Exception e) {
            fail("Exception not expected!");
        }
    }

    @Test
    public void testReduceFood() {
        try {
            foodList.addFood(apple);
            foodList.addFood(beef1);

            // successfully reduce the food by certain amount
            assertTrue(foodList.reduceFood("beef", 10));
            assertEquals(70, beef1.getAmount());
            // failed to reduce by entering the amount great than the original amount
            // of the food
            assertFalse(foodList.reduceFood("beef", 100));
            assertEquals(2, foodList.getNumOfFood());
            assertEquals(70, beef1.getAmount());
            // successfully cleared out the food by reducing its amount to 0
            assertTrue(foodList.reduceFood("beef", 70));
            assertEquals(1, foodList.getNumOfFood());
            // failed to reduce the food by entering the food that doesn't exist in the list
            assertFalse(foodList.reduceFood("egg", 10));
            // pass
        } catch (Exception e) {
            fail("Exception not expected!");
        }
    }

    @Test
    public void testCalculateTotalCalorie() {
        try {
            // empty list
            assertEquals(0, foodList.calculateTotalCalorie());
            // non-empty list
            foodList.addFood(apple);
            foodList.addFood(beef1);
            foodList.addFood(beef2);
            assertEquals(511, foodList.calculateTotalCalorie());
        } catch (Exception e) {
            fail("Exception not expected!");
        }
    }

    @Test
    public void testGetFoodList() {
        try {
            foodList.addFood(apple);
            foodList.addFood(beef1);
            foodList.addFood(beef2);
            assertEquals(2, foodList.getFoodList().size());
            assertEquals("apple - 250g - 128.0cal",
                    foodList.getFoodList().get(0));
            assertEquals("beef - 180g - 383.0cal",
                    foodList.getFoodList().get(1));
        } catch (Exception e) {
            fail("Exception not expected!");
        }
    }

    @Test
    public void testGetNumFood() {
        assertEquals( 0,foodList.getNumOfFood());

        try {
            foodList.addFood(apple);
            foodList.addFood(beef1);
            foodList.addFood(beef2);
            assertEquals(2, foodList.getNumOfFood());
        } catch (Exception e) {
            fail("Exception not expected!");
        }
    }

    @Test
    public void testRemoveFood() {
        try {
            assertEquals(0, foodList.getNumOfFood());
            foodList.addFood(apple);
            foodList.addFood(beef1);
            assertTrue(foodList.canBeFound(apple.getFoodName()));
            foodList.removeFood(apple.getFoodName());
            assertEquals(1, foodList.getNumOfFood());
            foodList.removeFood(banana.getFoodName());
            assertEquals(1, foodList.getNumOfFood());
            assertTrue(foodList.canBeFound(beef1.getFoodName()));
            foodList.removeFood(beef1.getFoodName());
            assertEquals(0, foodList.getNumOfFood());
        } catch (Exception e) {
            fail("Exception not expected!");
        }
    }

}
