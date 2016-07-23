package edu.cibertec.webdriver;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by Java-VS on 22/07/2016.
 */
public class LoginTest {

    private static final Logger LOG =
            LoggerFactory.getLogger(LoginTest.class);


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

    //@Before
    public void open(){
        driver = new FirefoxDriver();
        LOG.info("Driver creado!");
    }


    @AfterClass //se ejecuta una vz que to do los test haan terminado
    public static void closeDriver(){
        driver.quit();
        LOG.info("Driver terminado!");
    }

    //@After
    public void close(){
        //driver.quit();
        driver.close();
        LOG.info("Driver terminado!");
    }

    @Test
    public void loginValido(){
        driver.get(baseUrl);
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector(".radius")).click();
        String message = driver.findElement(By.id("flash")).getText();
        LOG.info("Message :"+ message);
        boolean contains = message.contains("You logged into");
        assertTrue(contains);
    }

    @Test
    public void loginUserInvalido(){
        driver.get(baseUrl);
        driver.findElement(By.id("username")).sendKeys("txxomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector(".radius")).click();
        String message = driver.findElement(By.id("flash")).getText();
        LOG.info("Message :"+ message);
        boolean contains = message.contains("Your username is invalid!");
        assertTrue(contains);
    }


    @Test
    public void loginPasswordInvalido(){
        driver.get(baseUrl);
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.cssSelector(".radius")).click();
        String message = driver.findElement(By.id("flash")).getText();
        LOG.info("Message :"+ message);
        boolean contains = message.contains("Your password is invalid!");
        assertTrue(contains);
    }



}
