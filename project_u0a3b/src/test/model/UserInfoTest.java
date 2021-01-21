package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInfoTest {

    UserInfo user;
    FoodList foodList;

    @BeforeEach
    public void setup(){
        foodList = new FoodList("firstList");
        user = new UserInfo("Bill", "Gates", 70,1.8, foodList);
    }

    @Test
    public void testConstructor() {
        assertEquals("Bill", user.getFirstName());
        assertEquals("Gates", user.getLastName());
        assertEquals(70, user.getWeight());
        assertEquals(1.8, user.getHeight());

    }

    @Test
    public void testCalculateBMItoString() {
        assertEquals("Your BMI is 21.60.", user.calculateBMItoString());
    }

    @Test
    public void setWeightTest() {
        assertEquals(70, user.getWeight());
        user.setWeight(80.0);
        assertEquals(80, user.getWeight());
    }

    @Test
    public void setHeightTest() {
        assertEquals(1.8, user.getHeight());
        user.setHeight(1.9);
        assertEquals(1.9, user.getHeight());
    }

    @Test
    public void setFirstNameTest() {
        assertEquals("Bill", user.getFirstName());
        user.setFirstName("Jack");
        assertEquals("Jack", user.getFirstName());
    }

    @Test
    public void setLastNameTest() {
        assertEquals("Gates", user.getLastName());
        user.setLastName("Ben");
        assertEquals("Ben", user.getLastName());
    }

    @Test
    public void setFoodListTest() {
        FoodList anotherFoodList = new FoodList("secondList");
        user.setFoodList(anotherFoodList);
    }

}
