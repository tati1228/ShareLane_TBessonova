import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DropdownTest extends BaseTest {

    @BeforeMethod
    public void navigate() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
    }


    @Test
    public void selectDropdownTest() throws InterruptedException {
        Select select = new Select(driver.findElement(By.cssSelector("select[id=\"dropdown\"]")));
        select.selectByIndex(2);
        select.selectByValue("1");
        select.selectByVisibleText("Option 2");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2", "Option 2 should be selected");
        List<WebElement> allOptions = select.getOptions();
        List<String> allOptionsNames = allOptions.stream().map(option -> option.getText()).toList();
        List<String> expectedOptionNames = new ArrayList<>();
        expectedOptionNames.add("Please select an option");
        expectedOptionNames.add("Option 1");
        expectedOptionNames.add("Option 2");
        Assert.assertEquals(allOptionsNames, expectedOptionNames);


        Thread.sleep(3000);
    }

}
