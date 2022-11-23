package com.cydeo.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final String gridAddress = "localhost";

    public static void setupDriver(String browser) {

        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driverPool.set(new FirefoxDriver());
            driverPool.get().manage().window().maximize();


        } else if (browser.equals("firefox-headless")) {
            WebDriverManager.firefoxdriver().setup();
            driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
            driverPool.get().manage().window().maximize();

        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driverPool.set(new ChromeDriver());
            //driverPool.get().manage().deleteAllCookies();
           driverPool.get().manage().window().maximize();


        } else if (browser.equals("safari")) {
            WebDriverManager.safaridriver().setup();
            driverPool.set(new SafariDriver());
            driverPool.get().manage().deleteAllCookies();
            driverPool.get().manage().window().maximize();


        }else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driverPool.set(new EdgeDriver());
            driverPool.get().manage().deleteAllCookies();
            driverPool.get().manage().window().maximize();

        } else if (browser.equals("chrome-headless")) {
            WebDriverManager.chromedriver().setup();
            driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
            driverPool.get().manage().deleteAllCookies();
            driverPool.get().manage().window().maximize();


        }else if (browser.equals("chrome-remote")) {
            try {
                URL url = new URL("http://"+gridAddress+":4444/wd/hub");
                ChromeOptions options = new ChromeOptions();
                driverPool.set(new RemoteWebDriver(url, options));
                driverPool.get().manage().window().maximize();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else if (browser.equals("edge-remote")) {
            try {
                URL url = new URL("http://"+gridAddress+":4444/wd/hub");
                EdgeOptions options = new EdgeOptions();
                driverPool.set(new RemoteWebDriver(url, options));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else if (browser.equals("firefox-remote")) {
            try {
                URL url = new URL("http://"+gridAddress+":4444/wd/hub");
                FirefoxOptions options = new FirefoxOptions();
                driverPool.set(new RemoteWebDriver(url, options));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public static WebDriver getDriver() {
        return driverPool.get();
    }



    public static void quitDriver() {

        driverPool.get().quit();

    }



}
