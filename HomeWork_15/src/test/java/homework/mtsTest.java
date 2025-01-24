package homework;

import core.BaseSelenium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class mtsTest  extends BaseSelenium {
    private String baseUrl = "https://www.mts.by";
    private String paymentBlockXpath = "//*[@id='pay-section']";
    private String cookiesButtonXpath = "//*[@id='cookie-agree']";
    private List<String> expectedLogoAlt = Arrays.asList(
            "Visa",
            "Verified By Visa",
            "MasterCard",
            "MasterCard Secure Code",
            "Белкарт"
    );
    private String moreInfoEndpoint = "/poryadok-oplaty-i-bezopasnost-internet-platezhey";
    private String phoneFieldXpath = "//*[@id='connection-phone']";
    private String sumFieldXpath = "//*[@id='connection-sum']";
    private String continueButtonXpath = "//*[@id='pay-connection']//button";
    private String testPhone = "297777777";
    private String testSum = "20";
    private String framePayXpath = "//iframe[@class='bepaid-iframe']";
    private String popupPaymentXpath = "//div[@class='app-wrapper']";

    @BeforeEach
    public void openWebSite() {
        driver.get(baseUrl);
        // Обработка всплывающего окна с куки файлами
        try {
            WebElement acceptCookiesButton = driver.findElement(By.xpath(cookiesButtonXpath));
            acceptCookiesButton.click();
        } catch (Exception e) {
            // Игнорирование если окно с куки не появилось
            // (по наблюдению появляется в 4 запусках из 5)
        }
    }

    @Test
    @DisplayName("Проверка названия блока")
    public void checkNameBlock() {
        // Ищу интересующий меня элемент
        WebElement blockTitle = driver.findElement(By.xpath(paymentBlockXpath + "//h2"));
        // Переменные с названием которое должно быть и с названием которое в блоке
        String expectedTitle = "Онлайн пополнение без комиссии";
        String actualTitle = blockTitle.getText().replaceAll("\\s+", " ").trim();
        // Сравниваю оба названия и провожу проверку на соответствие
        boolean result = Objects.equals(expectedTitle, actualTitle);
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void checkPaymentLogo() {
        for (String alt : expectedLogoAlt) {
            WebElement logo = driver.findElement(By.xpath(paymentBlockXpath + "//img[@alt='" + alt + "']"));
            assertNotNull(logo, "Логотип: " + expectedLogoAlt + " не найден");
        }
    }

    @Test
    @DisplayName("Проверка ссылки 'подробнее о сервисе'")
    public void checkMoreInfoLink() {
        WebElement moreInfoLink = driver.findElement(By.xpath(paymentBlockXpath + "//a"));
        assertNotNull(moreInfoLink, "Ссылка подробнее о сервисе не найдена");
        moreInfoLink.click();
        assertTrue(driver.getCurrentUrl().contains(moreInfoEndpoint), "Неверная страница");
        driver.navigate().back();
    }

    @Test
    @DisplayName("Проверка работоспособности кнопки 'Продолжить'")
    public void checkContinueButton() {
        WebElement phoneField = driver.findElement(By.xpath(phoneFieldXpath));
        phoneField.click();
        phoneField.sendKeys(testPhone);
        WebElement sumField = driver.findElement(By.xpath(sumFieldXpath));
        sumField.click();
        sumField.sendKeys(testSum);
        WebElement continueButton = driver.findElement(By.xpath(continueButtonXpath));
        continueButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(framePayXpath)));
        WebElement popupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(popupPaymentXpath)));
        assertNotNull(popupElement, "Всплывающее окно не появилось");
    }
}
