import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.lang.Thread.*;

public class SignUpTests extends BaseTest {

    @BeforeMethod
    public void navigate() {
        driver.get("https://sharelane.com/cgi-bin/register.py");
    }

    public void navigateToRegistration(String zipCode) {
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys(zipCode);
        WebElement continueButton = driver.findElement(By.cssSelector("[value=\"Continue\"]"));
        continueButton.click();
    }

    @Test
    public void zipCodePositiveTest() throws InterruptedException {
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed(),
                "There should be no zip_code field");
        softAssert.assertTrue((driver.findElement(By.name("first_name")).isDisplayed()),
                "There should be a first_name field");
        Thread.sleep(2000);
        softAssert.assertAll();
    }

    @Test
    public void zipCodeLessThanFive() throws InterruptedException {
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("123");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=\"Continue\"]"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(), "Sign Up page should still be displayed");
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(),"Error message should be shown");
        WebElement ERRORMESSAGETEXT = driver.findElement(By.className("error_message"));
        Assert.assertEquals(ERRORMESSAGETEXT.getText(), "Oops, error on page. ZIP code should have 5 digits");
        Thread.sleep(2000);
    }

    @Test
    public void zipCodeMoreThanFive() throws InterruptedException {
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("123456");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=\"Continue\"]"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(), "Sign Up page should still be displayed");
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Error message should be shown");
        WebElement ERRORMESSAGETEXT = driver.findElement(By.className("error_message"));
        Assert.assertEquals(ERRORMESSAGETEXT.getText(), "Oops, error on page. ZIP code should have 5 digits");
        Thread.sleep(2000);
    }

    @Test
    public void registrationPositiveTest() throws InterruptedException {
        navigateToRegistration("12345");

        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password1"));
        WebElement confirmPasswordInput = driver.findElement(By.name("password2"));
        WebElement registerButton = driver.findElement(By.cssSelector("[value=\"Register\"]"));

        firstNameInput.sendKeys("a");
        lastNameInput.sendKeys("b");
        emailInput.sendKeys("test@test.com");
        passwordInput.sendKeys("123456");
        confirmPasswordInput.sendKeys("123456");
        registerButton.click();

        SoftAssert softAssert = new SoftAssert();
        WebElement CONFIRMATION = driver.findElement(By.className("confirmation_message"));
        softAssert.assertTrue(driver.findElements(By.name("first_name")).isEmpty(),
                "There should be no First name field");
        softAssert.assertTrue(CONFIRMATION.isDisplayed(),
                "Confirmation message should be displayed");
        softAssert.assertEquals(CONFIRMATION.getText(), "Account is created!", "There should be 'Account is created!");


        Thread.sleep(2000);

        softAssert.assertAll();
    }

    @Test
    public void registrationEmptyFields() {
        navigateToRegistration("12345");

        WebElement registerButton = driver.findElement(By.cssSelector("[value=\"Register\"]"));
        registerButton.click();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(),
                "First name field should be displayed");
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        softAssert.assertTrue(errorMessage.isDisplayed(),
                "Error message should be displayed");
        softAssert.assertEquals(errorMessage.getText(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
        softAssert.assertAll();
    }

    @Test
    public void registrationInvalidEmail() {
        navigateToRegistration("12345");

        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password1"));
        WebElement confirmPasswordInput = driver.findElement(By.name("password2"));
        WebElement registerButton = driver.findElement(By.cssSelector("[value=\"Register\"]"));

        firstNameInput.sendKeys("a");
        lastNameInput.sendKeys("b");
        emailInput.sendKeys("test@test");
        passwordInput.sendKeys("123456");
        confirmPasswordInput.sendKeys("123456");
        registerButton.click();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(),
                "First name field should be displayed");
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        softAssert.assertTrue(errorMessage.isDisplayed(),
                "Error message should be displayed");
        softAssert.assertEquals(errorMessage.getText(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
        softAssert.assertAll();


    }
}
