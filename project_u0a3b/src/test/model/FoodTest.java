package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    Food apple;
    Food beef;

    @BeforeEach
    public void setup() {
        apple = new Food("apple", 250,51);
        beef = new Food("beef", 80, 213);
    }

    @Test
    public void testConstructor() {
        assertEquals("apple", apple.getFoodName());
        assertEquals(250, apple.getAmount());
    }

    @Test
    public void testCalculateCalorie() {
        assertEquals(128, apple.calculateCalorie());
        assertEquals(170, beef.calculateCalorie());
    }

    @Test
    public void testAddAmount() {
        assertEquals(250, apple.getAmount());
        apple.addAmount(50);
        assertEquals(300, apple.getAmount());

    }

    @Test
    public void testReduceAmount() {
        assertTrue(apple.reduceAmount(50));
        assertEquals(200, apple.getAmount());
        assertTrue(apple.reduceAmount(200));
        assertEquals(0, apple.getAmount());
        assertFalse(apple.reduceAmount(200));
        assertEquals(0, apple.getAmount());
    }
}