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

    public Moto(int kilometraje, String modelo, String ciudadv, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, String id, int capacidad,
                 DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista,
                 Usuario vendedor,int cilindraje) {
        super(kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, id, capacidad,
              detallesExt, detallesInt, lista);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }
    
}
