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
    private String transmision;
    private TipoCombustible combustible;
    private String placa;
    private boolean climatizado;
    private String tipoMotor;

    public DetallesVehiInt(String traccion, String transmision, TipoCombustible combustible, String placa, boolean climatizado, String tipoMotor) {
        this.traccion = traccion;
        this.transmision = transmision;
        this.combustible = combustible;
        this.placa = placa;
        this.climatizado = climatizado;
        this.tipoMotor = tipoMotor;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public TipoCombustible getCombustible() {
        return combustible;
    }

    public void setCombustible(TipoCombustible combustible) {
        this.combustible = combustible;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isClimatizado() {
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
    
    @Override
    public String toString() {
        return "DetallesVehiInt{" +
                "traccion='" + traccion + '\'' +
                ", transmision='" + transmision + '\'' +
                ", combustible=" + combustible +
                ", placa='" + placa + '\'' +
                ", climatizado=" + climatizado +
                ", tipoMotor='" + tipoMotor + '\'' +
                '}';
    }

}
