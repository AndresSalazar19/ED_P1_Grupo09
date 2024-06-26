/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import tda.*;

/**
 *
 * @author asala
 */
public class AplicacionVehiculo {
    public DoublyLinkedList<Vehiculo> vehiculos;
    
    public AplicacionVehiculo(DoublyLinkedList<Vehiculo> vehiculos){
        this.vehiculos = vehiculos;
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
        if(vehiculos.isEmpty()){
            vehiculos.addFirst(vehiculo);
        }else{
            vehiculos.addLast(vehiculo);
        }
    
    }
    public void editarVehiculo(int index, Vehiculo vehiculo){
       
        vehiculos.add(index, vehiculo);
    
    }
    public void eliminarVehiculo(int index){
        if(index > vehiculos.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }else{
            vehiculos.remove(index);
        }
    
    }
    public PriorityQueue<Vehiculo> OrdenarporPrecio(){
        PriorityQueue<Vehiculo> Porprecio = new PriorityQueue<>((Vehiculo v1, Vehiculo v2) -> {return Double.compare(v2.getPrecio(),v1.getPrecio());});
        if(vehiculos.isEmpty()){return new PriorityQueue<>();}
        int t = vehiculos.size();
        for(int i= 0; i<t; i++){
            Porprecio.offer(vehiculos.get(i));
        }
        return Porprecio;
    
    }
    public PriorityQueue<Vehiculo> OrdenarporKilometraje(){
        PriorityQueue<Vehiculo> PorKilometraje = new PriorityQueue<>((Vehiculo v1, Vehiculo v2) -> {return Integer.compare(v2.getKilometraje(), v1.getKilometraje());}); //falta el comparator
        int t = vehiculos.size();
        if(vehiculos.isEmpty()){return new PriorityQueue<>();}
        for(int i = 0; i<t; i++){
            PorKilometraje.offer(vehiculos.get(i));
        }
        return PorKilometraje;
    }
    public List<Vehiculo> filtrarPorMarcaModelo(String marca, String modelo){
        //por marca, buscar tal modelo
        DoublyLinkedList<Vehiculo> Pormarcas = new DoublyLinkedList<>();
        DoublyLinkedList<Vehiculo> marcaPormodelo = new DoublyLinkedList<>();
        int t = vehiculos.size();
        if(marca == null){
            return vehiculos;
        }else{
            for(int i =0; i<t; i++){
                if(vehiculos.get(i).getDetalles_Ext().getMarca().equals(marca)){
                    Pormarcas.addLast(vehiculos.get(i));
                }
            }
        }
        if(modelo == null){
            return Pormarcas;
        }else{
            for(Vehiculo v: Pormarcas){
                if(v.getModelo().equals(modelo)){
                    marcaPormodelo.addLast(v);
                }
            }
            return marcaPormodelo;  
        }
    }
    public PriorityQueue<Vehiculo> filtrarPorRangoPrecio(double minPrecio, double maxPrecio){
        PriorityQueue<Vehiculo> rangoPrecio = new PriorityQueue<>((Vehiculo v1, Vehiculo v2) -> {return Double.compare(v2.getPrecio(),v1.getPrecio());});
        int t = vehiculos.size();
        for(int i =0; i<t;i++){
            if(vehiculos.get(i).getPrecio() >= minPrecio && vehiculos.get(i).getPrecio() <= maxPrecio){
                rangoPrecio.offer(vehiculos.get(i));
            }
        }
        return rangoPrecio;
    
    }
    public PriorityQueue<Vehiculo> filtrarPorRangoKilometraje(int minKilometraje, int maxKilometraje){
        PriorityQueue<Vehiculo> rangoMetraje = new PriorityQueue<>((Vehiculo v1, Vehiculo v2) -> {return Integer.compare(v2.getKilometraje(), v1.getKilometraje());});
        int t = vehiculos.size();
        for(int i =0; i<t; i++){
            if(vehiculos.get(i).getKilometraje() >= minKilometraje && vehiculos.get(i).getKilometraje() <= maxKilometraje){
                rangoMetraje.offer(vehiculos.get(i));
            }
        }
        return rangoMetraje;
    }
}
