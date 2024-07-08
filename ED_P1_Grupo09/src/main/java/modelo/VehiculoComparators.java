/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Comparator;

/**
 *
 * @author asala
 */

public class VehiculoComparators {
    public static Comparator<Vehiculo> compararPorPrecioAscendente = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Double.compare(v1.getPrecio(), v2.getPrecio());
        }
    };

    public static Comparator<Vehiculo> compararPorPrecioDescendente = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Double.compare(v2.getPrecio(), v1.getPrecio());
        }
    };

    public static Comparator<Vehiculo> compararPorAnoAscendente = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Integer.compare(v1.getYear(), v2.getYear());
        }
    };

    public static Comparator<Vehiculo> compararPorAnoDescendente = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Integer.compare(v2.getYear(), v1.getYear());
        }
    };
}