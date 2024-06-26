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
public class Aereo extends Vehiculo {
    private String tipoAeronave;
    private double pesoMaximoDespegue;
    private int rangoVuelo;
    
    public Aereo(int id, String kilometraje, String modelo, String ciudadv, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int capacidad,
                 DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista,
                 Usuario vendedor, String tipoAeronave, double pesoMaximoDespegue, int rangoVuelo) {
        super(id, kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, capacidad,
              detallesExt, detallesInt, lista);
        this.tipoAeronave = tipoAeronave;
        this.pesoMaximoDespegue = pesoMaximoDespegue;
        this.rangoVuelo = rangoVuelo;
    }

    public String getTipoAeronave() {
        return tipoAeronave;
    }  

    public void setTipoAeronave(String tipoAeronave) {
        this.tipoAeronave = tipoAeronave;
    }

    public double getPesoMaximoDespegue() {
        return pesoMaximoDespegue;
    }

    public void setPesoMaximoDespegue(double pesoMaximoDespegue) {
        this.pesoMaximoDespegue = pesoMaximoDespegue;
    }

    public int getRangoVuelo() {
        return rangoVuelo;
    }

    public void setRangoVuelo(int rangoVuelo) {
        this.rangoVuelo = rangoVuelo;
    }    
}
