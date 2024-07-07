 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import tda.*;
import javafx.scene.image.Image;

/**
 *
 * @author LENOVO
 */
public class Aereo extends Vehiculo {
    private String tipoAeronave;
    private double pesoMaximoDespegue;
    private int rangoVuelo;
    
   public Aereo(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudad, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                 Usuario vendedor, DetallesVehiInt detallesInt, boolean negociable,LinkedList<Mantenimiento> mantenimientos, TipoVehiculo tipoVehiculo,
                 String tipoAeronave, double pesoMaximoDespegue, int rangoVuelo) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo);
        this.tipoAeronave = tipoAeronave;
        this.pesoMaximoDespegue = pesoMaximoDespegue;
        this.rangoVuelo = rangoVuelo;
    }
   
    public String getTipoAeronave() {
        return tipoAeronave;
    }  

    public void setTipoAeronave(String tipoAeronave) {
        this.tipoAeronave = tipoAeronave;
    }

    public double getPesoMaximoDespegue() {
        return pesoMaximoDespegue;
    }

    public void setPesoMaximoDespegue(double pesoMaximoDespegue) {
        this.pesoMaximoDespegue = pesoMaximoDespegue;
    }

    public int getRangoVuelo() {
        return rangoVuelo;
    }

    public void setRangoVuelo(int rangoVuelo) {
        this.rangoVuelo = rangoVuelo;
    }    

    @Override
    public String toString() {
        return "Aereo{" +
                ", modelo='" + getModelo() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                ", marca='" + getMarca() + '\'' +
                "tipoAeronave='" + tipoAeronave + '\'' +
                ", pesoMaximoDespegue=" + pesoMaximoDespegue +
                ", rangoVuelo=" + rangoVuelo +
                ", kilometraje=" + getKilometraje() +
                ", estado=" + getEstado() +
                ", ciudad='" + getCiudad() + '\'' +
                ", precio=" + getPrecio() +
                ", year='" + getYear() + '\'' +
                ", accidentes=" + getAccidentes() +
                ", id=" + getId() +
                ", capacidad=" + getCapacidad() +
                ", vendedor=" + getVendedor().getId() +
                ", detallesInt=" + getDetallesInt().toString() +
                ", negociable=" + isNegociable() +
                ", tipoVehiculo=" + getTipoVehiculo() +
                '}';
    }


}

