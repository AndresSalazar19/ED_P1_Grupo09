/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import tda.*;

/**
 *
 * @author Ayman El Salous Mnz
 */
public class Accidente {
    private final int id;
    private String descripcion;
    private String parteafec;
    private String accifecha;
    private LinkedList<Mantenimiento> listaMantenimiento;
    
    
   public Accidente(int id, String descripcion, String parteafec,String accifecha, LinkedList<Mantenimiento> listaMantenimiento){
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

    public String getParteafec() {
        return parteafec;
    }

    public void setParteafec(String parteafec) {
        this.parteafec = parteafec;
    }

    public String getAccifecha() {
        return accifecha;
    }

    public void setAccifecha(String accifecha) {
        this.accifecha = accifecha;
    }

    public List<Mantenimiento> getListaMantenimiento() {
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
