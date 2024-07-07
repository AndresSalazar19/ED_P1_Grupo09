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
    private String tipocarro;

   public Carro(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudad, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                 Usuario vendedor, DetallesVehiInt detallesInt, boolean negociable,LinkedList<Mantenimiento> mantenimientos, TipoVehiculo tipoVehiculo, String tipocarro) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo);
        this.tipocarro = tipocarro;
    }

    public String getTipocarro() {
        return tipocarro;
    }

    public void setTipocarro(String tipocarro) {
        this.tipocarro = tipocarro;
    }
    
 
}
