package com.platinum.steps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new"); // opcional para Jenkins
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ========= CASOS 4.1 y 4.2: LOGIN =========

    @Dado("que estoy en la página de login de usuario")
    public void que_estoy_en_la_pagina_de_login_de_usuario() {
        driver.get("http://localhost:8080/CtaCorriente/loginUsuario.jsp");
    }

    @Cuando("ingreso un nombre de usuario válido guardado en la BD")
    public void ingreso_un_nombre_de_usuario_valido_guardado_en_la_bd() {
        WebElement txtUsuario = driver.findElement(By.name("nombreUsuario"));
        txtUsuario.clear();
        txtUsuario.sendKeys("usuarioValido"); // usuario REAL en BD
    }

    @Cuando("ingreso una contraseña válida")
    public void ingreso_una_contrasena_valida() {
        WebElement txtPass = driver.findElement(By.name("password"));
        txtPass.clear();
        txtPass.sendKeys("claveValida"); // password REAL en BD
    }

    @Cuando("ingreso un nombre de usuario inválido")
    public void ingreso_un_nombre_de_usuario_invalido() {
        WebElement txtUsuario = driver.findElement(By.name("nombreUsuario"));
        txtUsuario.clear();
        txtUsuario.sendKeys("usuarioInvalido");
    }

    @Cuando("ingreso una contraseña inválida")
    public void ingreso_una_contrasena_invalida() {
        WebElement txtPass = driver.findElement(By.name("password"));
        txtPass.clear();
        txtPass.sendKeys("claveMala");
    }

    @Cuando("presiono el botón Ingresar")
    public void presiono_el_boton_ingresar() {
        WebElement btnIngresar = driver.findElement(
            By.cssSelector("form[action='loginUsuario'] button[type='submit']")
        );
        btnIngresar.click();
    }

    @Entonces("debo ser redirigido al menú de usuario")
    public void debo_ser_redirigido_al_menu_de_usuario() {
        boolean hayError = driver.getPageSource().contains("style=\"color:red\"");
        assertTrue("Se mostró un error de login, las credenciales no parecen válidas", !hayError);
    }

    @Entonces("debo ver un mensaje de error de credenciales inválidas")
    public void debo_ver_un_mensaje_de_error_de_credenciales_invalidas() {
        WebElement mensajeError = driver.findElement(
            By.cssSelector("p[style*='color:red']")
        );
        assertTrue(mensajeError.isDisplayed());
    }
}
