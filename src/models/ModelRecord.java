/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author mrles
 */
public class ModelRecord {
    private int idregistro;
    private String placa;
    private int cedula;
    private String hora_ingreso;
    private String hora_salida;
    private String estado;

    public ModelRecord() {
    }

    public ModelRecord(int idregistro, String placa, int cedula, String hora_ingreso, String hora_salida, String estado) {
        this.idregistro = idregistro;
        this.placa = placa;
        this.cedula = cedula;
        this.hora_ingreso = hora_ingreso;
        this.hora_salida = hora_salida;
        this.estado = estado;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
