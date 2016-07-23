import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by Java-VS on 22/07/2016.
 */
public class LoginPageTest {

    private static final Logger LOG =
            LoggerFactory.getLogger(LoginPageTest.class);


    private static WebDriver driver;

    private String baseUrl = "https://the-internet.herokuapp.com/login";

    @BeforeClass
    public static void openDriver(){
        System.setProperty("webdriver.chrome.driver"
                , "C:\\JAAD-2016-I\\02_Programas\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        LOG.info("Driver creado!");
    }


    @AfterClass //se ejecuta una vz que to do los test haan terminado
    public static void closeDriver(){
        driver.quit();
        LOG.info("Driver terminado!");
    }


    @Test
    public void LoginValido() throws Exception {
        LoginPage loginPage = new LoginPage(driver, baseUrl);
        loginPage.open();
        loginPage.sendUserName("tomsmith");
        loginPage.sendPassword("SuperSecretPassword!");
        loginPage.submitLogin();
        String message = loginPage.getMessage();
        boolean contains = loginPage.getMessage().contains("You logged into a secure area!");
        assertTrue(contains);

    }

    @Test
    public void LoginUsernameInvalido() throws Exception {
        LoginPage loginPage = new LoginPage(driver, baseUrl);
        loginPage.open();
        loginPage.sendUserName("tomsmxith");
        loginPage.sendPassword("SuperSecretPassword!");
        loginPage.submitLogin();
        String message = loginPage.getMessage();
        boolean contains = loginPage.getMessage().contains("Your username is invalid!");
        assertTrue(contains);
    }
}