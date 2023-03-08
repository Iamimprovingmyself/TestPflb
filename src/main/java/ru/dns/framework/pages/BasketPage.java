package ru.dns.framework.pages;

import io.qameta.allure.Allure;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.dns.framework.model.Product;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class BasketPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'cart-items__product-price')]")
    private List<WebElement> productNameElements;

    @FindBy(xpath = "//span[contains(text(),'Добавить компанию')]")
    private WebElement alert;

    @FindBy(xpath = "//a[contains(@class, 'buttons__link ui-link cart-link-counter')]/span[1]")
    private WebElement countProductsBasketElement;

    @FindBy(xpath = "//div[contains(@class, 'summary-header__total-items')]")
    private WebElement countProductsElement;

    @FindBy(xpath = "//div[contains(@class, 'mass-selection__delete-btn')]")
    private WebElement deleteAllProductsElement;

    @FindBy(xpath = "//div[contains(text(), 'Корзина пуста')]")
    private WebElement basketIsEmpty;

    @FindBy(xpath = "//div[contains(text(), 'Корзина')]")
    private WebElement checkOpen;

    @FindBy(xpath = "//*[contains(text(), 'Удаление товаров')]/../div/div/button/span/span")
    private WebElement subWindowDeleteButton;

    @FindBy(xpath = "//*[contains(text(), 'Корзина пуста')]")
    private List<WebElement> basketIsEmptyElement;


    public BasketPage closeAlert() {
        elementToBeVisible(alert);
        elementToBeClickable(alert.findElements(By.xpath("./../../../../..//button")).get(1)).click();
        return this;
    }

    public BasketPage checkOpenPage() {
        elementToBeVisible(checkOpen);
        return this;
    }

    public BasketPage checkProductsInBasket() {
        int size = Product.listProducts.size();
        explicitWait(2500);
        Assert.assertEquals("Количество товаров не соответствует", Product.listProducts.size(), productNameElements.size());
        List<String> actualProductsName = new ArrayList<>();
        for (WebElement element : productNameElements) {
            actualProductsName.add(element.getText());
        }
        assertTrue("Не все товары корректно были добавлены в корзину", isContainsAllProducts(Product.listProducts, actualProductsName));
//        StringBuilder textCountProducts = new StringBuilder();
//        textCountProducts.append(countProductsBasketElement.getText().trim())
//                .append(" - ")
//                .append(countProductsElement.getText().trim().split(" •")[0]);
//            assertEquals("Текст 'Ваша корзина - " + size + " товаров' отображается некорректно",
//                    "Ваша корзина - " + size + " товаров",
//                    textCountProducts.toString());
        System.out.println(Product.listProducts.get(0).getName());
        System.out.println(Product.listProducts.get(0).getPrice());
        System.out.println(Product.listProducts.get(1).getName());
        System.out.println(Product.listProducts.get(1).getPrice());
        return this;
    }


    public BasketPage addTextFile() {
        Product product = Product.listProducts.stream()
                .max(Product::compareTo)
                .get();
        try (FileWriter out = new FileWriter("src/main/resources/allProducts.txt", false)) {
            out.write("Продукт с максимальной ценой: \n " + product.getName() + ", его цена: " + product.getPrice() + " ₽\n\n");
            out.write("Список товаров: \n ");
            int i = 1;
            for (Product prod : Product.listProducts) {
                out.write("" + i + prod.toString());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Allure.addAttachment("Файл со списком товаров:", "text/plain", new ByteArrayInputStream(getBytes()), "text");
        return this;
    }

    public BasketPage deleteAllProducts() {
        elementToBeClickable(deleteAllProductsElement).click();
        explicitWait(2000);
        if(!basketIsEmpty.isDisplayed()) {
            Assert.fail("Корзина не очищена");
        }
        return this;
    }

    public byte[] getBytes() {
        try {
            return Files.readAllBytes(Paths.get("target/allProducts.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
