package homework;

import core.BaseSeleniumTest;
import core.TestListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(TestListener.class)
@DisplayName("Тесты блока 'Онлайн пополнение без комиссии'")
public class MtsMainPageTest extends BaseSeleniumTest {
    // Проверочные данные Заголовка и ссылка на подробнее о сервисе
    static String expectedTitle = "Онлайн пополнение без комиссии";
    static String moreInfoUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
    // Тестовый номер и сумма
    static String phone = "297777777";
    static String sum = "20";
    // Проверочные данные для данных карты в форме оплаты
    static String expectedNumberCard = "Номер карты";
    static String expectedDurationCard = "Срок действия";
    static String expectedCvcCard ="CVC";
    static String expectedHolderCard ="Имя держателя (как на карте)";
    // Проверочные плейсхолдеры номера телефона/счета для вариантов услуг
    static String expectedPhonePhonePlaceholder ="Номер телефона";
    static String expectedInternetPhonePlaceholder ="Номер абонента";
    static String expectedInstallmentPhonePlaceholder ="Номер счета на 44";
    static String expectedArrearsPhonePlaceholder ="Номер счета на 2073";
    // Проверочные плейсхолдеры для полей суммы и емайла для всех вариантов услуг
    static String expectedSumPlaceholder ="Сумма";
    static String expectedEmailPlaceholder ="E-mail для отправки чека";

    @Test
    @DisplayName("Проверка названия блока")
    public void checkTitleBlock(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        String actualTitle = mtsMainPage.getBlockTitle();
        assertEquals(expectedTitle, actualTitle);
        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Тест прошел, название соответствует");
        }
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void checkLogo() {
        MtsMainPage mtsMainPage = new MtsMainPage();
        assertTrue(mtsMainPage.arePaymentLogoDisplayed());
    }

    @Test
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    public void checkMoreInfoLink(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.clickMoreInfoLink();
        assertTrue(driver.getCurrentUrl().contains(moreInfoUrl));
        driver.navigate().back();
    }

    @Test
    @DisplayName("Проверка заполнения формы оплаты телефона")
    public void checkPaymentPhone() {
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.fillAndSubmitForm(phone,sum);
        mtsMainPage.switchToPayIframe();

        assertTrue(mtsMainPage.compareSumDescriptionOrder(sum));
        assertTrue(mtsMainPage.compareSumDescriptionButton(sum));
        assertTrue(mtsMainPage.comparePhoneDescriptionOrder(phone));
    }

    @Test
    @DisplayName("Проверка плэйсходеров в форме оплаты телефона")
    public void checkPlaceholderPhone(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.fillAndSubmitForm(phone,sum);
        mtsMainPage.switchToPayIframe();

        assertTrue(mtsMainPage.compareNumberLabel(expectedNumberCard));
        assertTrue(mtsMainPage.compareDurationLabel(expectedDurationCard));
        assertTrue(mtsMainPage.compareCvcLabel(expectedCvcCard));
        assertTrue(mtsMainPage.compareHolderLabel(expectedHolderCard));
    }

    @Test
    @DisplayName("Проверка логотипов платежных карт в форме оплаты")
    public void checkLogoCardOrder(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.fillAndSubmitForm(phone,sum);
        mtsMainPage.switchToPayIframe();

        assertTrue(mtsMainPage.areVisibleCardsLogoDisplayed());
        assertTrue(mtsMainPage.areRandomVisibleCardsLogoDisplayed());
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты услуг связи")
    public void checkPlaceholdersPhone(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectPhoneServices();
        assertTrue(mtsMainPage.comparePhonePhonePlaceholder(expectedPhonePhonePlaceholder));
        assertTrue(mtsMainPage.comparePhoneSumPlaceholder(expectedSumPlaceholder));
        assertTrue(mtsMainPage.comparePhoneEmailPlaceholder(expectedEmailPlaceholder));
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты интернета")
    public void checkPlaceholdersInternet(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectInternetServices();
        assertTrue(mtsMainPage.compareInternetPhonePlaceholder(expectedInternetPhonePlaceholder));
        assertTrue(mtsMainPage.compareInternetSumPlaceholder(expectedSumPlaceholder));
        assertTrue(mtsMainPage.compareInternetEmailPlaceholder(expectedEmailPlaceholder));
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты рассрочки")
    public void checkPlaceholdersInstallment(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectInstallmentServices();
        assertTrue(mtsMainPage.compareInstallmentPhonePlaceholder(expectedInstallmentPhonePlaceholder));
        assertTrue(mtsMainPage.compareInstallmentSumPlaceholder(expectedSumPlaceholder));
        assertTrue(mtsMainPage.compareInstallmentEmailPlaceholder(expectedEmailPlaceholder));
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты задолженности")
    public void checkPlaceholdersArrears(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectArrearsServices();
        assertTrue(mtsMainPage.compareArrearsPhonePlaceholder(expectedArrearsPhonePlaceholder));
        assertTrue(mtsMainPage.compareArrearsSumPlaceholder(expectedSumPlaceholder));
        assertTrue(mtsMainPage.compareArrearsEmailPlaceholder(expectedEmailPlaceholder));
    }
}