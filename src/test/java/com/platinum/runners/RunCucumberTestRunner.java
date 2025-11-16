package com.platinum.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "com.platinum.steps",
    plugin = {
        "pretty",
        "html:target/cucumber-report.html"
    },
    monochrome = true
)
public class RunCucumberTestRunner {
    // Vacío: Cucumber se encarga de todo
}
