package com.platinum.model;

public class Ejecutivo {
    private int rutEjecutivo;
    private String nombre;
    private String departamento;

    public Ejecutivo() {
    }

    public Ejecutivo(int rutEjecutivo, String nombre, String departamento) {
        this.rutEjecutivo = rutEjecutivo;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public int getRutEjecutivo() {
        return rutEjecutivo;
    }

    public void setRutEjecutivo(int rutEjecutivo) {
        this.rutEjecutivo = rutEjecutivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
