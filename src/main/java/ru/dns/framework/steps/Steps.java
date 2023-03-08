package ru.dns.framework.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.dns.framework.managers.PageManager;
import ru.dns.framework.pages.BasketPage;
import ru.dns.framework.pages.SearchPage;
import ru.dns.framework.pages.StartPage;


public class Steps {

    private PageManager app = PageManager.getINSTANCE();

    @Когда("^Выполняем поиск продукта '(.*)'$")
    public void selectProductSearch(String nameProduct) {
        app.getPage(StartPage.class).checkOpenPage().selectProductSearch(nameProduct);}

    @Когда("^Ограничение цены до '(\\d+)'$")
    public void clickProductSearch(Integer upLimit) {
        app.getPage(SearchPage.class).clickProductSearch(upLimit);
    }

    @Когда("^Заполняем поля значениями$")
    public void fillCheckbox(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getPage(SearchPage.class).fillCheckbox(raw.get(0), raw.get(1));
                });
    }

    @Когда("^Добавляем в корзину первые '(\\d+)' товара$")
    public void fillBasket(Integer countProduct) {app.getPage(SearchPage.class).fillBasket(countProduct);}

    /*@Когда("^Закрываем баннер")
    public void closeAlert() {
        app.getPage(BasketPage.class).closeAlert();
    }*/

    @Когда("^Проверяем, что страница поиска пустая$")
    public void checkResultPageIsEmpty() {
        app.getPage(SearchPage.class).checkElementsIsEmpty();
    }

    @Когда("^Добавляем в корзину все нечетные товары$")
    public void fillBasketWithHP() {
        app.getPage(SearchPage.class).fillBasketWithHP();
    }

    @Тогда("^Проверяем наличие товаров в корзине$")
    public void checkProductsInBasket() {
        app.getPage(BasketPage.class).checkProductsInBasket();}

    @Тогда("^Добавляем в отчет Аллюра текстовый файл$")
    public void addTextFile() { app.getPage(BasketPage.class).addTextFile();}

    @Тогда("^Удаляем все товары из корзины$")
    public void deleteAllProducts() { app.getPage(BasketPage.class).deleteAllProducts();}
}
