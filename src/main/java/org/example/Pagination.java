package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Pagination extends BasePage{

    public Pagination(WebDriver driver) {
        super(driver);
    }

    private final String URL = "https://demo.seleniumeasy.com/table-pagination-demo.html";
    private final By TABLE_ROW = By.xpath("//tbody/tr[@style='display: table-row;']");
    private final By BUTTON_NEXT = By.xpath("//*[@class='next_link']");

    public void navigate() {
        driver.navigate().to(URL);
    }

    public void clickNextArrow() {
        driver.findElement(BUTTON_NEXT).click();
    }

    public boolean isNextButtonClickable() {
        return (driver.findElement(BUTTON_NEXT).getAttribute("style")).equals("display: none;");
    }

    public void getNumberFromTable(List<Integer> result) {
        List<WebElement> tableRows = driver.findElements(TABLE_ROW);
        for (WebElement row : tableRows) {
            WebElement td = row.findElement(By.xpath("./td[1]"));
            Integer value = Integer.parseInt(td.getText());
            result.add(value);
        }
    }

}
