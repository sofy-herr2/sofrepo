package onbrand;


import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@CucumberOptions(format = {"pretty","html:html-report", "json:json-report"},features="src/test")
@CucumberOptions(plugin={"pretty","html:html-report", "json:json-report"},features="src/test/")
public class TestRunner {
    @Test(groups = "examples-testng", description = "Example of using TestNGCucumberRunner to invoke Cucumber")
    public void runCukes() {
        new TestNGCucumberRunner(getClass()).runCukes();
    }
}
