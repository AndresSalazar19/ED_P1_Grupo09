/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author asala
 */
public class Usuario {
    String nombre;
    String email;
    String telefono;
    LinkedList<Vehiculo> vehiculosFavoritos;
    List<Vehiculo> publicadosPorUsuario;
    List<Contacto> contactos;
    
    public Usuario(String nombre, String email, String telefono){
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.vehiculosFavoritos = new ArrayList<>();
        this.publicadosPorUsuario = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LinkedList<Vehiculo> getVehiculosFavoritos() {
        return vehiculosFavoritos;
    }

    public void setVehiculosFavoritos(LinkedList<Vehiculo> vehiculosFavoritos) {
        this.vehiculosFavoritos = vehiculosFavoritos;
    }

    public List<Vehiculo> getPublicadosPorUsuario() {
        return publicadosPorUsuario;
    }

    public void setPublicadosPorUsuario(List<Vehiculo> publicadosPorUsuario) {
        this.publicadosPorUsuario = publicadosPorUsuario;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
    
    public void agregarVehiculoAFavoritos(Vehiculo vehiculo){
        vehiculosFavoritos.addFirst(vehiculo);  //Similitud a una pila usando LinkedList
    }
    
    public void mostrarFavoritos(){  
       
    }
    
    public void agregarContacto(){
        
    }
    
    public void mostrarContacto(){
        
    }
}
