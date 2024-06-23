/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asala
 */
public class AplicacionVehiculo {
    private List<DetallesVehiExt> vehiculos;

    public AplicacionVehiculo() {
        this.vehiculos = new ArrayList<>();
    }

    public void agregarVehiculo(DetallesVehiExt vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    public void editarVehiculo(int index, DetallesVehiExt vehiculo) {
        if (index >= 0 && index < vehiculos.size()) {
            vehiculos.set(index, vehiculo);
        }
    }

    public void eliminarVehiculo(int index) {
        if (index >= 0 && index < vehiculos.size()) {
            vehiculos.remove(index);
        }
    }

    public List<DetallesVehiExt> ordenarPorPrecio() {
        // Implementación para ordenar por precio
        return null;
    }

    public List<DetallesVehiExt> ordenarPorKilometraje() {
        // Implementación para ordenar por kilometraje
        return null;
    }

    public List<DetallesVehiExt> filtrarPorMarcaModelo(String marca, String modelo) {
        // Implementación para filtrar por marca y modelo
        return null;
    }

    public List<DetallesVehiExt> filtrarPorRangoPrecio(double minPrecio, double maxPrecio) {
        // Implementación para filtrar por rango de precio
        return null;
    }

    public List<DetallesVehiExt> filtrarPorRangoKilometraje(int minKilometraje, int maxKilometraje) {
        // Implementación para filtrar por rango de kilometraje
        return null;
    }
}
