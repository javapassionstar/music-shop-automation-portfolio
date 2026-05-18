package pl.malgorzata.galera.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productPriceLocator = By.className("price-wrapper");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductPrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator));
        return priceElement.getText();
    }
}
