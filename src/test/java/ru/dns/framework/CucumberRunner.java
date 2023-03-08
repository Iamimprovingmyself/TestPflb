package ru.dns.framework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        glue = {"ru.dns.framework.steps"},
        features = {"src/test/resources/"},
        tags = "@DNS"
)
public class CucumberRunner {
}
