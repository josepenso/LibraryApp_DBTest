package com.library.step_definitions;



import com.library.utility.Config;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    @Before("@ui")
    public void setUp(){

        Driver.setupDriver(Config.getProperty("browser"));
        Driver.getDriver().get(Config.getProperty("library_url"));


    }



    @After("@ui")
    public void teardownScenario(Scenario scenario) {
        // We will implement taking screenshot in this method
        //System.out.println("It will be closing browser using cucumber @After each scenario");
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());


        }

        Driver.quitDriver();
    }

    @Before("@db")
    public void setupDB(){
        DB_Util.createConnection();
    }

    @After("@db")
    public void destroyDB(){
        DB_Util.destroy();
    }




}

