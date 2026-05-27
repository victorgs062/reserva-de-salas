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

        // inicia o navegador
        WebDriver driver = new ChromeDriver();

        try {
            //
            driver.manage().window().maximize();

            // acessa tela de login
            driver.navigate().to("http://localhost:4200/login");

            // preenche email
            driver.findElement(By.id("email"))
                    .sendKeys("victorgs@gmail.com");

            // preenche senha
            driver.findElement(By.id("password"))
                    .sendKeys("123456");

            // clica no botão de login
            driver.findElement(By.id("btn-login")).click();

            // aguarda redirecionamento
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.urlContains("/admin/inicio")
            );

            // valida se login foi realizado
            Assertions.assertTrue(
                    driver.getCurrentUrl().contains("/admin/inicio")
            );

        } finally {

            // fecha o navegador
            driver.quit();
        }
    }

    @Test
    public void naoDeveriaEfetuarLoginComSenhaIncorreta() {

        // inicia o navegador
        WebDriver driver = new ChromeDriver();

        try {

            // acessa tela de login
            driver.navigate().to("http://localhost:4200/login");

            // preenche email
            driver.findElement(By.id("email"))
                    .sendKeys("victorgs@gmail.com");

            // preenche senha incorreta
            driver.findElement(By.id("password"))
                    .sendKeys("senha_errada");

            // clica no botão de login
            driver.findElement(By.id("btn-login")).click();

            // aguarda mensagem de erro
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("swal2-html-container")
                    )
            );

            // valida que não houve redirecionamento
            Assertions.assertFalse(
                    driver.getCurrentUrl().contains("/admin/inicio")
            );

            // captura mensagem de erro
            String mensagem = driver.findElement(
                    By.id("swal2-html-container")
            ).getText();

            // valida mensagem apresentada
            Assertions.assertEquals(
                    "Email ou senha inválido",
                    mensagem
            );

        } finally {

            // fecha o navegador
            driver.quit();
        }
    }
}