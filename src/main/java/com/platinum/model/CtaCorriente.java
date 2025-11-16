package com.platinum.model;

public class CtaCorriente {

    private int idCuenta;
    private int rutCliente;
    private double monto;
    private String ejecutivo;  // 👈 CAMBIO IMPORTANTE (antes int)

    // ====== CONSTRUCTOR COMPLETO ======
    public CtaCorriente(int idCuenta, int rutCliente, double monto, String ejecutivo) {
        this.idCuenta = idCuenta;
        this.rutCliente = rutCliente;
        this.monto = monto;
        this.ejecutivo = ejecutivo;
    }

    // ====== CONSTRUCTOR PARA INSERTAR (sin idCuenta) ======
    public CtaCorriente(int rutCliente, double monto, String ejecutivo) {
        this.rutCliente = rutCliente;
        this.monto = monto;
        this.ejecutivo = ejecutivo;
    }

    // ====== GETTERS & SETTERS ======
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(int rutCliente) {
        this.rutCliente = rutCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEjecutivo() {
        return ejecutivo;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }
}
