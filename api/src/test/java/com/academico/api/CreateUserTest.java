package com.academico.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CreateUserTest {

    // Modo demo ativa pausas para visualizar o teste rodando
    private static final boolean DEMO_MODE = true;

    @Test
    public void deveriaCriarNovoUsuarioComSucesso() throws Exception {

        WebDriver driver = new ChromeDriver();

        try {

            // Maximiza a janela do navegador
            driver.manage().window().maximize();

            // Aguarda elementos da página com timeout de 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Acessa a página de login
            driver.navigate().to("http://localhost:4200/login");
            pausa(2000);

            // Preenche email e senha com digitação simulada
            digitarLento(driver, By.id("email"), "victorgs@gmail.com", 120);
            pausa(1000);

            digitarLento(driver, By.id("password"), "123456", 120);
            pausa(1000);

            // Clica no botão de login
            driver.findElement(By.id("btn-login")).click();
            pausa(2000);

            // Aguarda redirecionamento para página inicial do admin
            wait.until(ExpectedConditions.urlContains("/admin/inicio"));

            // Valida se login foi bem-sucedido
            Assertions.assertTrue(driver.getCurrentUrl().contains("/admin/inicio"));

            // Abre menu de usuários
            driver.findElement(By.linkText("Usuários")).click();
            pausa(2000);

            // Aguarda página de usuários carregar
            wait.until(ExpectedConditions.urlContains("/admin/users"));

            // Valida navegação
            Assertions.assertTrue(driver.getCurrentUrl().contains("/admin/users"));

            // Clica no botão de adicionar novo usuário
            driver.findElement(By.cssSelector(".btn.btn-dark.fab-btn")).click();
            pausa(2000);

            // Nome do usuário
            digitarLento(driver, By.id("nome"), "Victor", 100);
            pausa(1000);

            // Gera email único para evitar conflito em testes
            String email = "victor" + System.currentTimeMillis() + "@gmail.com";
            digitarLento(driver, By.id("email"), email, 100);
            pausa(1000);

            // Seleciona tipo de usuário
            driver.findElement(By.id("type")).click();
            pausa(1000);

            // Seleciona opção "ALUNO"
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[normalize-space()='ALUNO']")
            )).click();

            pausa(1000);

            // Senha do usuário
            digitarLento(driver, By.id("password"), "123456", 100);
            pausa(1000);

            driver.findElement(By.id("submit")).click();
            pausa(2000);

            // Aguarda mensagem de sucesso aparecer
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".swal2-icon.swal2-success")
            ));

            // Aguarda texto de confirmação
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("swal2-html-container"),
                    "O usuário foi criado com sucesso."
            ));

            // Confirma que o alerta de sucesso apareceu
            Assertions.assertTrue(
                    driver.findElements(By.cssSelector(".swal2-icon.swal2-success")).size() > 0
            );

            // Valida mensagem final exibida
            String mensagem = driver.findElement(By.id("swal2-html-container")).getText();

            Assertions.assertEquals(
                    "O usuário foi criado com sucesso.",
                    mensagem
            );

            pausa(3000);

        } finally {
            // Fecha o navegador ao final do teste
            driver.quit();
        }
    }

    // Simula pausas para facilitar demonstração em apresentação
    private void pausa(long ms) throws InterruptedException {
        if (DEMO_MODE) {
            Thread.sleep(ms);
        }
    }

    // Digita caractere por caractere para simular comportamento humano
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