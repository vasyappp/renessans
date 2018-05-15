package Pages;

import Steps.BaseSteps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Описание калькулятора для вкладов
 */
public class CalculatorPage extends BasePage {
    private static final String calculatorXpath = ".//div[@class = 'calculator-block']"; // Путь к разделу калькулятора на странице

    @FindBy(xpath = calculatorXpath + "/div[@class = 'wrapper']/h2")
    public WebElement header; // Заголовок страницы

    @FindBy(xpath = calculatorXpath +
            "//div[@class = 'calculator__currency-content']/label//span[@class = 'calculator__currency-field-text']")
    public List<WebElement> currencies; // Список опций валют вклада

    @FindBy(xpath = calculatorXpath +
            "//label[contains(text(), 'Сумма вклада')]/following-sibling::div//input[@name = 'amount']")
    public WebElement amountInput; // Поле для ввода суммы вклада

    @FindBy(xpath = calculatorXpath +
            "//label[contains(text(), 'Ежемесячное пополнение')]/following-sibling::div//input")
    public WebElement monthlyAddInput; // Поле для ввода ежемесячного пополнения

    @FindBy(xpath = calculatorXpath +
            "//div[@class = 'calculator__check-row-field']/label")
    public List<WebElement> checkboxes; // Чекбоксы для параметров вклада


    /**
     * Метод выбирает заданный срок вклада
     *
     * @param period Срок вклада
     */
    public void selectPeriod(String period) {
        WebElement periodButton = BaseSteps.getDriver().findElement(By.xpath(calculatorXpath + "//select[@id = 'period']"));
        Select periodSelect = new Select(periodButton);

        periodSelect.selectByVisibleText(period);
    }

    /**
     * Метод получает название чекбокса
     * Возвращает пустую строку, если названия по указанному пути не существует.
     *
     * @param checkbox Элемент-чекбокс, для которого требуется получить название
     *
     * @return Название чекбокса
     */
    private String getCheckboxName(WebElement checkbox) {
        String checkboxNameXpath = "span[@class = 'calculator__check-block-text']/span";

        if (isElementPresent(checkbox.findElement(By.xpath
                (checkboxNameXpath)))) {
            return checkbox.findElement(By.xpath(checkboxNameXpath)).getText();
        }

        return "";
    }

    /**
     * Метод ищет в коллекции чекбокс с указанным названием
     *
     * @param checkboxName Название чекбокса
     *
     * @return Искомый элемент-чекбокс
     */
    private WebElement findCheckboxOption(String checkboxName) {
        for (WebElement checkboxOption : checkboxes) {
            if (getCheckboxName(checkboxOption).equalsIgnoreCase(checkboxName)) {
                return checkboxOption;
            }
        }

        Assert.fail("Не найдена опция \"" + checkboxName + "\"");
        return null;
    }

    /**
     * Метод проставляет чекбокс с заданным названием
     *
     * @param checkboxName Название чекбокса
     */
    public void selectCheckbox(String checkboxName) {
        WebElement checkbox = findCheckboxOption(checkboxName);

        checkbox.findElement(By.xpath
                (".//span[@class = 'calculator__check-block-input']")).click();
    }

    /**
     * Метод заполняет поле параметра вклада заданным значением
     *
     * @param fieldName Название поля параметра
     * @param parameter Данные для ввода
     */
    public void fillParameter(String fieldName, String parameter) {
        switch (fieldName) {
            case "Валюта":
                selectCollectionItem(parameter, currencies);
                break;
            case "Сумма вклада":
                fillField(amountInput, parameter);
                break;
            case "На срок":
                selectPeriod(parameter);
                break;
            case "Ежемесячное пополнение":
                fillField(monthlyAddInput, parameter);
                break;
            default:
                Assert.fail("Неправильное название поля для заполнения - \"" + fieldName + "\"");
        }
    }
}
