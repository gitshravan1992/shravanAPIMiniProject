package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
   features = {"src/test/java/features/"},
   glue = "stepDefination",
	plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/report.html"},

   monochrome = true,
	strict = true
   )
public class testngRunner extends AbstractTestNGCucumberTests{

}