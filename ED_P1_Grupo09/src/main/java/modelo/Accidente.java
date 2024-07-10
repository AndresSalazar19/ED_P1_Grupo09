/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.time.LocalDate;
import tda.*;

/**
 *
 * @author Ayman El Salous Mnz
 */
public class Accidente {
    private final int id;
    private String descripcion;
    private String parteafec;
    private LocalDate accifecha;
    private LinkedList<Mantenimiento> listaMantenimiento;
    
    
   public Accidente(int id, String descripcion, String parteafec,LocalDate accifecha, LinkedList<Mantenimiento> listaMantenimiento){
       this.id= id;
       this.descripcion= descripcion;
       this.parteafec = parteafec;
       this.accifecha = accifecha;
       this.listaMantenimiento =listaMantenimiento;
   }

   public String getDescripcion(){
       return descripcion;
   
   }
   public void setDescripcion(String descripcion){
       this.descripcion = descripcion;
   }

    public String getParteAfectada() {
        return parteafec;
    }

    public void setParteAfectada(String parteafec) {
        this.parteafec = parteafec;
    }

    public LocalDate getFechaAccidente() {
        return accifecha;
    }

    public void setFechaAccidente(LocalDate accifecha) {
        this.accifecha = accifecha;
    }

    public LinkedList<Mantenimiento> getListaMantenimiento() {
        return listaMantenimiento;
    }

    public void setListaMantenimiento(LinkedList<Mantenimiento> listaMantenimiento) {
        this.listaMantenimiento = listaMantenimiento;
    }
   
    public int getId(){
       return id;
   }
   
    @Override
    public String toString() {
        return "Accidente{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", parteafec='" + parteafec + '\'' +
                ", accifecha='" + accifecha + '\'' +
                ", listaMantenimiento=" + listaMantenimiento +
                '}';
    }   


}