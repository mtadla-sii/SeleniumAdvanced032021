package pages.product;

import model.Order;
import model.OrderLine;
import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.math.BigDecimal;

public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1[itemprop='name']")
    private WebElement nameLabel;

    @FindBy(css = "span[itemprop='price']")
    private WebElement priceLabel;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".add-to-cart")
    private WebElement addProductBtn;

    public String getProductName(){
        return nameLabel.getText();
    }

    public BigDecimal getProductPrice(){
        return new BigDecimal(priceLabel.getText().replace("$",""));
    }

    public void setQuantity(String quantity){
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    public int getProductQuantity(){
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }

    public void addToBasket(Order order){
        order.addNewOrderLine(toOrderLine());
        addProductBtn.click();
        new WebDriverWait(getDriver(), 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
    }

    public OrderLine toOrderLine(){
        return new OrderLine(new Product(getProductPrice(), getProductName()),
                getProductQuantity());
    }
}
