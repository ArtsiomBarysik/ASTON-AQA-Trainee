package core;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import java.util.Optional;

import static core.BaseSeleniumPage.driver;

public class TestListener implements TestWatcher {

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        driver.close();
        driver.quit();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.addAttachment("Логи теста: ",String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        driver.close();
        driver.quit();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        driver.close();
        driver.quit();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause){
        Allure.getLifecycle().addAttachment("screenshot", "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment("Логи теста: ",String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        driver.close();
        driver.quit();
    }
}
