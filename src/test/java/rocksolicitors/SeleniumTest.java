package rocksolicitors;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SeleniumTest extends Main{

    @BeforeTest
    public void PreparingEnvironment() throws IOException {
        System.out.println("Preparing Environment for testing. This should always execute before any test.\n");
        driver = new ChromeDriver();
        // Creates the property object
        Properties prop = new Properties();
        String os = System.getProperty("os.name");
        FileInputStream fis;
        if(os.contains("Mac")){
            // This allows for you to read the file
            fis = new FileInputStream(System.getProperty("user.dir") + "//data.properties");
        } else {
            // This allows for you to read the file
            fis = new FileInputStream(System.getProperty("user.dir") + "\\data.properties");
        }

        // Now lets get the content of the file and open browser
        prop.load(fis);
        String url = prop.getProperty("URL");
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void BrowserAutomation() throws InterruptedException {
        System.out.println("BrowserAutomation");
        System.out.println("Validating refer to a friend button");

        // 01. Validate refer to a friend button
        WebElement referToAFriend = driver.findElement(By.xpath("//div[@class='recommend-us']"));
        referToAFriend.click();

        Thread.sleep(5000);

        // Validate close [X] button
        WebElement closeButton = driver.findElement(By.cssSelector(
                "div[class='jconfirm-box jconfirm-hilight-shake jconfirm-type-default jconfirm-type-animated'] div[class='jconfirm-closeIcon']"));
        closeButton.click();

        driver.navigate().refresh();

        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);

        // Validate entering details then closing form using the close [X] button

        // referrer name
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).sendKeys("James");
        driver.findElement(By.cssSelector("input[id='referral-name-last']")).sendKeys("Roghan");
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");

        // person referring
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        driver.findElement(By.cssSelector(
                "div[class='jconfirm-box jconfirm-hilight-shake jconfirm-type-default jconfirm-type-animated'] div[class='jconfirm-closeIcon']"))
                .click();

        driver.navigate().refresh();

        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);
        // Validate entering all the required details then closing form using [X] button

        // referrer name
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).sendKeys("James");
        driver.findElement(By.cssSelector("input[id='referral-name-last']")).sendKeys("Roghan");
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");

        // person referring
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        driver.findElement(By.cssSelector(
                "div[class='jconfirm-box jconfirm-hilight-shake jconfirm-type-default jconfirm-type-animated'] div[class='jconfirm-closeIcon']"))
                .click();

        // Validate entering details without closing form

        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);
        // referrer name
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).sendKeys("James");
        driver.findElement(By.cssSelector("input[id='referral-name-last']")).sendKeys("Roghan");
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");

        // person referring
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        // get and validate message preview

        String messagePreview = driver.findElement(By.xpath("//div[@id='email-content']")).getText();
        String referedPerson = "Dear James Roghan";
        String personRefering = "Justin Brown";
        String webpageURL = "ttps://rocksolicitors.leapweb.co.uk";
        Assert.assertTrue(messagePreview.contains(referedPerson)); //Needs fixing as it is a bug
        Assert.assertTrue(messagePreview.contains(personRefering));
        Assert.assertTrue(messagePreview.contains(webpageURL));

        boolean isSendButtonEnabled = driver.findElement(By.xpath("//button[contains(@class,'export_confirmation')]"))
                .isEnabled();
        Assert.assertTrue(isSendButtonEnabled);
        boolean isCancelButtonEnabled = driver.findElement(By.xpath("//button[contains(@class,'edit_cancel')]"))
                .isEnabled();
        Assert.assertTrue(isCancelButtonEnabled);

        // Click on cancel button
        driver.findElement(By.cssSelector("button[class='btn edit_cancel']")).click();

        // Validate refer to a friend error messages

        // A. Enter only names and click on send
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);
        // referrer name
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).sendKeys("James");
        driver.findElement(By.cssSelector("input[id='referral-name-last']")).sendKeys("Roghan");

        // person referring
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");

        // Click send button
        driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']"))
                .click();

        // validate error warning
        WebElement noEmailAddress = driver.findElement(By.cssSelector("div p[id='referral-warning']"));
        String expectedNoEmailaddressError = "Please enter a valid email address for your friend.";
        Assert.assertEquals(noEmailAddress.getText(), expectedNoEmailaddressError);

        // Click on cancel button
        driver.findElement(By.cssSelector("button[class='btn edit_cancel']")).click();

        // B. Enter only email and click on the send button

        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);
        // referrer email
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");

        // person referring email
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        // Click send button
        driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']"))
                .click();

        // validate error warning
        WebElement noNameError = driver.findElement(By.cssSelector("div p[id='referral-warning']"));
        String expectedNoNameError = "Please provide your friends first name.";
        Assert.assertEquals(noNameError.getText(), expectedNoNameError);

        // Click on cancel button
        driver.findElement(By.cssSelector("button[class='btn edit_cancel']")).click();

        Thread.sleep(3000);

        // C. Complete Friends details and only complete person referring name

        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);
        // referrer name and email address
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).sendKeys("James");
        driver.findElement(By.cssSelector("input[id='referral-name-last']")).sendKeys("Roghan");
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");

        // person referring name and last name only
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");

        // Click send button
        driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']"))
                .click();

        WebElement noPersonReferringEmail = driver.findElement(By.cssSelector("div p[id='referral-warning']"));
        String expectedNoPersonReferringEmailError = "Please enter a valid email address for yourself.";
        Assert.assertEquals(noPersonReferringEmail.getText(), expectedNoPersonReferringEmailError);

        // Click on cancel button
        driver.findElement(By.cssSelector("button[class='btn edit_cancel']")).click();


        //D. Validate name errors

        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);

        // referrer first name and email address only
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).sendKeys("James");
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");

        // person referring
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        // Click send button
        driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']"))
                .click();

        WebElement youFriendlastnemerror = driver.findElement(By.cssSelector("div p[id='referral-warning']"));
        String expectedyouFriendlastnemerror = "Please provide your friends last name.";
        Assert.assertEquals(youFriendlastnemerror.getText(), expectedyouFriendlastnemerror);

        // referrer last name and email address only
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).clear();
        driver.findElement(By.cssSelector("input[id='referral-name-last']")).sendKeys("Roghan");
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");

        // person referring
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        // Click send button
        driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']"))
                .click();


        // Validate error message
        WebElement yourFriendFirstNameerror = driver.findElement(By.cssSelector("div p[id='referral-warning']"));
        String expectedyourFriendFirstNameerror = "Please provide your friends first name.";
        Assert.assertEquals(yourFriendFirstNameerror.getText(), expectedyourFriendFirstNameerror);

        // Click on cancel button
        driver.findElement(By.cssSelector("button[class='btn edit_cancel']")).click();

        // Prepare screen
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='recommend-us']")).click();

        Thread.sleep(3000);
        // Your friend name field
        driver.findElement(By.cssSelector("input[id='referral-name-first']")).sendKeys("James");
        driver.findElement(By.cssSelector("input[id='referral-name-last']")).sendKeys("Roghan");
        driver.findElement(By.xpath("//input[@id='referral-email']")).sendKeys("James.Roghan@tflrail.com");


        // Your name field
        driver.findElement(By.cssSelector("input[id='your-name-first']")).sendKeys("Justin");
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        // Click send button
        driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']"))
                .click();

        // Validate error message
        WebElement yourLastNameError = driver.findElement(By.cssSelector("div p[id='referral-warning']"));
        String expectedYourLastNameError = "Your last name is not valid.";
        Assert.assertEquals(yourLastNameError.getText(), expectedYourLastNameError);

        Thread.sleep(3000);
        // Your name field
        driver.findElement(By.cssSelector("input[id='your-name-first']")).clear();
        driver.findElement(By.cssSelector("input[id='your-name-last']")).sendKeys("Brown");
        driver.findElement(By.xpath("//input[@id='your-email']")).clear();
        driver.findElement(By.xpath("//input[@id='your-email']")).sendKeys("Justin.Brown@tflrail.com");

        // Click send button
        driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']"))
                .click();

        Thread.sleep(3000);
        // Validate error message
        WebElement yourFirstNameError = driver.findElement(By.cssSelector("div p[id='referral-warning']"));
        String expectedYourFirstNameError = "Your first name is not valid.";
        Assert.assertEquals(yourFirstNameError.getText(), expectedYourFirstNameError);


        // Click on cancel button
        driver.findElement(By.cssSelector("button[class='btn edit_cancel']")).click();

        Thread.sleep(3000);

        driver.quit();
    }

    @Test
    public void elements(){
        System.out.println("elements");
    }

}
