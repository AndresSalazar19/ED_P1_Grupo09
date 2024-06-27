package modelo;

import tda.*;
import javafx.scene.image.Image;

/**
 *
 * @author andres b
 */
public class Aereo extends Vehiculo {
    private String tipoAeronave;
    private double pesoMaximoDespegue;
    private int rangoVuelo;

    public Aereo(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudadVehi, double precio, String year,
                 CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                 Usuario vendedor, DetallesVehiInt detallesInt, String tipoAeronave, double pesoMaximoDespegue, int rangoVuelo, boolean negociable) {
        super(kilometraje, modelo, descripcion, marca, estado, ciudadVehi, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable);
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