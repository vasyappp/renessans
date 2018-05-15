package Pages;

import Utils.Stash;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Описывает главную страницу сайта Ренессанс Кредит
 */
public class MainPage extends BasePage {
    @FindBy(xpath = ".//div[contains(@class, 'services_main')]/div[@class = 'service']")
    public List<WebElement> serviceTypes; // Коллекция видов услуг

    /**
     * Метод получает название вида услуги
     *
     * @param element Услуга, для которой требуется получить ее название
     *
     * @return Название услуги
     */
    private String getServiceTypeName(WebElement element) {
        return element.findElement(By.xpath
                (".//div[@class = 'service__title-text']")).getText();
    }

    /**
     * Метод выбирает заданный вид услуги
     *
     * @param serviceTypeName Название вида услуги
     */
    public void selectServiceType(String serviceTypeName) {
        for (WebElement serviceType : serviceTypes) {
            if (getServiceTypeName(serviceType).equalsIgnoreCase(serviceTypeName)) {
                Stash.getInstance().put(Stash.choosenServiceType, serviceType);
                return;
            }
        }

        Assert.fail("Не найден вид услуги \"" + serviceTypeName + "\"");
    }

    /**
     * Метод выбирает заданную услугу
     *
     * @param serviceName Название услуги
     */
    public void selectService(String serviceName) {
        String serviceXpath = ".//div[@class = 'service__links']/a/span[contains(text(), '" +
                serviceName + "')]";
        WebElement serviceType = (WebElement) Stash.getInstance().get(Stash.choosenServiceType);

        if (isElementPresent(serviceType.findElement(By.xpath(serviceXpath)))) {
            serviceType.findElement(By.xpath(serviceXpath + "/..")).click();
        } else
            Assert.fail("Не найдена услуга \"" + serviceName + "\"");
    }
}
