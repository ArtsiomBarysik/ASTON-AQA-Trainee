package tests;

import core.BaseSeleniumTest;
import core.TestListener;
import org.example.pages.MtsMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(TestListener.class)
@DisplayName("Тесты блока 'Онлайн пополнение без комиссии'")
public class MtsMainPageTest extends BaseSeleniumTest {
    // Проверочные данные Заголовка и ссылка на подробнее о сервисе
    private static final String EXPECTED_TITLE = "Онлайн пополнение без комиссии";
    private static final String MORE_INFO_URL = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
    // Тестовый номер и сумма
    private static final String TEST_PHONE = "297777777";
    private static final String TEST_SUM = "20";
    // Проверочные данные для данных карты в форме оплаты
    private static final String EXPECTED_NUMBER_CARD = "Номер карты";
    private static final String EXPECTED_DURATION_CARD = "Срок действия";
    private static final String EXPECTED_CVC_CARD ="CVC";
    private static final String EXPECTED_HOLDER_CARD ="Имя держателя (как на карте)";
    // Проверочные плейсхолдеры номера телефона/счета для вариантов услуг
    private static final String EXPECTED_PHONE_PHONE_PLACEHOLDER ="Номер телефона";
    private static final String EXPECTED_INTERNET_PHONE_PLACEHOLDER ="Номер абонента";
    private static final String EXPECTED_INSTALLMENT_PHONE_PLACEHOLDER ="Номер счета на 44";
    private static final String EXPECTED_ARREARS_PHONE_PLACEHOLDER ="Номер счета на 2073";
    // Проверочные плейсхолдеры для полей суммы и емайла для всех вариантов услуг
    private static final String EXPECTED_SUM_PLACEHOLDER ="Сумма";
    private static final String EXPECTED_EMAIL_PLACEHOLDER ="E-mail для отправки чека";

    @Test
    @DisplayName("Проверка названия блока")
    public void checkTitleBlock(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        String actualTitle = mtsMainPage.getBlockTitle();
        assertEquals(EXPECTED_TITLE, actualTitle);
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
        assertTrue(driver.getCurrentUrl().contains(MORE_INFO_URL));
        driver.navigate().back();
    }

    @Test
    @DisplayName("Проверка заполнения формы оплаты телефона")
    public void checkPaymentPhone() {
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.fillAndSubmitForm(TEST_PHONE,TEST_SUM);
        mtsMainPage.switchToPayIframe();
        assertTrue(mtsMainPage.compareSumDescriptionOrder(TEST_SUM));
        assertTrue(mtsMainPage.compareSumDescriptionButton(TEST_SUM));
        assertTrue(mtsMainPage.comparePhoneDescriptionOrder(TEST_PHONE));
    }

    @Test
    @DisplayName("Проверка плэйсходеров в форме оплаты телефона")
    public void checkPlaceholderPhone(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.fillAndSubmitForm(TEST_PHONE,TEST_SUM);
        mtsMainPage.switchToPayIframe();
        assertTrue(mtsMainPage.compareNumberLabel(EXPECTED_NUMBER_CARD));
        assertTrue(mtsMainPage.compareDurationLabel(EXPECTED_DURATION_CARD));
        assertTrue(mtsMainPage.compareCvcLabel(EXPECTED_CVC_CARD));
        assertTrue(mtsMainPage.compareHolderLabel(EXPECTED_HOLDER_CARD));
    }

    @Test
    @DisplayName("Проверка логотипов платежных карт в форме оплаты")
    public void checkLogoCardOrder(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.fillAndSubmitForm(TEST_PHONE,TEST_SUM);
        mtsMainPage.switchToPayIframe();
        assertTrue(mtsMainPage.areVisibleCardsLogoDisplayed());
        assertTrue(mtsMainPage.areRandomVisibleCardsLogoDisplayed());
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты услуг связи")
    public void checkPlaceholdersPhone(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectPhoneServices();
        assertTrue(mtsMainPage.comparePhonePhonePlaceholder(EXPECTED_PHONE_PHONE_PLACEHOLDER));
        assertTrue(mtsMainPage.comparePhoneSumPlaceholder(EXPECTED_SUM_PLACEHOLDER));
        assertTrue(mtsMainPage.comparePhoneEmailPlaceholder(EXPECTED_EMAIL_PLACEHOLDER));
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты интернета")
    public void checkPlaceholdersInternet(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectInternetServices();
        assertTrue(mtsMainPage.compareInternetPhonePlaceholder(EXPECTED_INTERNET_PHONE_PLACEHOLDER));
        assertTrue(mtsMainPage.compareInternetSumPlaceholder(EXPECTED_SUM_PLACEHOLDER));
        assertTrue(mtsMainPage.compareInternetEmailPlaceholder(EXPECTED_EMAIL_PLACEHOLDER));
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты рассрочки")
    public void checkPlaceholdersInstallment(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectInstallmentServices();
        assertTrue(mtsMainPage.compareInstallmentPhonePlaceholder(EXPECTED_INSTALLMENT_PHONE_PLACEHOLDER));
        assertTrue(mtsMainPage.compareInstallmentSumPlaceholder(EXPECTED_SUM_PLACEHOLDER));
        assertTrue(mtsMainPage.compareInstallmentEmailPlaceholder(EXPECTED_EMAIL_PLACEHOLDER));
    }

    @Test
    @DisplayName("Проверка плейсхолдеров оплаты задолженности")
    public void checkPlaceholdersArrears(){
        MtsMainPage mtsMainPage = new MtsMainPage();
        mtsMainPage.selectArrearsServices();
        assertTrue(mtsMainPage.compareArrearsPhonePlaceholder(EXPECTED_ARREARS_PHONE_PLACEHOLDER));
        assertTrue(mtsMainPage.compareArrearsSumPlaceholder(EXPECTED_SUM_PLACEHOLDER));
        assertTrue(mtsMainPage.compareArrearsEmailPlaceholder(EXPECTED_EMAIL_PLACEHOLDER));
    }
}