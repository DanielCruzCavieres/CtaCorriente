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
        // Para Jenkins sin interfaz gráfica:
        // options.addArguments("--headless=new");
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
        txtUsuario.sendKeys("usuarioValido"); // 👈 debe existir en tu BD
    }

    @Cuando("ingreso una contraseña válida")
    public void ingreso_una_contrasena_valida() {
        WebElement txtPass = driver.findElement(By.name("password"));
        txtPass.clear();
        txtPass.sendKeys("claveValida"); // 👈 password correcta
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
        // Único botón submit del form de login
        WebElement btnIngresar = driver.findElement(
            By.cssSelector("form[action='loginUsuario'] button[type='submit']")
        );
        btnIngresar.click();
    }

    @Entonces("debo ser redirigido al menú de usuario")
    public void debo_ser_redirigido_al_menu_de_usuario() {
        // Asumimos que el login correcto redirige a menuUsuario.jsp
        assertTrue(driver.getCurrentUrl().contains("menuUsuario.jsp"));
    }

    @Entonces("debo ver un mensaje de error de credenciales inválidas")
    public void debo_ver_un_mensaje_de_error_de_credenciales_invalidas() {
        // En loginUsuario.jsp el error es un <p style="color:red">...</p>
        WebElement mensajeError = driver.findElement(
            By.cssSelector("p[style*='color:red']")
        );
        assertTrue(mensajeError.isDisplayed());
    }


    // =========================
    // 4.3 Registro hora veterinaria
    // =========================

    @Dado("que estoy logeado como usuario válido")
    public void que_estoy_logeado_como_usuario_valido() {
        // Hacemos login rápido
        driver.get("http://localhost:8080/CtaCorriente/loginUsuario.jsp");
        WebElement txtUsuario = driver.findElement(By.name("username"));
        WebElement txtPass    = driver.findElement(By.name("password"));
        txtUsuario.sendKeys("usuarioValido");
        txtPass.sendKeys("claveValida");
        driver.findElement(By.id("btnLogin")).click();
    }

    @Dado("estoy en la página de registro de hora veterinaria")
    public void estoy_en_la_pagina_de_registro_de_hora_veterinaria() {
        driver.get("http://localhost:8080/CtaCorriente/registroHoraVeterinaria.jsp");
    }

    @Cuando("selecciono una mascota válida")
    public void selecciono_una_mascota_valida() {
        // ej: combo <select id="mascota">
        WebElement cboMascota = driver.findElement(By.id("mascota"));
        cboMascota.sendKeys("Firulais");
    }

    @Cuando("selecciono una fecha y hora válidas")
    public void selecciono_una_fecha_y_hora_validas() {
        WebElement txtFecha = driver.findElement(By.id("fecha"));
        WebElement txtHora  = driver.findElement(By.id("hora"));
        txtFecha.sendKeys("20-11-2025");
        txtHora.sendKeys("10:30");
    }

    @Cuando("presiono el botón Registrar Hora")
    public void presiono_el_boton_registrar_hora() {
        WebElement btnRegistrar = driver.findElement(By.id("btnRegistrarHora"));
        btnRegistrar.click();
    }

    @Entonces("debo ver un mensaje de confirmación de hora registrada")
    public void debo_ver_un_mensaje_de_confirmacion_de_hora_registrada() {
        WebElement lblConfirmacion = driver.findElement(By.id("mensajeExitoHora"));
        assertTrue(lblConfirmacion.getText().contains("Hora registrada"));
    }
}
