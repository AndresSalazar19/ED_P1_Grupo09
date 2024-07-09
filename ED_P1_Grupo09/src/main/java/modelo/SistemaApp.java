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
    private List<Vehiculo> vehiculos;
    
    private SistemaApp() throws IOException{
        this.vehiculos = VehiculoManager.cargarVehiculos();
    }
    
    public void cargarVehiculosAUsuario(Usuario usuario){
        DoublyLinkedList<Vehiculo> vehiculosUsuario = new DoublyLinkedList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.getVendedor().getId() == usuario.getId()){
                vehiculosUsuario.addFirst(vehiculo);
            }
        }
        System.out.println("EAS" + vehiculosUsuario.size());
        usuario.setMisVehiculos(vehiculosUsuario);
    }
    
    public static SistemaApp getInstance() throws IOException {
        if (instance == null) {
            instance = new SistemaApp();
        }
        return instance;
    }
    
    public List<Vehiculo> getVehiculos() throws IOException{
        if(vehiculos == null){
            this.vehiculos = VehiculoManager.cargarVehiculos();
            setVehiculos(vehiculos);
        }
        return vehiculos;
    }
    
        public void setVehiculos(List<Vehiculo> vehiculos){
        this.vehiculos = vehiculos;
    }
   
}
