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
public class Pesado extends Vehiculo {
    private double pesoMax;
    private double pesoMin;

    public Pesado(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudad, double precio, int year,
                  CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                  Usuario vendedor, DetallesVehiInt detallesInt, boolean negociable, LinkedList<Mantenimiento> mantenimientos, TipoVehiculo tipoVehiculo,
                  double pesoMax, double pesoMin) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo);
        this.pesoMax = pesoMax;
        this.pesoMin = pesoMin;
    }


    public double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public double getPesoMin() {
        return pesoMin;
    }

    public void setPesoMin(double pesoMin) {
        this.pesoMin = pesoMin;
    }


}
