package Steps;

import Pages.MainPage;
import io.qameta.allure.Step;

/**
 * Описание шагов теста для главной страницы сайта Ренессанс Кредит
 */
public class MainSteps {

    @Step("выбран тип услуги {0}")
    public void stepSelectServiceType(String serviceType) {
        new MainPage().selectServiceType(serviceType);
    }

    @Step("выбрана услуга {0}")
    public void stepSelectService(String service) {
        new MainPage().selectService(service);
    }

}
