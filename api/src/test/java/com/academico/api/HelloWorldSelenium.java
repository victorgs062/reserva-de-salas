package com.academico.api;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {

    @Test
    public void hello(){
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com.br/index.html");
        driver.quit();
    }
}
