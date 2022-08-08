package rocksolicitors;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TabletTest extends Main{

    @Test
    public void mobileWebLogin(){
        System.out.println("mobileWebLogin\n");
        System.out.println("Mobile web test completed successfully \n");
        System.out.println("Git nigerian dude code \n");
    }

    @Test
    public void checkHomePage(){
        System.out.println("checkHomePage");
        System.out.println("check Home Page Test completed successfully\n");
    }

    @Test
    public void tearDown(){
        System.out.println("tearDown\n");
        System.out.println("Teardown completed successfully\n");
    }

    @AfterTest
    public void CleaningUpTheEnvironment() throws InterruptedException {
        System.out.println("Cleaning up the environment after every test has been executed. This should always execute after all test.");
        Thread.sleep(3500);
        driver.quit();
    }

}
