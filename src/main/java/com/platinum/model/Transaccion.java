package com.platinum.model;

public class Transaccion {

    private int idTransaccion;
    private int rutCliente;
    private int rutDueno;
    private int idCuenta;
    private double montoTransferencia;
    private String cuentaTransferencia; // OJO: String
    private String tipoCuenta;
    private String bancoDestino;
    private String tipoCuentaDestino;

    // Constructor completo
    public Transaccion(int idTransaccion,
                       int rutCliente,
                       int rutDueno,
                       int idCuenta,
                       double montoTransferencia,
                       String cuentaTransferencia,
                       String tipoCuenta,
                       String bancoDestino,
                       String tipoCuentaDestino) {

        this.idTransaccion = idTransaccion;
        this.rutCliente = rutCliente;
        this.rutDueno = rutDueno;
        this.idCuenta = idCuenta;
        this.montoTransferencia = montoTransferencia;
        this.cuentaTransferencia = cuentaTransferencia;
        this.tipoCuenta = tipoCuenta;
        this.bancoDestino = bancoDestino;
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    // Constructor sin idTransaccion (el que usan los servlets)
    public Transaccion(int rutCliente,
                       int rutDueno,
                       int idCuenta,
                       double montoTransferencia,
                       String cuentaTransferencia,
                       String tipoCuenta,
                       String bancoDestino,
                       String tipoCuentaDestino) {

        this(0, rutCliente, rutDueno, idCuenta,
             montoTransferencia, cuentaTransferencia,
             tipoCuenta, bancoDestino, tipoCuentaDestino);
    }

    public int getIdTransaccion() { return idTransaccion; }
    public int getRutCliente() { return rutCliente; }
    public int getRutDueno() { return rutDueno; }
    public int getIdCuenta() { return idCuenta; }
    public double getMontoTransferencia() { return montoTransferencia; }
    public String getCuentaTransferencia() { return cuentaTransferencia; }
    public String getTipoCuenta() { return tipoCuenta; }
    public String getBancoDestino() { return bancoDestino; }
    public String getTipoCuentaDestino() { return tipoCuentaDestino; }

    public void setIdTransaccion(int idTransaccion) { this.idTransaccion = idTransaccion; }
    public void setRutCliente(int rutCliente) { this.rutCliente = rutCliente; }
    public void setRutDueno(int rutDueno) { this.rutDueno = rutDueno; }
    public void setIdCuenta(int idCuenta) { this.idCuenta = idCuenta; }
    public void setMontoTransferencia(double montoTransferencia) { this.montoTransferencia = montoTransferencia; }
    public void setCuentaTransferencia(String cuentaTransferencia) { this.cuentaTransferencia = cuentaTransferencia; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    public void setBancoDestino(String bancoDestino) { this.bancoDestino = bancoDestino; }
    public void setTipoCuentaDestino(String tipoCuentaDestino) { this.tipoCuentaDestino = tipoCuentaDestino; }
}
