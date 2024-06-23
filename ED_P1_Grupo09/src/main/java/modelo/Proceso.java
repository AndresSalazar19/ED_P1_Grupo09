/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author asala
 */
public class Proceso {
    private String descripcion;
    private String tipo_proceso;

    public Proceso(String descripcion, String tipo_proceso) {
        this.descripcion = descripcion;
        this.tipo_proceso = tipo_proceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoProceso() {
        return tipo_proceso;
    }

    public void setTipoProceso(String tipo_proceso) {
        this.tipo_proceso = tipo_proceso;
    }
}
