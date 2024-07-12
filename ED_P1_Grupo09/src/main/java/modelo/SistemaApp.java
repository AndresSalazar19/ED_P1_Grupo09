/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.IOException;
import tda.*;
/**
 *
 * @author asala
 */


public class SistemaApp {
    private static SistemaApp instance;
    private LinkedList<Vehiculo> vehiculos;
    private LinkedList<Usuario> usuarios;

    private SistemaApp() throws IOException {
        this.vehiculos = VehiculoManager.cargarVehiculos();
    }

    public static SistemaApp getInstance() throws IOException {
        if (instance == null) {
            instance = new SistemaApp();
        }
        return instance;
    }

    public LinkedList<Vehiculo> getVehiculos() throws IOException {
        if (vehiculos == null) {
            this.vehiculos = VehiculoManager.cargarVehiculos();
            setVehiculos(vehiculos);
        }
        return vehiculos;
    }

    public void setVehiculos(LinkedList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(LinkedList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void cargarVehiculosAUsuario(Usuario usuario) {
        DoublyLinkedList<Vehiculo> vehiculosUsuario = new DoublyLinkedList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getVendedor().getId() == usuario.getId()) {
                vehiculosUsuario.addFirst(vehiculo);
            }
        }
        usuario.setMisVehiculos(vehiculosUsuario);
    }

    public void guardarVehiculos() throws IOException {
        VehiculoManager.guardarVehiculos(vehiculos);
    }



    public void agregarVehiculo(Vehiculo vehiculo) throws IOException {
        vehiculos.addFirst(vehiculo);
        guardarVehiculos();
    }

    public void eliminarVehiculo(Vehiculo vehiculo) throws IOException {
        vehiculos.removeVehicle(vehiculo);
        guardarVehiculos();
    }


}
