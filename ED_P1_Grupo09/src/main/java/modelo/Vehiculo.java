/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import javafx.scene.image.Image;
/**
 *
 * @author Ayman El Salous Mnz
 */
abstract class Vehiculo {
    protected String kilometraje;
    protected String modelo;
    protected String ciudadv;
    protected double precio;
    protected String year;
    protected List<Image> Imagenes;
    protected List<Accidente> accidentes;
    protected String id;
    protected int capacidad;
    protected DetallesVehiExt detalles_Ext;
    protected DetallesVehiInt detalles_Int;
    protected List<Proceso> lista;
    protected Usuario vendedor;

    

    public Vehiculo(String kilometraje, String modelo, String ciudadv, double precio, String year, List<Image> Imagenes, List<Accidente> accidentes, String id, int capacidad, DetallesVehiExt detalles_Ext, DetallesVehiInt detalles_Int, List<Proceso> lista) {
        this.kilometraje = kilometraje;
        this.modelo = modelo;
        this.ciudadv = ciudadv;
        this.precio = precio;
        this.year = year;
        this.Imagenes = Imagenes;
        this.accidentes = accidentes;
        this.id = id;
        this.capacidad = capacidad;
        this.detalles_Ext = detalles_Ext;
        this.detalles_Int = detalles_Int;
        this.lista = lista;
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

    public String getCiudadv() {
        return ciudadv;
    }

    public void setCiudadv(String ciudadv) {
        this.ciudadv = ciudadv;
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

    public void setImagenes(List<Image> Imagenes) {
        this.Imagenes = Imagenes;
    }

    public List<Accidente> getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(List<Accidente> accidentes) {
        this.accidentes = accidentes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public DetallesVehiExt getDetalles_Ext() {
        return detalles_Ext;
    }

    public void setDetalles_Ext(DetallesVehiExt detalles_Ext) {
        this.detalles_Ext = detalles_Ext;
    }

    public DetallesVehiInt getDetalles_Int() {
        return detalles_Int;
    }

    public void setDetalles_Int(DetallesVehiInt detalles_Int) {
        this.detalles_Int = detalles_Int;
    }

    public List<Proceso> getLista() {
        return lista;
    }

    public void setLista(List<Proceso> lista) {
        this.lista = lista;
    }
    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
    
}
