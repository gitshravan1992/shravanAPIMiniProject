package runner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.apache.poi.ddf.EscherColorRef.SysIndexProcedure;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.Pickle;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.restassured.http.Method;



@CucumberOptions(
   features = {"src/test/java/features/"},
   glue = "stepDefination",
	plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/report.html"},

   monochrome = true,
	strict = true
   )
public class testngRunnerSpecific extends AbstractTestNGCucumberTests{
	
	
	private TestNGCucumberRunner testNGCucumberRunner;
	@Parameters({"featurePath"})
	@BeforeTest(alwaysRun = true)
	public void setUpTest(@Optional("src/test/java/features/") String featurePath) throws Exception {   
	    Class<?> testClass = this.getClass();
	    changeCucumberAnnotation(testClass, "features", new String [] {featurePath});       
	    testNGCucumberRunner = new TestNGCucumberRunner(testClass);        
	}

	private static void changeCucumberAnnotation(Class<?> clazz, String key, Object newValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{  
	    Annotation options = clazz.getAnnotation(CucumberOptions.class);                   //get the CucumberOptions annotation  
	    InvocationHandler proxyHandler = Proxy.getInvocationHandler(options);              //setup handler so we can update Annotation using reflection. Basically creates a proxy for the Cucumber Options class
	    Field f = proxyHandler.getClass().getDeclaredField("memberValues");                //the annotaton key/values are stored in the memberValues field
	    f.setAccessible(true);                                                             //suppress any access issues when looking at f
	    Map<String, Object> memberValues = (Map<String, Object>) f.get(proxyHandler);      //get the key-value map for the proxy
	    memberValues.remove(key);                                                          //renove the key entry...don't worry, we'll add it back
	    memberValues.put(key,newValue);                                                    //add the new key-value pair. The annotation is now updated.
	}//end method

}