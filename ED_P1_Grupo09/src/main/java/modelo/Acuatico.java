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

   public Acuatico(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudadVehi, double precio, int year,
                    CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                    Usuario vendedor, DetallesVehiInt detallesInt, boolean negociable,LinkedList<Mantenimiento> mantenimientos, TipoVehiculo tipoVehiculo,String tipoacua) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudadVehi, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo);
        this.tipoacua = tipoacua;
    }

    public String getTipoacua() {
        return tipoacua;
    }

    public void setTipoacua(String tipoacua) {
        this.tipoacua = tipoacua;
    }
    



}
