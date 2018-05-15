package Steps;

import Pages.CalculatorResultsPage;
import io.qameta.allure.Step;
import org.junit.Assert;

/**
 * Описание шагов теста для страницы результатов калькулятора
 */
public class CalculatorResultsSteps {

    @Step("поле {0} равно {1}")
    public void stepCheckResult(String fieldName, String expectedResult) {
        String actualResult = new CalculatorResultsPage().getResult(fieldName);

        if (!actualResult.equalsIgnoreCase(expectedResult))
            try {
                Thread.sleep(5000);
                actualResult = new CalculatorResultsPage().getResult(fieldName);
            } catch (InterruptedException e) {
                Assert.fail("Interrupted");
            }

        Assert.assertTrue(String.format
                ("В поле %s рассчитано значение %s. Ожидалось - %s", fieldName, actualResult, expectedResult),
                actualResult.equalsIgnoreCase(expectedResult));
    }

}
