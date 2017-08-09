package com.endava.test;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class YahooTest {

    public static WebDriver webDriver;

    @BeforeClass
    public static void before() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/rciuciuc/AppData/Roaming/Skype/My Skype Received Files/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @Before
    public void beforeTest() {
        webDriver.get("https://ro.yahoo.com");
    }

    @Test

    public void sendEmail() {
        //sign in button
        WebElement auth = webDriver.findElement(By.id("uh-signin"));
        auth.click();

        //insert username
        WebElement username = webDriver.findElement(By.id("login-username"));
        username.sendKeys("ciuciuc.ramona04@yahoo.com");

        //uncheck the "stay connected" checkbox
        WebElement authCheckBox = webDriver.findElement(By.xpath(".//*[@id='login-username-form']/p[2]/span[1]"));
        authCheckBox.click();

        //click next button
        WebElement nextButton = webDriver.findElement(By.id("login-signin"));
        nextButton.click();

        //insert password
        WebElement password = webDriver.findElement(By.id("login-passwd"));
        password.sendKeys("123@DMIN");

        //click login button
        WebElement authenticationButton = webDriver.findElement(By.id("login-signin"));
        authenticationButton.click();

        //click mail button
        WebElement mailButton = webDriver.findElement(By.id("uh-mail-link"));
        mailButton.click();

        //click compose button
        WebElement composeButton = webDriver.findElement(By.xpath(".//*[@id='Compose']/button"));
        composeButton.click();

        //insert receiver
        WebElement toInput = webDriver.findElement(By.id("to-field"));
        toInput.sendKeys("ciuciuc.ramona04@yahoo.com");

        //insert subject
        WebElement subject = webDriver.findElement(By.id("subject-field"));
        subject.sendKeys("test");

        //insert content
        WebElement content = webDriver.findElement(By.id("rtetext"));
        content.sendKeys("content test");

        //click send button
        WebElement sendEmailButton = webDriver.findElement(By.xpath(".//*[@id='shellinner']/div/div//tr/td/div[2]/span[1]"));
        sendEmailButton.click();

        //verify the name of the email sender
        WebElement openMail = webDriver.findElement(By.xpath(".//div[contains(@class, 'name first')]"));
        String checkReceivedMail = openMail.getText();
        Assert.assertEquals("Ramona Ciuciuc", checkReceivedMail);

        //verify the subject of the email
        WebElement receivedEmailSubject = webDriver.findElement(By.xpath(".//div[contains(@class, 'subj')]/span[1]"));
        String checkReceivedEmailSubject = receivedEmailSubject.getText();
        Assert.assertEquals("test", checkReceivedEmailSubject);

        //selected the 3rd component in the list-view-items-page which is the second received mail because the first mail was not visible
        WebElement receivedMail = webDriver.findElement(By.xpath(".//div[contains(@class,'list-view-items-page')]/div[3]"));
        WebDriverWait wait = new WebDriverWait(webDriver, 30000);
        wait.until(ExpectedConditions.visibilityOf(receivedMail));
        receivedMail.click();

        //open received mail
        WebElement receivedEmail = webDriver.findElement(By.xpath(".//div[contains(@class,'thread-item-list')]/div[1]/div[1]"));
        receivedEmail.click();

        /*WebElement contentContent = webDriver.findElement(By.xpath(".//div[contains(@class,'email-wrapped')]/div/div/div/div"));
        wait.until(ExpectedConditions.visibilityOf(contentContent));
        String contentText = content.getText();
        Assert.assertEquals("content test", contentText);*/

        //click on sent mails
        WebElement sentMail = webDriver.findElement(By.xpath(".//*[@id='Sent']/a"));
        sentMail.click();

        //verify the name of the person which sent the last mail
        WebElement senderName = webDriver.findElement(By.xpath("//div[contains(@class,'name-list')]/div[1]"));
        wait.until(ExpectedConditions.visibilityOf(senderName));
        String checkSenderName = senderName.getText();
        Assert.assertEquals("ciuciuc.ramona04@yahoo.com", checkSenderName);

        //logout
        WebElement logout = webDriver.findElement(By.xpath(".//td[contains(@class,\'W(1%)\')]/ul/li[2]/a"));
        logout.click();

        WebElement logoutFin = webDriver.findElement(By.id("yucs-signout"));
        logoutFin.click();
    }
    public void closeWindow(){
        webDriver.close();
    }
}
