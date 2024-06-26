/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author asala
 */
public class DetallesVehiInt {
    private String traccion;
    private String transmission;
    private TipoCombustible combustible;
    private String placade;
    private boolean climatizado;
    private String tipoMotor;

    public DetallesVehiInt(String traccion, String transmission, TipoCombustible combustible, String placade, boolean climatizado, String tipoMotor) {
        this.traccion = traccion;
        this.transmission = transmission;
        this.combustible = combustible;
        this.placade = placade;
        this.climatizado = climatizado;
        this.tipoMotor = tipoMotor;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public TipoCombustible getCombustible() {
        return combustible;
    }

    public void setCombustible(TipoCombustible combustible) {
        this.combustible = combustible;
    }

    public String getPlacade() {
        return placade;
    }

    public void setPlacade(String placade) {
        this.placade = placade;
    }

    public boolean getClimatizado() {
        return climatizado;
    }

    public void setClimatizado(boolean climatizado) {
        this.climatizado = climatizado;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }
}
