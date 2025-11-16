package com.platinum.model;

public class Usuario {

    private int id;
    private String nombreUsuario;
    private String password;
    private int rutCliente;  // FK a persona.rut

    // Constructor completo
    public Usuario(int id, String nombreUsuario, String password, int rutCliente) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rutCliente = rutCliente;
    }

    // Constructor para registro (sin id)
    public Usuario(String nombreUsuario, String password, int rutCliente) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rutCliente = rutCliente;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(int rutCliente) {
        this.rutCliente = rutCliente;
    }
}
