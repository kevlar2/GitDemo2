package rocksolicitors;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TabletTest extends Main{

    @Test
    public void mobileWebLogin(){
        System.out.println("mobileWebLogin");
    }

    @Test
    public void checkHomePage(){
        System.out.println("checkHomePage");
    }

    @Test
    public void tearDown(){
        System.out.println("tearDown\n");
    }

    @AfterTest
    public void CleaningUpTheEnvironment() throws InterruptedException {
        System.out.println("Cleaning up the environment after every test has been executed. This should always execute after all test.");
        Thread.sleep(3500);
        driver.quit();
    }

}
