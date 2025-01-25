package homework;

import core.BaseSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MtsMainPage extends BaseSelenium {

    // Поля оплаты телефона
    @FindBy(id = "connection-phone")
    private WebElement phoneInputPhone;
    @FindBy(id = "connection-sum")
    private WebElement sumInputPhone;
    @FindBy (id ="connection-email")
    private WebElement emailInputPhone;
    @FindBy(xpath = "//*[@id='pay-connection']//button")
    private WebElement continueButtonPhone;

    @FindBy (xpath = "//*[@id='pay-section']//h2")
    private WebElement paySectionTitle;

    @FindBy (id ="id='cookie-agree")
    private WebElement agreeCookie;

    // Поля оплаты интернета
    @FindBy(id = "connection-phone")
    private WebElement phoneInputInternet;
    @FindBy(id = "connection-sum")
    private WebElement sumInputInternet;
    @FindBy (id ="connection-email")
    private WebElement emailInputInternet;
    @FindBy(xpath = "//*[@id='pay-internet']//button")
    private WebElement continueButtonInternet;

    // Поля оплаты рассрочки
    @FindBy(id = "score-instalment")
    private WebElement phoneInputInstallment;
    @FindBy(id = "instalment-sum")
    private WebElement sumInputInstallment;
    @FindBy (id ="instalment-email")
    private WebElement emailInputInstallment;
    @FindBy(xpath = "//*[@id='pay-instalment']//button")
    private WebElement continueButtonInstallment;

    // Поля оплаты задолженности
    @FindBy(id = "score-arrears")
    private WebElement phoneInputArrears;
    @FindBy(id = "arrears-sum")
    private WebElement sumInputArrears;
    @FindBy (id ="arrears-email")
    private WebElement emailInputArrears;
    @FindBy(xpath = "//*[@id='pay-arrears']//button")
    private WebElement continueButtonArrears;

    public MtsMainPage() {
        driver.get("https://www.mts.by");
        PageFactory.initElements(driver,this);
        try {
            WebElement acceptCookiesButton = driver.findElement(By.id("id='cookie-agree"));
            acceptCookiesButton.click();
            System.out.println("Куки данные были приняты");
        } catch (Exception e) {
            // Игнорирование если окно с куки не появилось
            System.out.println("Окно с куки файлами не появилось");
        }
    }

    public String getBlockTitle() {
        return paySectionTitle.getText().replaceAll("\\s+"," ").trim();
    }
//    public boolean arePaymentLogo(List<String> expectedAltLogo) {
//
//    }
}
