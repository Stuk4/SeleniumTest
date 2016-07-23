import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Java-VS on 22/07/2016.
 */
public class LoginPage {

    private final WebDriver driver;
    private final String baseUrl;

    /**
     * ctor
     * @param driver
     * @param baseUrl
     */
    public LoginPage(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    public void open(){
        driver.get(baseUrl);
    }

    public void sendUserName(String username){
        driver.findElement(By.id("username")).sendKeys(username);
    }

    public void sendPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void submitLogin(){
        driver.findElement(By.cssSelector(".radius")).click();
    }

    public String getMessage(){
        return driver.findElement(By.id("flash")).getText();
    }



}
