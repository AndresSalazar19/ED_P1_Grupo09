/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import tda.*;


/**
 *
 * @author asala
 */
public class Usuario {
    int id;
    String nombre;
    String correo;
    String telefono;
    String contrasena;
    
    DoublyLinkedList<Vehiculo> vehiculosFavoritos;
    DoublyLinkedList<Vehiculo> misVehiculos;
    
    public Usuario(int id, String nombre, String correo, String telefono, String contrasena){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.vehiculosFavoritos = new DoublyLinkedList<>();
        this.misVehiculos = new DoublyLinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
     public String getContrasena() {
        return contrasena;
    }
      
    public DoublyLinkedList<Vehiculo> getVehiculosFavoritos() {
        return vehiculosFavoritos;
    }

    public void setVehiculosFavoritos(DoublyLinkedList<Vehiculo> vehiculosFavoritos) {
        this.vehiculosFavoritos = vehiculosFavoritos;
    }

    public DoublyLinkedList<Vehiculo> getMisVehiculos() {
        return misVehiculos;
    }

    public void setMisVehiculos(DoublyLinkedList<Vehiculo> misVehiculos) {
        this.misVehiculos = misVehiculos;
    }


    
    public void agregarVehiculoAFavoritos(Vehiculo vehiculo){
        vehiculosFavoritos.addFirst(vehiculo);  //Similitud a una pila usando LinkedList
    }
    
    public void mostrarFavoritos(){  
       
    }
    
    public int getId(){
        return id;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{")
            .append("nombre='").append(nombre).append('\'')
            .append(", correo='").append(correo).append('\'')
            .append(", telefono='").append(telefono).append('\'')
            .append(", contrasena='").append(contrasena).append('\'')
            .append(", vehiculosFavoritos=").append(vehiculosFavoritos)
            .append(", misVehiculos=").append(misVehiculos)
            .append('}');
        return sb.toString();
}


}
