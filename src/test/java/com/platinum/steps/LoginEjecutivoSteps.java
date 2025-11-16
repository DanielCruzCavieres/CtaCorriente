package com.platinum.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.platinum.config.DriverFactory;

import java.time.Duration;

public class LoginEjecutivoSteps {

    private final WebDriver driver = DriverFactory.getDriver();

    // ================================
    // LOGIN EJECUTIVO - CASO EXITOSO
    // ================================
    @Given("que estoy en la página de login de ejecutivo")
    public void que_estoy_en_la_pagina_de_login_de_ejecutivo() {
        driver.get("http://localhost:8080/CtaCorriente/loginEjecutivo.jsp");
    }

    @When("ingreso un rut de ejecutivo válido sin dv")
    public void ingreso_un_rut_de_ejecutivo_valido_sin_dv() {
        driver.findElement(By.name("rutEjecutivo")).sendKeys("12345678"); // ajusta según tus datos
    }

    @When("ingreso un nombre de ejecutivo válido")
    public void ingreso_un_nombre_de_ejecutivo_valido() {
        driver.findElement(By.name("nombre")).sendKeys("Juan Pérez"); // ajusta según tus datos
    }

    @When("presiono el botón Ingresar como ejecutivo")
    public void presiono_el_boton_ingresar_como_ejecutivo() {
        driver.findElement(By.cssSelector("input[type='submit'],button[type='submit']")).click();
    }

    @Then("debo ser redirigido al menú de ejecutivo")
    public void debo_ser_redirigido_al_menu_de_ejecutivo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("menuEjecutivo"));

        Assert.assertTrue(
                "No se redirigió correctamente al menú de ejecutivo",
                driver.getCurrentUrl().contains("menuEjecutivo")
        );
    }

    // ================================
    // LOGIN EJECUTIVO - CASO FALLIDO
    // ================================
    @When("ingreso un rut de ejecutivo inválido")
    public void ingreso_un_rut_de_ejecutivo_invalido() {
        WebElement rut = driver.findElement(By.name("rutEjecutivo"));
        rut.clear();
        rut.sendKeys("99999999");
    }

    @When("ingreso un nombre de ejecutivo inválido")
    public void ingreso_un_nombre_de_ejecutivo_invalido() {
        WebElement nombre = driver.findElement(By.name("nombre"));
        nombre.clear();
        nombre.sendKeys("XXX");
    }

    @Then("debo ver un mensaje de error de login ejecutivo inválido")
    public void debo_ver_un_mensaje_de_error_de_login_ejecutivo_invalido() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement mensajeError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("p[style*='color:red']")
                )
        );

        Assert.assertTrue("El mensaje de error no está visible", mensajeError.isDisplayed());

        String texto = mensajeError.getText().toLowerCase();
        Assert.assertTrue(
                "El texto del mensaje no contiene referencia a error/ejecutivo",
                texto.contains("error") || texto.contains("ejecutivo") || texto.contains("credenciales")
        );
    }
}
