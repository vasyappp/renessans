package Steps;

import Pages.CalculatorPage;
import io.qameta.allure.Step;
import org.junit.Assert;

/**
 * Описание шагов теста для калькулятора
 */
public class CalculatorSteps {

    @Step("на странице присутствует заголовок \"{0}\"")
    public void stepCheckTitle(String expectedTitle) {
        CalculatorPage calculatorPage = new CalculatorPage();

        calculatorPage.waitVisibility(calculatorPage.header);

        String actualTitle = calculatorPage.header.getText();

        Assert.assertTrue(String.format
                ("Заголовок страницы равен \"%s\". Ожидалось - \"%s\"", actualTitle, expectedTitle),
                actualTitle.equalsIgnoreCase(expectedTitle));
    }

    @Step("выбран параметр {0} и выставлено значение {1}")
    public void stepSetParameter(String parameterName, String parameter) {
        new CalculatorPage().fillParameter(parameterName, parameter);
    }

    @Step("выставлен срок вклада {0}")
    public void stepSetPeriod(String period) {
        new CalculatorPage().selectPeriod(period);
    }

    @Step("выставлен чекбокс {0}")
    public void stepSetCheckbox(String checkboxName) {
        new CalculatorPage().selectCheckbox(checkboxName);
    }

}
