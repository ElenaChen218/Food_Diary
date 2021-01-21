package persistence;

import exceptions.InvalidAmountException;
import model.Food;
import model.FoodList;
import model.UserInfo;
import org.junit.jupiter.api.Test;
import persistence.JSonTest;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JSonWriterTest extends JSonTest {
    //UserInfo(String fn, String ln, int a, String g, double w, double h, FoodList fl)
    Food apple = new Food("pear", 100, 50);
    Food banana = new Food("banana", 250, 120);
    Food beef = new Food("beef", 150, 130);

    @Test
    public void testWriterInvalidFile() {
        try {
            FoodList foodList = new FoodList("Elena's foodlist");
            UserInfo ui = new UserInfo("Elena", "Chen", 50,1.65, foodList);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyUserInfo() {
        try {
            FoodList foodList = new FoodList("");
            UserInfo ui = new UserInfo("", "",0,0, foodList);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUserInfo.json");
            writer.open();
            writer.write(ui);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUserInfo.json");
            ui = reader.read();
            assertEquals("", ui.getFoodList().getDiaryName());
            assertEquals(0, ui.getFoodList().getNumOfFood());
            assertEquals("", ui.getFirstName());
            assertEquals("", ui.getLastName());
            assertEquals(0, ui.getWeight());
            assertEquals(0, ui.getHeight());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralWorkroom() {
        try {
            FoodList foodList = new FoodList("Cheryl's diary");
            UserInfo ui = new UserInfo("Cheryl", "Chang", 55,1.70, foodList);
            foodList.addFood(apple);
            foodList.addFood(banana);
            foodList.addFood(beef);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUserInfo.json");
            writer.open();
            writer.write(ui);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUserInfo.json");
            ui = reader.read();
            assertEquals("Cheryl's diary", ui.getFoodList().getDiaryName());
            assertEquals(3, foodList.getNumOfFood());
            assertEquals("Cheryl Chang", ui.getFirstName() + " " + ui.getLastName());
            assertEquals(56.7, ui.getWeight() + ui.getHeight());
            checkFood("pear", 100, 50, foodList.getNthFood(0));
            checkFood("banana", 250, 120, foodList.getNthFood(1));
            checkFood("beef", 150, 130, foodList.getNthFood(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
