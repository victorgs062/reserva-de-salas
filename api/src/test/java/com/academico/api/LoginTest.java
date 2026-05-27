package com.academico.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginTest {

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {

        WebDriver driver = new ChromeDriver();

        try {

            driver.navigate().to("http://localhost:4200/login");

            driver.findElement(By.id("email"))
                    .sendKeys("victorgs@gmail.com");

            driver.findElement(By.id("password"))
                    .sendKeys("123456");

            driver.findElement(By.id("btn-login")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.urlContains("/admin/inicio")
            );

            Assertions.assertTrue(
                    driver.getCurrentUrl().contains("/admin/inicio")
            );

        } finally {
            driver.quit();
        }
    }
}