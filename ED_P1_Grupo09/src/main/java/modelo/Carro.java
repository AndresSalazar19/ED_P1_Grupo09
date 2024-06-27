package modelo;

import tda.*;
import javafx.scene.image.Image;

/**
 *
 * @author andres b
 */
public class Carro extends Vehiculo {
    private String tipocarro;

    public Carro(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudadVehi, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                 Usuario vendedor, DetallesVehiInt detallesInt, String tipocarro, boolean negociable) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudadVehi, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable);
        this.tipocarro = tipocarro;
    }

    public String getTipocarro() {
        return tipocarro;
    }

    public void setTipocarro(String tipocarro) {
        this.tipocarro = tipocarro;
    }
}