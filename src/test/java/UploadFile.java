import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UploadFile extends BaseTest {

    @BeforeMethod
    public void navigate() {
        driver.get("http://the-internet.herokuapp.com/upload");
    }

    @Test
    public void FileUploadTest() throws InterruptedException {
        WebElement fileUploadInput = driver.findElement(By.id("file-upload"));
        fileUploadInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/download.jpg");
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        Thread.sleep(5000);
    }
}