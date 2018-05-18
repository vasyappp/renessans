package Steps;

import Pages.CalculatorResultsPage;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Описание шагов теста для страницы результатов калькулятора
 */
public class CalculatorResultsSteps {

    @Step("поле {0} равно {1}")
    public void stepCheckResult(String fieldName, String expectedResult) {
        WebDriverWait wait = new WebDriverWait(BaseSteps.getDriver(), 10);

        Assert.assertTrue(String.format
                ("В поле %s рассчитано значение %s. Ожидалось - %s", fieldName,
                        new CalculatorResultsPage().getResult(fieldName), expectedResult),
                wait.until((ExpectedCondition<Boolean>) driver -> {
                    String actualResult = new CalculatorResultsPage().getResult(fieldName);
                    return actualResult.equalsIgnoreCase(expectedResult);
                }));
    }

}
