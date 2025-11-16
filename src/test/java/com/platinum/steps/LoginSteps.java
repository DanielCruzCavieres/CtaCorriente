package com.platinum.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.platinum.config.DriverFactory;

import java.time.Duration;

public class LoginSteps {

    private final WebDriver driver = DriverFactory.getDriver();

    // ================================
    // LOGIN USUARIO - CASO EXITOSO
    // ================================
    @Given("que estoy en la página de login de usuario")
    public void que_estoy_en_la_pagina_de_login_de_usuario() {
        // Ajusta el contexto si es distinto
        driver.get("http://localhost:8080/CtaCorriente/loginUsuario.jsp");
    }

    @When("ingreso un nombre de usuario válido guardado en la BD")
    public void ingreso_un_nombre_de_usuario_valido_guardado_en_la_bd() {
        driver.findElement(By.name("nombreUsuario")).sendKeys("admin"); // ajusta según tus datos
    }

    @When("ingreso una contraseña válida")
    public void ingreso_una_contrasena_valida() {
        driver.findElement(By.name("password")).sendKeys("1234"); // ajusta según tus datos
    }

    @When("presiono el botón Ingresar")
    public void presiono_el_boton_ingresar() {
        driver.findElement(By.cssSelector("button[type='submit'],input[type='submit']")).click();
    }

    @Then("debo ser redirigido al menú de usuario")
    public void debo_ser_redirigido_al_menu_de_usuario() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Espera a que la URL cambie a algo que represente el menú de usuario
        wait.until(ExpectedConditions.urlContains("menuUsuario"));

        Assert.assertTrue(
                "No se redirigió correctamente al menú de usuario",
                driver.getCurrentUrl().contains("menuUsuario")
        );
    }

    // ================================
    // LOGIN USUARIO - CASO FALLIDO
    // ================================
    @When("ingreso un nombre de usuario inválido")
    public void ingreso_un_nombre_de_usuario_invalido() {
        driver.findElement(By.name("nombreUsuario")).clear();
        driver.findElement(By.name("nombreUsuario")).sendKeys("usuario_inexistente");
    }

    @When("ingreso una contraseña inválida")
    public void ingreso_una_contrasena_invalida() {
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("xxxxxx");
    }

    @Then("debo ver un mensaje de error de credenciales inválidas")
    public void debo_ver_un_mensaje_de_error_de_credenciales_invalidas() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Tus JSP de login muestran el error como:
        // <p style="color:red;">...</p>
        WebElement mensajeError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("p[style*='color:red']")
                )
        );

        Assert.assertTrue("El mensaje de error no está visible", mensajeError.isDisplayed());

        String texto = mensajeError.getText().toLowerCase();
        Assert.assertTrue(
                "El texto del mensaje no contiene referencia a error/credenciales",
                texto.contains("error") || texto.contains("credenciales")
        );
    }
}
