import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwagLabsLocators extends BaseTest {

    @BeforeMethod
    public void navigate() {
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void checkLocators() throws InterruptedException {

        // Login page https://www.saucedemo.com/

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Error page - missing Username
        loginButton.click();
        WebElement errorMessageMissingUsername = driver.findElement(By.cssSelector("button[class=\"error-button\"]"));
        WebElement errorMessageMissingUsernameXpath =
                driver.findElement(By.xpath("//h3[text()=\"Epic sadface: Username is required\"]"));
        Assert.assertEquals(errorMessageMissingUsernameXpath.getText(), "Epic sadface: Username is required");


        Thread.sleep(2000);

    }
}
