/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import tda.*;
import javafx.scene.image.Image;

/**
 *
 * @author Ayman El Salous Mnz
 */
public class Carro extends Vehiculo {
    private TipoCarro tipocarro;

   public Carro(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudad, double precio, int year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                 Usuario vendedor, DetallesVehiInt detallesInt, boolean negociable,LinkedList<Mantenimiento> mantenimientos, TipoVehiculo tipoVehiculo, TipoCarro tipocarro) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo);
        this.tipocarro = tipocarro;
    }

    public TipoCarro getTipoCarro() {
        return tipocarro;
    }

    public void setTipoCarro(TipoCarro tipocarro) {
        this.tipocarro = tipocarro;
    }
    
    @Override
    public String toString() {
        return "Carro{" +
                "tipocarro=" + tipocarro +
                ", kilometraje=" + getKilometraje() +
                ", modelo='" + getModelo() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", estado=" + getEstado() +
                ", ciudad='" + getCiudad() + '\'' +
                ", precio=" + getPrecio() +
                ", year=" + getYear() +
                ", imagenes=" + getImagenes() +
                ", accidentes=" + getAccidentes() +
                ", id=" + getId() +
                ", capacidad=" + getCapacidad() +
                ", vendedor=" + getVendedor() +
                ", detallesInt=" + getDetallesInt() +
                ", negociable=" + isNegociable() +
                ", mantenimientos=" + getMantenimientos() +
                ", tipoVehiculo=" + getTipoVehiculo() +
                '}';
    }

 
}
