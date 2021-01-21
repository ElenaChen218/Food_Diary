package persistence;

import model.FoodList;
import model.UserInfo;
import org.junit.jupiter.api.Test;
import persistence.JSonTest;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JSonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            UserInfo ui = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyUserInfo() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUserInfo.json");
        try {
            UserInfo ui = reader.read();
            assertEquals("", ui.getFoodList().getDiaryName());
            assertEquals(0, ui.getFoodList().getNumOfFood());
            assertEquals("", ui.getFirstName());
            assertEquals("", ui.getLastName());
            assertEquals(0, ui.getWeight());
            assertEquals(0, ui.getHeight());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testGeneralUserInfo() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserInfo.json");
        try {
            UserInfo ui = reader.read();
            assertEquals("Elena's diary", ui.getFoodList().getDiaryName());
            FoodList foodList = ui.getFoodList();
            assertEquals(2, foodList.getNumOfFood());
            assertEquals("Elena", ui.getFirstName());
            assertEquals("Chen", ui.getLastName());
            assertEquals(50, ui.getWeight());
            assertEquals(1.65, ui.getHeight());
            checkFood("apple", 100, 56, foodList.getNthFood(0));
            checkFood("banana", 250, 120, foodList.getNthFood(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
