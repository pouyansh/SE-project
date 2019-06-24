package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class first {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "F:\\daneshgah\\Software engineering\\project\\phase3\\SE-project\\selenium_libraries\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();

        login(driver);

        add_data(driver);

        logout(driver);

        driver.quit();
    }

    private static void login(FirefoxDriver driver) throws InterruptedException {
        driver.get("http://localhost:8080/login");

        System.out.println("In login page ...");

        WebElement username = driver.findElementByName("username");
        WebElement password = driver.findElementByName("password");

        System.out.println("filling username and password ...");
        username.sendKeys("se");
        password.sendKeys("se");

        WebElement form = driver.findElementByTagName("form");
        System.out.println("submitting the form ...");
        form.submit();

        Thread.sleep(5000);
    }

    private static void add_data(FirefoxDriver driver) throws InterruptedException {
        System.out.println("In add data ...");
        WebElement se = driver.findElementByClassName("dropdown");
        WebElement settings = se.findElement(By.cssSelector("ul > li:nth-child(4)"));
        System.out.println("Click on the profile ...");
        se.click();
        Thread.sleep(1000);
        System.out.println("Click on the system settings ...");
        settings.click();
        Thread.sleep(2000);

        WebElement MaintainQueues = driver.findElement(By.linkText("Maintain Queues"));
        System.out.println("Click on the Maintain Queues link ...");
        MaintainQueues.click();
        Thread.sleep(2000);

        WebElement AddQueue = driver.findElement(By.className("addlink"));
        System.out.println("Click on add item ...");
        AddQueue.click();
        Thread.sleep(2000);

        WebElement title = driver.findElementByName("title");
        WebElement slug = driver.findElementByName("slug");

        int r = new Random().nextInt();
        System.out.println("Filling the title and slug ...");
        title.sendKeys("queue_" + r);
        slug.sendKeys("slug");
        Thread.sleep(1000);

        WebElement form = driver.findElementById("queue_form");
        System.out.println("Submitting the form ...");
        form.submit();
        Thread.sleep(2000);

        System.out.println("Redirecting to the submitting ticket form ...");
        driver.get("http://localhost:8080/tickets/submit/");
        WebElement select = driver.findElementById("id_queue");
        System.out.println("Filling the form ...");
        select.sendKeys("1");
        Thread.sleep(1000);

        r = new Random().nextInt();
        WebElement titleTicket = driver.findElementById("id_title");
        titleTicket.sendKeys("ticket_" + r);
        Thread.sleep(1000);

        WebElement description = driver.findElementById("id_body");
        description.sendKeys("ticket_" + r);
        Thread.sleep(1000);

        WebElement formTicket = driver.findElementByTagName("form");
        System.out.println("Submitting the form ...");
        formTicket.submit();
        Thread.sleep(3000);
    }

    private static void logout(FirefoxDriver driver) throws InterruptedException {
        WebElement se = driver.findElementByClassName("dropdown");
        WebElement settings = se.findElement(By.cssSelector("ul > li:nth-child(5)"));
        System.out.println("Click on the profile ...");
        se.click();
        Thread.sleep(1000);
        System.out.println("Click on the logout ...");
        settings.click();
        Thread.sleep(2000);
    }
}
