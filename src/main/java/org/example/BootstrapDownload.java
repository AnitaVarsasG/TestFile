package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BootstrapDownload extends BasePage{

    public BootstrapDownload(WebDriver driver) {
        super(driver);
    }

    private final String URL = "https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html";
    private final By BUTTON_DOWNLOAD = By.id("cricle-btn");
    private final By PERCENT_TEXT = By.xpath("//*[@class='percenttext']");

    public void navigate() {
        driver.navigate().to(URL);
    }

    public void clickDownload() {
        driver.findElement(BUTTON_DOWNLOAD).click();
    }

    public String readPercent() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(PERCENT_TEXT, "100%"));
        return driver.findElement(PERCENT_TEXT).getText();
    }
}
