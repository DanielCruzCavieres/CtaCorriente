Feature: Pruebas de login y registro de hora médica veterinaria

  # 4.1 Ingreso de nombre de usuario con datos almacenados en BD
  Scenario: Login exitoso con usuario válido
    Given que estoy en la página de login de usuario
    When ingreso un nombre de usuario válido guardado en la BD
    And ingreso una contraseña válida
    And presiono el botón Ingresar
    Then debo ser redirigido al menú de usuario

  # 4.2 Ingreso de nombre de usuario y password con datos erróneos.
  Scenario: Login fallido con credenciales erróneas
    Given que estoy en la página de login de usuario
    When ingreso un nombre de usuario inválido
    And ingreso una contraseña inválida
    And presiono el botón Ingresar
    Then debo ver un mensaje de error de credenciales inválidas

  # 4.3 Registro de hora médica veterinaria
  Scenario: Registro exitoso de hora médica veterinaria
    Given que estoy logeado como usuario válido
    And estoy en la página de registro de hora veterinaria
    When selecciono una mascota válida
    And selecciono una fecha y hora válidas
    And presiono el botón Registrar Hora
    Then debo ver un mensaje de confirmación de hora registrada
