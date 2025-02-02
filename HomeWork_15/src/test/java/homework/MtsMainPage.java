package homework;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MtsMainPage extends BaseSeleniumPage {

    private WebDriverWait wait;

    // Принятие куки файлов
    @FindBy(id = "cookie-agree")
    private WebElement agreeCookie;

    // Элементы фрейма оплаты
    @FindBy (xpath = "//div[@class='pay-description__cost']//span[contains(text(), 'BYN')]")
    private WebElement payDescriptionCost;
    @FindBy (xpath = "//div[@class='pay-description__text']//span")
    private WebElement payDescriptionText;
    @FindBy (xpath = "//button[@class='colored disabled']")
    private WebElement payButtonDissabled;
    @FindBy (xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement bepaidIframe;
    @FindBy (xpath = "//label[@class='ng-tns-c46-1 ng-star-inserted']")
    private WebElement numberCardLabel;
    @FindBy (xpath = "//label[@class='ng-tns-c46-4 ng-star-inserted']")
    private WebElement durationCardLabel;
    @FindBy (xpath = "//label[@class='ng-tns-c46-5 ng-star-inserted']")
    private WebElement cvcCardLabel;
    @FindBy (xpath = "//label[@class='ng-tns-c46-3 ng-star-inserted']")
    private WebElement holderCardLabel;
    @FindBy (xpath = "//div[@class='cards-brands ng-tns-c46-1']")
    private WebElement payCardsLogoBlock;
    @FindBy (xpath = "//img[@class='ng-tns-c61-0 ng-star-inserted']")
    private List<WebElement> visiblePayCardsLogo;
    // Две карты которые имеют рандомное отображение
    @FindBy (xpath = "//div[@class='cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted']//img[1]")
    private WebElement randomVisiblePayCardsLogoMaestro;
    @FindBy (xpath = "//div[@class='cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted']//img[2]")
    private WebElement randomVisiblePayCardsLogoMir;

    // Титл блока оплаты, партнеры оплаты, ссылка на подробную информацию
    @FindBy(xpath = "//*[@id='pay-section']//h2")
    private WebElement paySectionTitle;
    @FindBy(xpath = "//div[contains(@class, 'pay__partners')]//img")
    private List<WebElement> payParthnersLogo;
    @FindBy (xpath = "//div[@class='pay__wrapper']//a")
    private WebElement moreInfoLink;

    // Селектор выбора услуг
    @FindBy (xpath = "//button[@class='select__header']")
    private WebElement buttonSelectServices;
    @FindBy (xpath = "//p[text()='Услуги связи']")
    private WebElement selectPhoneServices;
    @FindBy (xpath = "//p[text()='Домашний интернет']")
    private WebElement selectInternetServices;
    @FindBy (xpath = "//p[text()='Рассрочка']")
    private WebElement selectInstallmentServices;
    @FindBy (xpath = "//p[text()='Задолженность']")
    private WebElement selectArrearsServices;

    // Поля оплаты телефона
    @FindBy(id = "connection-phone")
    private WebElement phoneInputPhone;
    @FindBy(id = "connection-sum")
    private WebElement sumInputPhone;
    @FindBy (id ="connection-email")
    private WebElement emailInputPhone;
    @FindBy(xpath = "//*[@id='pay-connection']//button")
    private WebElement continueButtonPhone;

    // Поля оплаты интернета
    @FindBy(id = "internet-phone")
    private WebElement phoneInputInternet;
    @FindBy(id = "internet-sum")
    private WebElement sumInputInternet;
    @FindBy(id = "internet-email")
    private WebElement emailInputInternet;
    @FindBy(xpath = "//*[@id='pay-internet']//button")
    private WebElement continueButtonInternet;

    // Поля оплаты рассрочки
    @FindBy(id = "score-instalment")
    private WebElement phoneInputInstallment;
    @FindBy(id = "instalment-sum")
    private WebElement sumInputInstallment;
    @FindBy(id = "instalment-email")
    private WebElement emailInputInstallment;
    @FindBy(xpath = "//*[@id='pay-instalment']//button")
    private WebElement continueButtonInstallment;

    // Поля оплаты задолженности
    @FindBy(id = "score-arrears")
    private WebElement phoneInputArrears;
    @FindBy(id = "arrears-sum")
    private WebElement sumInputArrears;
    @FindBy(id = "arrears-email")
    private WebElement emailInputArrears;
    @FindBy(xpath = "//*[@id='pay-arrears']//button")
    private WebElement continueButtonArrears;
    // Конструктор класса, отвечает за открытие страницы, инициализацию элементов, принятие куки файлов
    public MtsMainPage() {
        driver.get("https://www.mts.by");
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver,5);
        try {
            agreeCookie.click();
            System.out.println("Куки данные были приняты");
        } catch (Exception e) {
            System.out.println("Окно с куки файлами не появилось");
        }
    }

    public String getBlockTitle() {
        return paySectionTitle.getText().replaceAll("\\s+", " ").trim();
    }

    public boolean arePaymentLogoDisplayed() {
        boolean allLogoFound = true;
        for (WebElement logo : payParthnersLogo) {
            if (logo.isDisplayed()) {
                System.out.println("Логотип:" +logo.getAttribute("alt") + " найден");
            } else {
                System.out.println("Логотип:" +logo.getAttribute("alt") + " не найден");
                allLogoFound = false;
            }
        }
        return allLogoFound;
    }

    public void clickMoreInfoLink() {
        moreInfoLink.click();
    }

    public void fillAndSubmitForm(String phone, String sum) {
        buttonSelectServices.click();
        selectPhoneServices.click();
        phoneInputPhone.click();
        phoneInputPhone.sendKeys(phone);
        sumInputPhone.click();
        sumInputPhone.sendKeys(sum);
        continueButtonPhone.click();
    }

    public void switchToPayIframe(){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bepaidIframe));
    }

    public String getDescriptionCost() {
        wait.until(ExpectedConditions.visibilityOf(payDescriptionCost));
        return payDescriptionCost.getText();
    }

    public String getButtonText() {
        wait.until(ExpectedConditions.visibilityOf(payButtonDissabled));
        return payButtonDissabled.getText();
    }

    public String getDescriptionText(){
        wait.until(ExpectedConditions.visibilityOf(payDescriptionText));
        return payDescriptionText.getText();
    }

    public boolean compareSumDescriptionOrder(String expectedSum){
        String actualSum = getDescriptionCost();
        return expectedSum.equals(actualSum.replaceAll("\\.00 BYN",""));
    }

    public boolean compareSumDescriptionButton(String expectedSum){
        String actualSum = getButtonText();
        return expectedSum.equals(actualSum.replaceAll("\\Оплатить ","").replaceAll("\\.00 BYN",""));
    }

    public boolean comparePhoneDescriptionOrder(String expectedPhone){
        String actualPhone = getDescriptionText();
        return expectedPhone.equals(actualPhone.replaceAll("\\Оплата: Услуги связи Номер:375",""));
    }

    public String getNumberCardFieldLabel(){
        wait.until(ExpectedConditions.visibilityOf(numberCardLabel));
        return numberCardLabel.getText();
    }

    public String getDurationCardFieldLabel(){
        wait.until(ExpectedConditions.visibilityOf(durationCardLabel));
        return durationCardLabel.getText();
    }

    public String getCvcCardFieldLabel(){
        wait.until(ExpectedConditions.visibilityOf(cvcCardLabel));
        return cvcCardLabel.getText();
    }

    public String getHolderCardFieldLabel(){
        wait.until(ExpectedConditions.visibilityOf(holderCardLabel));
        return holderCardLabel.getText();
    }

    public boolean compareNumberLabel(String expectedNumberLabel){
        String actualNumberLabel = getNumberCardFieldLabel();
        return expectedNumberLabel.equals(actualNumberLabel);
    }

    public boolean compareDurationLabel(String expectedDurationLabel){
        String actualDurationLabel = getDurationCardFieldLabel();
        return expectedDurationLabel.equals(actualDurationLabel);
    }

    public boolean compareCvcLabel(String expectedCvcLabel){
        String actualCvcLabel = getCvcCardFieldLabel();
        return expectedCvcLabel.equals(actualCvcLabel);
    }

    public boolean compareHolderLabel(String expectedHolderLabel){
        String actualHolderLabel = getHolderCardFieldLabel();
        return expectedHolderLabel.equals(actualHolderLabel);
    }

    public boolean areVisibleCardsLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(payCardsLogoBlock));
        System.out.println("Проверка статичных логотипов:");
        boolean allLogoFound = true;
        for (WebElement logo : visiblePayCardsLogo) {
            if (logo.isDisplayed()) {
                System.out.println("Логотип отображается");
            } else {
                System.out.println("Логотип не отображается");
                allLogoFound = false;
            }
        }
        return allLogoFound;
    }

    public boolean areRandomVisibleCardsLogoDisplayed(){
        System.out.println("Проверка сменяющихся логотипов:");
        boolean maestroLogoVisible = wait.until(ExpectedConditions.visibilityOf(randomVisiblePayCardsLogoMaestro)).isDisplayed();
        boolean mirLogoVisible = wait.until(ExpectedConditions.visibilityOf(randomVisiblePayCardsLogoMir)).isDisplayed();
        if (maestroLogoVisible) {
            System.out.println("Логотип Maestro виден");
        }
        if (mirLogoVisible) {
            System.out.println("Логотип Mir виден");
        }
        return maestroLogoVisible && mirLogoVisible;
    }

    public void selectPhoneServices(){
        buttonSelectServices.click();
        selectPhoneServices.click();
    }

    public boolean comparePhonePhonePlaceholder(String expectedPhonePlaceholder){
        String actualPhonePlaceholder = phoneInputPhone.getAttribute("placeholder");
        return expectedPhonePlaceholder.equals(actualPhonePlaceholder);
    }

    public boolean comparePhoneSumPlaceholder(String expectedSumPlaceholder){
        String actualSumPlaceholder = sumInputPhone.getAttribute("placeholder");
        return expectedSumPlaceholder.equals(actualSumPlaceholder);
    }

    public boolean comparePhoneEmailPlaceholder(String expectedEmailPlaceholder){
        String actualEmailPlaceholder = emailInputPhone.getAttribute("placeholder");
        return expectedEmailPlaceholder.equals(actualEmailPlaceholder);
    }

    public void selectInternetServices(){
        buttonSelectServices.click();
        selectInternetServices.click();
    }

    public boolean compareInternetPhonePlaceholder(String expectedPhonePlaceholder){
        String actualPhonePlaceholder = phoneInputInternet.getAttribute("placeholder");
        return expectedPhonePlaceholder.equals(actualPhonePlaceholder);
    }

    public boolean compareInternetSumPlaceholder(String expectedSumPlaceholder){
        String actualSumPlaceholder = sumInputInternet.getAttribute("placeholder");
        return expectedSumPlaceholder.equals(actualSumPlaceholder);
    }

    public boolean compareInternetEmailPlaceholder(String expectedEmailPlaceholder){
        String actualEmailPlaceholder = emailInputInternet.getAttribute("placeholder");
        return expectedEmailPlaceholder.equals(actualEmailPlaceholder);
    }

    public void selectInstallmentServices(){
        buttonSelectServices.click();
        selectInstallmentServices.click();
    }

    public boolean compareInstallmentPhonePlaceholder(String expectedPhonePlaceholder){
        String actualPhonePlaceholder = phoneInputInstallment.getAttribute("placeholder");
        return expectedPhonePlaceholder.equals(actualPhonePlaceholder);
    }

    public boolean compareInstallmentSumPlaceholder(String expectedSumPlaceholder){
        String actualSumPlaceholder = sumInputInstallment.getAttribute("placeholder");
        return expectedSumPlaceholder.equals(actualSumPlaceholder);
    }

    public boolean compareInstallmentEmailPlaceholder(String expectedEmailPlaceholder){
        String actualEmailPlaceholder = emailInputInstallment.getAttribute("placeholder");
        return expectedEmailPlaceholder.equals(actualEmailPlaceholder);
    }

    public void selectArrearsServices(){
        buttonSelectServices.click();
        selectArrearsServices.click();
    }

    public boolean compareArrearsPhonePlaceholder(String expectedPhonePlaceholder){
        String actualPhonePlaceholder = phoneInputArrears.getAttribute("placeholder");
        return expectedPhonePlaceholder.equals(actualPhonePlaceholder);
    }

    public boolean compareArrearsSumPlaceholder(String expectedSumPlaceholder){
        String actualSumPlaceholder = sumInputArrears.getAttribute("placeholder");
        return expectedSumPlaceholder.equals(actualSumPlaceholder);
    }

    public boolean compareArrearsEmailPlaceholder(String expectedEmailPlaceholder){
        String actualEmailPlaceholder = emailInputArrears.getAttribute("placeholder");
        return expectedEmailPlaceholder.equals(actualEmailPlaceholder);
    }
}