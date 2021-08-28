import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AutomationPracticeTests {
    WebDriver driver;
    String URL = "http://automationpractice.com/index.php";

    @BeforeTest
    public void init(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(enabled = false)
    public void sendMessageThroughContactUsForm(){
        driver.get(URL);

        driver.findElement(By.cssSelector("[title=\"Contact Us\"]")).click();

        Select subjectHeadingSel = new Select(driver.findElement(By.cssSelector("#id_contact")));
        subjectHeadingSel.selectByVisibleText("Customer service");

        driver.findElement(By.cssSelector("[data-validate=\"isEmail\"]")).sendKeys("email@email1234.email");

        driver.findElement(By.cssSelector("textarea[name=\"message\"]")).sendKeys("Poruka");

        driver.findElement(By.cssSelector("[name=\"submitMessage\"]")).click();

        WebElement alertBox = driver.findElement(By.cssSelector(".alert.alert-success"));

        Assert.assertTrue(alertBox.isDisplayed());
        Assert.assertEquals(alertBox.getCssValue("background-color"),"rgba(85, 198, 94, 1)");
        Assert.assertEquals(alertBox.getText(), "Your message has been successfully sent to our team.");
    }

    @Test(enabled = false)
    public void sendMessageWithUpload(){
        driver.get(URL);

        driver.findElement(By.cssSelector("[title=\"Contact Us\"]")).click();

        Select subjectHeadingSel = new Select(driver.findElement(By.cssSelector("#id_contact")));
        subjectHeadingSel.selectByVisibleText("Customer service");

        driver.findElement(By.cssSelector("[data-validate=\"isEmail\"]")).sendKeys("email@email1234.email");

        driver.findElement(By.cssSelector("#id_order")).sendKeys("123123");

        driver.findElement(By.cssSelector("[name='fileUpload']")).sendKeys("C:\\Users\\aleks\\OneDrive\\Desktop\\Test.txt");

        driver.findElement(By.cssSelector("textarea[name=\"message\"]")).sendKeys("Poruka");

        driver.findElement(By.cssSelector("[name=\"submitMessage\"]")).click();

        WebElement alertBox = driver.findElement(By.cssSelector(".alert.alert-success"));

        Assert.assertTrue(alertBox.isDisplayed());
        Assert.assertEquals(alertBox.getCssValue("background-color"),"rgba(85, 198, 94, 1)");
        Assert.assertEquals(alertBox.getText(), "Your message has been successfully sent to our team.");
    }

    @Test(enabled = false)
    public void domaci(){
        driver.get(URL);

        driver.findElement(By.cssSelector("[title=\"Contact Us\"]")).click();

        driver.findElement(By.cssSelector("[name=\"submitMessage\"]")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(".alert.alert-danger li")).getText(),"Invalid email address.");
        Assert.assertEquals(driver.findElement(By.cssSelector(".alert.alert-danger p")).getText(),"There is 1 error");
    }

    @Test
    public void register(){
        String email = randCharacters(10)+"@mail.com";
        System.out.println(email);

        driver.get(URL);

        driver.findElement(By.xpath("//a[contains(text(),\"Sign in\")]")).click();

        driver.findElement(By.cssSelector("#email_create")).sendKeys(email);

        driver.findElement(By.xpath("//button[@id=\"SubmitCreate\"]")).click();

        //Registracija
        driver.findElement(By.id("id_gender1")).click();

        driver.findElement(By.cssSelector("#customer_firstname")).sendKeys("FirstName");

        driver.findElement(By.cssSelector("#customer_lastname")).sendKeys("LastName");

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.id("passwd")).sendKeys("12345");

        Select months = new Select(driver.findElement(By.id("months")));
        months.selectByValue("1");

        Select days = new Select(driver.findElement(By.id("days")));
        days.selectByValue("24");

        Select years = new Select(driver.findElement(By.id("years")));
        years.selectByValue("2000");

        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys("FirstName");

        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("LastName");

        driver.findElement(By.id("address1")).sendKeys("Address 1");

        driver.findElement(By.id("city")).sendKeys("City");

        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByVisibleText("Alaska");

        driver.findElement(By.id("postcode")).sendKeys("12312");

        driver.findElement(By.id("phone_mobile")).sendKeys("123456456");

        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("Address1");

        driver.findElement(By.id("submitAccount")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@class=\"info-account\"]")).getText(), "Welcome to your account. Here you can manage all of your personal information and orders.");
    }

    public String randCharacters(int num){
        String[] chars = {"q","w","e","r","t","y","u","i","o","p","a"};
        String result = "";

        Random r = new Random();

        for(int i = 0; i < num; i++){
            result += chars[r.nextInt(10)];
        };

        return result;
    }
}