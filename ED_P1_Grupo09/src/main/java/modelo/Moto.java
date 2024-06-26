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
public class Moto extends Vehiculo{
    private int cilindraje;

    public Moto(int id, String kilometraje, String modelo, String ciudad, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int capacidad,
                 DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista,
                 Usuario vendedor,int cilindraje) {
        super(id, kilometraje, modelo, ciudad, precio, year, imagenes, accidentes, capacidad,
              detallesExt, detallesInt, lista);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }
    
    @Override
    public String toString() {
        return "Moto{" +
                "id=" + getId() +
                ", kilometraje='" + getKilometraje() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", ciudad='" + getCiudad() + '\'' +
                ", precio=" + getPrecio() +
                ", year='" + getYear() + '\'' +
                ", cilindraje=" + cilindraje +
                '}';
    }


}
