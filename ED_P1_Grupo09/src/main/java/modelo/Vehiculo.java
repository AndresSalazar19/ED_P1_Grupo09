/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



import tda.*;
import javafx.scene.image.Image;
/**
 *
 * @author asala
 */
public abstract class Vehiculo {
    private int kilometraje;
    private String modelo;
    private String descripcion;
    private String marca;
    private Estado estado;
    private String ciudad;
    private double precio;
    private int year;
    private CircularDoublyLinkedList<Image> imagenes;
    private LinkedList<Accidente> accidentes;
    private int id;
    private int capacidad;
    private DetallesVehiInt detallesInt;
    private Usuario vendedor;
    private boolean negociable;
    private LinkedList<Mantenimiento> mantenimientos;
    private TipoVehiculo tipoVehiculo;

    
    public Vehiculo(int kilometraje, String modelo, String descripcion, String marca, Estado estado, String ciudad, double precio, int year,
                        CircularDoublyLinkedList<Image> imagenes, LinkedList<Accidente> accidentes, int id, int capacidad,
                        Usuario vendedor, DetallesVehiInt detallesInt, boolean negociable, LinkedList<Mantenimiento> mantenimientos, TipoVehiculo tipoVehiculo) {
            this.kilometraje = kilometraje;
            this.modelo = modelo;
            this.descripcion = descripcion;
            this.marca = marca;
            this.estado = estado;
            this.ciudad = ciudad;
            this.precio = precio;
            this.year = year;
            this.imagenes = imagenes;
            this.accidentes = accidentes;
            this.id = id;
            this.capacidad = capacidad;
            this.vendedor = vendedor;
            this.detallesInt = detallesInt;
            this.negociable = negociable;
            this.mantenimientos = mantenimientos;
            this.tipoVehiculo = tipoVehiculo;
        }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CircularDoublyLinkedList<Image> getImagenes() {
        return imagenes;
    }

    public void setImagenes(CircularDoublyLinkedList<Image> imagenes) {
        this.imagenes = imagenes;
    }

    public LinkedList<Accidente> getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(LinkedList<Accidente> accidentes) {
        this.accidentes = accidentes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public DetallesVehiInt getDetallesInt() {
        return detallesInt;
    }

    public void setDetallesInt(DetallesVehiInt detallesInt) {
        this.detallesInt = detallesInt;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
    
    public boolean isNegociable() {
        return negociable;
    }

    public void setNegociable(boolean negociable) {
        this.negociable = negociable;
    }
    
    public LinkedList<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }    
    
    public void setMantenimientos(LinkedList<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }    
    
    public TipoVehiculo getTipoVehiculo(){
        return tipoVehiculo;
    }
    
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo ;
    }
     
}