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

    public Carro( int id, String kilometraje, String modelo, String ciudad, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int capacidad,
                 DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista,
                 Usuario vendedor,String tipocarro) {
        super(id, kilometraje, modelo, ciudad, precio, year, imagenes, accidentes, capacidad,
              detallesExt, detallesInt, lista, vendedor);
        this.tipocarro = tipocarro;
    }

    public String getTipocarro() {
        return tipocarro;
    }

    public void setTipocarro(String tipocarro) {
        this.tipocarro = tipocarro;
    }
    
    @Override
    public String toString() {
        return "Carro{"
                + "id=" + getId()
                + ", kilometraje='" + getKilometraje() + '\''
                + ", modelo='" + getModelo() + '\''
                + ", ciudad='" + getCiudad() + '\''
                + ", precio=" + getPrecio()
                + ", year='" + getYear() + '\''
                + ", tipocarro='" + tipocarro + '\''
                + ", imagenes=" + getImagenes()
                + ", accidentes=" + getAccidentes()
                + ", capacidad=" + getCapacidad()
                + ", detallesExt=" + getDetallesExt()
                + ", detallesInt=" + getDetallesInt()
                + ", lista=" + getLista()
                + ", vendedor=" + getVendedor()
                + '}';
    }

}
