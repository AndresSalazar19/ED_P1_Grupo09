package modelo;

import tda.*;
import javafx.scene.image.Image;

/**
 *
 * @author andres b
 */
public class Pesado extends Vehiculo {
    private double pesoMax;
    private double pesoMin;

    public Pesado(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudadVehi, double precio, String year,
                  CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                  Usuario vendedor, DetallesVehiInt detallesInt, double pesoMax, double pesoMin, boolean negociable) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudadVehi, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable);
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