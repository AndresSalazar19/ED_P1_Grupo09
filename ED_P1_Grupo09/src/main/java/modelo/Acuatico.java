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
public class Acuatico extends Vehiculo{
    private String tipoacua;

    public Acuatico(int id, String kilometraje, String modelo, String ciudad, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int capacidad,
                 DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista,
                 Usuario vendedor,String tipoacua) {
        super(id,kilometraje, modelo, ciudad, precio, year, imagenes, accidentes, capacidad,
              detallesExt, detallesInt, lista, vendedor);
        
        this.tipoacua = tipoacua;
    }

    public String getTipoacua() {
        return tipoacua;
    }

    public void setTipoacua(String tipoacua) {
        this.tipoacua = tipoacua;
    }
    

    
    @Override
    public String toString() {
        return "Acuatico{" +
                "id='" + id + '\'' +
                ", tipoacua=" + tipoacua +
                ", kilometraje='" + kilometraje + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", precio=" + precio +
                ", year='" + year + '\'' +
                ", imagenes=" + "img" +
                ", accidentes=" + accidentes +
                ", capacidad=" + capacidad +
                ", detallesExt=" + detallesExt +
                ", detallesInt=" + detallesInt +
                ", lista=" + lista +
                ", vendedor=" + vendedor +
                '}';
    }

}
