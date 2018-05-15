package Steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Описание шагов тестов для Cucumber
 */
public class ScenarioSteps {
    MainSteps mainSteps = new MainSteps();
    CalculatorSteps calculatorSteps = new CalculatorSteps();
    CalculatorResultsSteps calculatorResultsSteps = new CalculatorResultsSteps();

    @When("^выбран тип услуги \"(.+)\"$")
    public void selectServiceType(String serviceType) {
        mainSteps.stepSelectServiceType(serviceType);
    }

    @When("^выбрана услуга \"(.+)\"$")
    public void selectService(String service) {
        mainSteps.stepSelectService(service);
    }

    @Then("^заголовок страницы равен \"(.+)\"$")
    public void checkTitle(String expectedTitle) {
        calculatorSteps.stepCheckTitle(expectedTitle);
    }

    @When("^выставлен срок вклада \"(.+)\"$")
    public void setPeriod(String period) {
        calculatorSteps.stepSetPeriod(period);
    }

    @When("^выставлены чекбоксы:$")
    public void setCheckboxes(DataTable checkboxes) {
        checkboxes.asMap(String.class, Boolean.class)
                .forEach((checkboxName, isSet) -> {if (isSet.equals(true)) calculatorSteps.stepSetCheckbox(checkboxName);});
    }

    @Then("^рассчитанные значения равны:$")
    public void checkResults(DataTable results){
        results.asMap(String.class, String.class)
                .forEach((fieldName, expectedResult) ->
                        calculatorResultsSteps.stepCheckResult(fieldName, expectedResult));
    }

    @When("^заполнены поля калькулятора:$")
    public void setParameters(DataTable parameters) {
        parameters.asMap(String.class, String.class)
                .forEach((parameterName, parameter) ->
                        calculatorSteps.stepSetParameter(parameterName, parameter));
    }
}