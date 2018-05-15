package Pages;

import Steps.BaseSteps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Описание базовой страницы
 * Методы, общие для всех страниц
 */
public class BasePage {
    public BasePage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    /**
     * Метод заполняет поле указанными данными
     *
     * @param field Поле для заполнения
     * @param value Данные для ввода
     */
    public void fillField(WebElement field, String value) {
        scrollToElement(field);
        field.clear();
        field.sendKeys(value);
    }

    /**
     * Метод выбирает из коллекции искомый элемент
     *
     * @param itemName Текст для поиска элемента
     * @param collection Коллекция элементов
     */
    public void selectCollectionItem(String itemName, List<WebElement> collection) {
        for (WebElement item : collection) {
            if (item.getText().equalsIgnoreCase(itemName)) {
                scrollToElement(item);
                item.click();
                return;
            }
        }
        Assert.fail("Не найден элемент коллекции - " + itemName);
    }

    /**
     * Метод ожидает отображения элемента на странице заданное время
     *
     * @param element Искомый элемент
     * @param time Время ожидания
     */
    public void waitVisibility(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(BaseSteps.getDriver(), time);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Метод ожидает отображения элемента на странице стандартное (для данного теста) время
     *
     * @param element Искомый элемент
     */
    public void waitVisibility(WebElement element) {
        waitVisibility(element, 10);
    }

    /**
     * Метод проверяет, находится ли искомый элемент на странице и отображен ли он
     *
     * @param by Параметр поиска элемента
     * @return Отображен элемент или нет
     */
    public boolean isElementPresent(By by) {
        WebDriver driver = BaseSteps.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    /**
     * Метод проверяет, находится ли искомый элемент на странице и отображен ли он
     *
     * @param element Искомый элемент
     *
     * @return Отображен элемент или нет
     */
    public boolean isElementPresent(WebElement element) {
        WebDriver driver = BaseSteps.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    /**
     * Метод скроллит страницу до заданного элемента
     *
     * @param element Элемент, до которого требуется прокрутить страницу
     */
    public void scrollToElement(WebElement element) {
        WebDriver driver = BaseSteps.getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }
}
