package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
  // features = "src/test/java/features/POSTResponse.feature",
  // features = "src/test/java/features/GETResponse.feature",
  // features = "src/test/java/features/PUTResponse.feature",

   features = "src/test/java/features/GETResponse.feature",

   glue = "stepDefination",
	plugin = { "pretty", "html:target/cucumber-reports"},

   monochrome = true,
	strict = true
   )
public class junitRunner  {
	 

}