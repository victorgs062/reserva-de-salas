package com.academico.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginTestDemo {

    // Modo demonstração (deixa o teste mais lento e visual para apresentação)
    private static final boolean DEMO_MODE = true;

    @Test
    public void deveriaEfetuarLoginComDadosValidos() throws Exception {

        // inicia o navegador
        WebDriver driver = new ChromeDriver();

        try {

            // maximiza a janela para melhor visualização na apresentação
            driver.manage().window().maximize();

            // espera explícita para validações de tela
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // acessa tela de login
            driver.navigate().to("http://localhost:4200/login");
            pausa(2000); // tempo para o público visualizar a tela carregando

            // preenche email (digitação lenta para simular usuário real)
            digitarLento(driver, By.id("email"), "victorgs@gmail.com", 120);
            pausa(1000);

            // preenche senha
            digitarLento(driver, By.id("password"), "123456", 120);
            pausa(1000);

            // clica no botão de login
            driver.findElement(By.id("btn-login")).click();
            pausa(2000); // aguarda transição visual do sistema

            // aguarda redirecionamento
            wait.until(
                    ExpectedConditions.urlContains("/admin/inicio")
            );

            // valida se login foi realizado
            Assertions.assertTrue(
                    driver.getCurrentUrl().contains("/admin/inicio")
            );

            pausa(3000); // mantém a tela final visível na apresentação

        } finally {

            // fecha o navegador
            driver.quit();
        }
    }

    @Test
    public void naoDeveriaEfetuarLoginComSenhaIncorreta() throws Exception {

        // inicia o navegador
        WebDriver driver = new ChromeDriver();

        try {

            // maximiza janela para apresentação
            driver.manage().window().maximize();

            // espera explícita para elementos dinâmicos
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            // acessa tela de login
            driver.navigate().to("http://localhost:4200/login");
            pausa(2000);

            // preenche email
            digitarLento(driver, By.id("email"), "victorgs@gmail.com", 120);
            pausa(1000);

            // preenche senha incorreta
            digitarLento(driver, By.id("password"), "senha_errada", 120);
            pausa(1000);

            // clica no botão de login
            driver.findElement(By.id("btn-login")).click();
            pausa(2000);

            // aguarda mensagem de erro
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("swal2-html-container")
                    )
            );

            // valida que não houve redirecionamento
            Assertions.assertFalse(
                    driver.getCurrentUrl().contains("/admin/inicio")
            );

            // captura mensagem de erro exibida na tela
            String mensagem = driver.findElement(
                    By.id("swal2-html-container")
            ).getText();

            // valida mensagem apresentada
            Assertions.assertEquals(
                    "Email ou senha inválido",
                    mensagem
            );

            pausa(3000); // mantém alerta visível para demonstração

        } finally {

            // fecha o navegador
            driver.quit();
        }
    }


    // Pausa controlada para tornar o teste visível em apresentações.
    private void pausa(long ms) throws InterruptedException {
        if (DEMO_MODE) {
            Thread.sleep(ms);
        }
    }

    // Simula digitação humana caractere por caractere.
    private void digitarLento(WebDriver driver, By locator, String texto, long delay)
            throws InterruptedException {

        if (!DEMO_MODE) {
            driver.findElement(locator).sendKeys(texto);
            return;
        }

        for (char c : texto.toCharArray()) {
            driver.findElement(locator).sendKeys(String.valueOf(c));
            Thread.sleep(delay);
        }
    }
}