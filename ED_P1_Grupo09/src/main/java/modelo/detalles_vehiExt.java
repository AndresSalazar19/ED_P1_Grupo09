/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author asala
 */
public class Detalles_vehiExt {
    private String descripcion;
    private String marca;
    private boolean usado;
    private boolean negociable;

    public Detalles_vehiExt(String descripcion, String marca, boolean usado, boolean negociable) {
        this.descripcion = descripcion;
        this.marca = marca;
        this.usado = usado;
        this.negociable = negociable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean getUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public boolean getNegociable() {
        return negociable;
    }

    public void setNegociable(boolean negociable) {
        this.negociable = negociable;
    }
}
