package rocksolicitors;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class FailedTestScreenshots extends Main {

    @Test
    public static void takesScreenshotOfFailedTest(String failedTestName)  {
        // Using this to create a time reference to add to the screenshot name
        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeInString = dateTime.toString();
        String[] newDateTime = dateTimeInString.split(":");
        String updatedDateTime = String.join("", newDateTime);

        // Path to store screenshot
        String pathName = System.getProperty("user.dir") + "\\Firstmavenproject\\Screenshots\\";

        // Screenshot object
        TakesScreenshot screenShot = (TakesScreenshot) driver;

        // Stores screenshot as file in the screenshot folder
        try {
            FileHandler.copy(screenShot.getScreenshotAs(OutputType.FILE),new File(pathName + failedTestName + updatedDateTime + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
