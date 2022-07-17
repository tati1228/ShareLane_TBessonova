import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenu extends BaseTest {
    @BeforeMethod
    public void navigate() {
        driver.get("http://the-internet.herokuapp.com/context_menu");
    }

    @Test
    public void ContextMenuTest() throws InterruptedException {
        WebElement hotSpot = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(hotSpot).build().perform();

        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        driver.switchTo().defaultContent();
        Thread.sleep(3000);
    }
}
