import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;

public class Hovers extends BaseTest {

    @BeforeMethod
    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/hovers");
    }

    @Test
    public void hoversTest() throws InterruptedException {
        List<WebElement> allAvatars = driver.findElements(By.cssSelector(".figure"));
        Actions actions = new Actions(driver);
        for (WebElement avatar : allAvatars
        ) {
            actions.moveToElement(avatar).build().perform();
            Thread.sleep(3000);
        }


    }

}
