/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Ayman El Salous Mnz
 */
public class Carro extends Vehiculo {
    private String tipocarro;

    public Carro( String kilometraje, String modelo, String ciudadv, double precio, String year,
                 List<Image> imagenes, List<Accidente> accidentes, String id, int capacidad,
                 Detalles_vehiExt detallesExt, Detalles_vehiInt detallesInt, List<Proceso> lista,
                 Usuario vendedor,String tipocarro) {
        super(kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, id, capacidad,
              detallesExt, detallesInt, lista);
        this.tipocarro = tipocarro;
    }

    public String getTipocarro() {
        return tipocarro;
    }

    public void setTipocarro(String tipocarro) {
        this.tipocarro = tipocarro;
    }
    
    
}
