package ru.dns.framework.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.dns.framework.managers.DriverManager;
import ru.dns.framework.managers.InitManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class Hooks {

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("failureScreenshot", "image/png", addScreenshot(), "png");
        }
        InitManager.quitFramework();
    }

    private static InputStream addScreenshot() {
        byte[] screenshot = ((TakesScreenshot) DriverManager.getINSTANCE().getDriver()).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }
}
