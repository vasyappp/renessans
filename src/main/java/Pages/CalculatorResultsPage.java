package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Описание страницы рассчитанных результирующих данных для вклада
 */
public class CalculatorResultsPage extends BasePage {
    private static final String calculatorResultsXpath = ".//div[@class = 'calculator__result-block']"; // Путь к результатам работы калькулятора

    @FindBy(xpath = calculatorResultsXpath +
            "/div[@class = 'calculator__dep-percent']/span/span")
    public WebElement percent; // Рассчитанная ставка

    @FindBy(xpath = calculatorResultsXpath +
            "//td[contains(text(), 'Начислено %')]/following-sibling::td//span[@class = 'js-calc-earned']")
    public WebElement earned; // Рассчитанные начисленные проценты

    @FindBy(xpath = calculatorResultsXpath +
            "//td[contains(text(), 'Пополнение за')]/following-sibling::td//span[@class = 'js-calc-replenish']")
    public WebElement replenish; // Рассчитанное пополнение

    @FindBy(xpath = calculatorResultsXpath +
            "//div[contains(text(), 'К снятию через')]/following-sibling::div/span[@class = 'js-calc-result']")
    public WebElement result; // Рассчитаная сумма к снятию

    /**
     * Метод получает рассчитанный калькулятором результат
     *
     * @param fieldName Искомый вид результата
     *
     * @return Рассчитанный результат
     */
    public String getResult(String fieldName) {
        switch (fieldName) {
            case "Ставка":
                scrollToElement(percent);
                return percent.getText();
            case "Начислено":
                scrollToElement(earned);
                return earned.getText();
            case "Пополнение":
                scrollToElement(replenish);
                return replenish.getText();
            case "К снятию":
                scrollToElement(result);
                return result.getText();
            default:
                Assert.fail("Неправильное название поля для проверки - " + fieldName);
                return null;
        }
    }
}
