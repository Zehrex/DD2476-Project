2
https://raw.githubusercontent.com/EmojiMeals/meals-java/master/src/test/java/MealsTest.java
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MealsTest {

    @Test
    public void TestItWorks() throws IOException {
        String result = Meals.mealify("🐟🍚");
        Assert.assertEquals(result,"🍣");
    }

    @Test
    public void TestItWorksTheOtherWayAround() throws IOException {
        String result = Meals.mealify("🍚🐟");
        Assert.assertEquals(result,"🍣");
    }
}
