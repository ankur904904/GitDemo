package cucumber.Options;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\java\\feautres",
plugin="json:target\\jsonReports\\cucumber-report.json",glue= {"stepDefinations"})
//tags="@AddPlace1")
public class TestRunner {

}

