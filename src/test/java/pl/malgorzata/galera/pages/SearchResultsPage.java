package pl.malgorzata.galera.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By searchHeadline = By.className("header-headline");
    private final By firstProductLink = By.xpath(
            "//a[contains(@class, 'product__image') or contains(@class, 'fx-link')]");
    private final By productPriceLocator = By.cssSelector(
            ".fx-typography-price-primary.fx-price-group__primary.product__price-primary");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getSearchHeadlineText() {
        WebElement headline = wait.until(ExpectedConditions.visibilityOfElementLocated(searchHeadline));
        return headline.getText();
    }

    public String getProductPrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator));
        return priceElement.getText();
    }

    public ProductDetailsPage clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProductLink)).click();
        return new ProductDetailsPage(driver);
    }

}
