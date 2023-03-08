package ru.dns.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.dns.framework.managers.DriverManager;
import ru.dns.framework.model.Product;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static ru.dns.framework.model.Product.listProducts;


public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[contains(@data-id, 'price')]/div/div/div[2]/input")
    private WebElement searchUpLimitElement;

    @FindBy(xpath = "//span[contains(text(),'Есть обзор')]")
    private WebElement hvFeedback;

    @FindBy(xpath = "//div[contains(@data-id, 'stock')]/label[1]/input")
    private WebElement inStock;

    @FindBy(xpath = "//div[contains(@data-id, 'action')]/div/div/div/label[1]/span[2]")
    private WebElement profitKits;

    @FindBy(xpath = "//div[contains(@data-id, 'action')]/div/div/div/label[3]/span[2]")
    private WebElement discountsAndOffers;

    @FindBy(xpath = "//div[contains(@data-id, 'product')]//div[4]")
    private List<WebElement> allSearchElements;

    @FindBy(xpath = "//header/div/div/a/span[text() = 'Корзина']/../span[position() = 1]")
    private WebElement basketCountElement;

    @FindBy(xpath = "//a[contains(@data-commerce-target, 'CART')]")
    private WebElement basketElement;

    @FindBy(xpath = "//div[contains(text(), 'Бренды')]/..//span[@class='show']")
    private WebElement btn;

    @FindBy(xpath = "//div[contains(text(),'результатов не найдено')]")
    private WebElement noResults;

    @FindBy(xpath = "//div[contains(@data-label, 'Показать')]")
    private WebElement show;

    @FindBy(xpath = "//div[contains(@data-id, 'product')]//div[4]//../../a/span")
    private List<WebElement> spans;

    public SearchPage clickProductSearch(Integer upLimit) {
        scrollToElementJs(hvFeedback);
        while (!searchUpLimitElement.getAttribute("value").isEmpty()) {
            searchUpLimitElement.sendKeys(Keys.BACK_SPACE);
        }
        fillInputField(searchUpLimitElement, upLimit);
        explicitWait(500);
        action.sendKeys(Keys.ENTER);
        assertEquals("Ограничение цены " + upLimit + " в графе 'до' заполнено некорректно", "" + upLimit, searchUpLimitElement.getAttribute("value"));
        return application.getPage(SearchPage.class);
    }

    public SearchPage fillCheckbox(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "В наличии":
                element = inStock;
                break;
            case "Скидки и предложения":
                element = discountsAndOffers;
                break;
            case "Выгодные комплекты":
                element = profitKits;
                break;
            default:
                fail("Поле с наименованием '" + nameField + "' отсутствует на странице " + "'Поиска'");

        }
        Actions act = new Actions(DriverManager.getINSTANCE().getDriver());
        explicitWait(2000);
        act.moveToElement(element).click().build().perform();
        if (Boolean.parseBoolean(value) && Boolean.parseBoolean(element.getAttribute("checked"))) {
            element.findElement(By.xpath("./..")).click();
        }
        act.moveToElement(show).click().build().perform();
        return this;
    }


    public BasketPage fillBasket(Integer countProduct) {
        for (int i = 1, j = 0; j < countProduct; i++) {
            int a = i;
            explicitWait(1000);
            List<WebElement> elm = allSearchElements.get(i).findElements(By.xpath("//button[text() = 'Купить']"));
            explicitWait(1000);
            WebElement temp = allSearchElements.get(i).findElement(By.xpath("//button[text()= 'Купить']"));
            action.moveToElement(allSearchElements.get(i).findElement(By.xpath("//button[text() = 'Купить']")));
            allSearchElements.get(i).findElement(By.xpath("//button[text() = 'Купить']")).click();
            System.out.println(temp.findElement(By.xpath("//../div/div[contains(text(), '₽')]")));
            add(temp, a);
            j++;

        }

        elementToBeClickable(basketElement).click();
        return application.getPage(BasketPage.class);
    }

    private void add(WebElement product, int j) {
        String title = spans.get(j).getText();
        System.out.println(title);
        List<WebElement> spans = product.findElements(By.xpath("//../div/div[contains(text(), '₽')][1]"));
        int value = 0;
        explicitWait(1000);
        value = utilParsInteger(spans.get(j).getText());
        System.out.println("ЭТО ЦЕНА " + value);
        listProducts.add(new Product(title, value));
    }

    public BasketPage fillBasketWithHP() {
        int arraySize = 0;
        int j = 0;
        do {
            while (arraySize * 2 < allSearchElements.size()) {
                List<WebElement> inBasket = allSearchElements.get(arraySize * 2).findElements(By.xpath(".//span[text()='В корзину']"));
                if (inBasket.size() == 2) {
                    (inBasket.get(1)).click();
                    j++;
                    wait.until(ExpectedConditions.textToBePresentInElement(basketCountElement, "" + j));
                } else if (inBasket.size() == 1) {
                    if (!inBasket.get(0).findElement(By.xpath("./../../../../..//b")).getText().contains("час")) {
                        (inBasket.get(0)).click();
                        j++;
                        wait.until(ExpectedConditions.textToBePresentInElement(basketCountElement, "" + j));
                    } else if (!inBasket.get(0).findElement(By.xpath("./../../../../..//b")).getText().contains("час")) {
                        continue;

                    }
                }
                arraySize++;
            }
            arraySize = 0;
        } while (nextPage());
        elementToBeClickable(basketElement).click();
        return application.getPage(BasketPage.class);
    }

    private boolean nextPage() {
        try {
            driverManager.getDriver().findElement(By.xpath("//div[contains(text(), 'Дальше')]")).click();
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void viewAllBrandsElements() {
        try {
            DriverManager.getINSTANCE().getDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            btn.click();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
        }
    }

    public void checkElementsIsEmpty() {
        if (noResults.isDisplayed()) {

        }
    }

}
