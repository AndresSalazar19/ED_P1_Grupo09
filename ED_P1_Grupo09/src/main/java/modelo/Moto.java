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

    public Moto(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudad, double precio, int year,
        CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
        DetallesVehiInt detallesInt, Usuario vendedor, boolean negociable, LinkedList<Mantenimiento> mantenimientos, TipoVehiculo tipoVehiculo, int cilindraje) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo);
        this.cilindraje = cilindraje;
    }



    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }
    


}
