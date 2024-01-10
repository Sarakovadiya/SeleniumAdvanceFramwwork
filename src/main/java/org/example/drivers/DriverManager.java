package org.example.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.util.Objects;

public class DriverManager {

   public static final ThreadLocal<WebDriver> dr= new ThreadLocal<>();
   public static void setDriver(WebDriver driverRef){
       dr.set(driverRef);
   }
   public static WebDriver getDriver(){
        return dr.get();
   }
   public static void unload(){
       dr.remove();
   }
   public static void down(){
       if(Objects.nonNull(DriverManager.getDriver())){
           getDriver().quit();
           unload();
       }
   }
   public static void init() throws MalformedURLException {
       if (Objects.isNull(DriverManager.getDriver())){
           ChromeOptions options = new ChromeOptions();
           options.addArguments("--guest");
           options.addArguments("--remote-allow-origins=*");
           WebDriver driver = new ChromeDriver(options);
           setDriver(driver);
       }
   }

}
