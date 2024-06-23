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

 public Pesado(String kilometraje, String modelo, String ciudadv, double precio, String year,
              CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, String id, int capacidad,
              DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista,
              Usuario vendedor, double pesoMax, double pesoMin) {
    super(kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, id, capacidad,
          detallesExt, detallesInt, lista);
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
