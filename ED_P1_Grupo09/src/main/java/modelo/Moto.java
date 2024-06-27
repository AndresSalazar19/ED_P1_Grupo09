    package modelo;

import tda.*;
import javafx.scene.image.Image;

/**
 *
 * @autor andres b
 */
public class Moto extends Vehiculo {
    private int cilindraje;

    public Moto(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudadVehi, double precio, String year,
                CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                Usuario vendedor, DetallesVehiInt detallesInt, int cilindraje, boolean negociable) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudadVehi, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }
}