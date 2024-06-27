/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



import tda.*;
import javafx.scene.image.Image;
/**
 *
 * @author Ayman El Salous Mnz
 */
public class Vehiculo {
    public int id;
    public String kilometraje;
    protected String modelo;
    protected String ciudad;
    protected double precio;
    protected String year;
    protected CircularDoublyLinkedList<Image> Imagenes;
    protected LinkedList<Accidente> accidentes;
    protected int capacidad;
    protected DetallesVehiExt detallesExt;
    protected DetallesVehiInt detallesInt;
    protected LinkedList<Proceso> lista;
    public Usuario vendedor;

    

    public Vehiculo(int id, String kilometraje, String modelo, String ciudad, double precio, String year, CircularDoublyLinkedList<Image> Imagenes, LinkedList<Accidente> accidentes, int capacidad, DetallesVehiExt detallesExt, DetallesVehiInt detallesInt, LinkedList<Proceso> lista, Usuario vendedor) {
        this.id = id;
        this.kilometraje = kilometraje;
        this.modelo = modelo;
        this.ciudad = ciudad;
        this.precio = precio;
        this.year = year;
        this.Imagenes = Imagenes;
        this.accidentes = accidentes;
        this.capacidad = capacidad;
        this.detallesExt = detallesExt;
        this.detallesInt = detallesInt;
        this.lista = lista;
        this.vendedor = vendedor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Image> getImagenes() {
        return Imagenes;
    }

    public void setImagenes(CircularDoublyLinkedList<Image> Imagenes) {
        this.Imagenes = Imagenes;
    }

    public List<Accidente> getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(LinkedList<Accidente> accidentes) {
        this.accidentes = accidentes;
    }


    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public DetallesVehiExt getDetallesExt() {
        return detallesExt;
    }

    public void setDetallesExt(DetallesVehiExt detalles_Ext) {
        this.detallesExt = detalles_Ext;
    }

    public DetallesVehiInt getDetallesInt() {
        return detallesInt;
    }

    public void setDetallesInt(DetallesVehiInt detallesInt) {
        this.detallesInt = detallesInt;
    }

    public List<Proceso> getLista() {
        return lista;
    }

    public void setLista(LinkedList<Proceso> lista) {
        this.lista = lista;
    }
    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
    

   public Image getImage(){
        CircularDoublyLinkedList<Image> imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes/vehiculos/" + String.valueOf(id));
        return imagenes.get(0);
   }
}
