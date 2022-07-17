import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HtmlTagsTests extends BaseTest {

    @Test
    public void AddRemoveElementsTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addElementButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addElementButton.click();
        addElementButton.click();
        List<WebElement> deleteButtonsList = driver.findElements(By.xpath("//button[text()='Delete']"));
        deleteButtonsList.get(0).click();
        Assert.assertEquals(1, driver.findElements(By.xpath(
                "//button[text()='Delete']")).size(), "There should be one Delete button");
    }

    @Test
    public void CheckboxesTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        Assert.assertFalse(checkboxes.get(0).isSelected(), "Checkbox 1 should be unchecked");
        checkboxes.get(0).click();
        Assert.assertTrue(checkboxes.get(0).isSelected(), "Checkbox 1 should be checked");
        Assert.assertTrue(checkboxes.get(1).isSelected(), "Checkbox 2 should be checked");
        checkboxes.get(1).click();
        Assert.assertFalse(checkboxes.get(1).isSelected(), "Checkbox 2 should be unchecked");
    }

    @Test
    public void dropDownTest1() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        Select select = new Select(driver.findElement(By.cssSelector("select[id=\"dropdown\"]")));
        List<WebElement> allOptionsList = select.getOptions();
        Assert.assertEquals(allOptionsList.get(0).getText(),
                "Please select an option", "Should be 'Please select an option'");
        Assert.assertEquals(allOptionsList.get(1).getText(),
                "Option 1", "Should be 'Option 1'");
        Assert.assertEquals(allOptionsList.get(2).getText(),
                "Option 2", "Should be 'Option 2'");
        allOptionsList.get(1).click();
        Assert.assertTrue(allOptionsList.get(1).isSelected(), "Option 1 should be selected");
        allOptionsList.get(2).click();
        Assert.assertTrue(allOptionsList.get(2).isSelected(), "Option 2 should be selected");
    }

    @Test
    public void inputsTests() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        Actions actions = new Actions(driver);
        actions.click(inputField).build().perform();

        Thread.sleep(1000);
        actions.sendKeys(Keys.chord(Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP)).build().perform();
        Assert.assertEquals(inputField.getAttribute("value"), "3", "Should be 3");
        actions.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN)).build().perform();
        Assert.assertEquals(inputField.getAttribute("value"), "-2", "Should be -2");
        Thread.sleep(3000);
    }
}
