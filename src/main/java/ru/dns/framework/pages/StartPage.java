package ru.dns.framework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;


public class StartPage extends BasePage {
    @FindBy(xpath = "//input[contains(@enterkeyhint, 'search')]")
    WebElement searchElement;

    @FindBy(xpath = "//div[contains(text(), 'Личный кабинет')]")
    WebElement  check;


    public SearchPage selectProductSearch(String nameProduct){
        fillInputField(searchElement, nameProduct);
        assertEquals("Наименование продукта " + nameProduct + " в графе поиск заполнено некорректно", nameProduct, searchElement.getAttribute("value"));
        searchElement.sendKeys(Keys.ENTER);
        return application.getPage(SearchPage.class);
    }
    public StartPage checkOpenPage() {
        wait.until(ExpectedConditions.visibilityOf(check));
        return this;
    }
}
