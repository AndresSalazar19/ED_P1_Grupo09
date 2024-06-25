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

    public Acuatico(int kilometraje, String modelo, String ciudadv, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, String id, int capacidad,
                 DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista,
                 Usuario vendedor,String tipoacua) {
        super(kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, id, capacidad,
              detallesExt, detallesInt, lista);
        
        this.tipoacua = tipoacua;
    }

    public String getTipoacua() {
        return tipoacua;
    }

    public void setTipoacua(String tipoacua) {
        this.tipoacua = tipoacua;
    }
    
}
