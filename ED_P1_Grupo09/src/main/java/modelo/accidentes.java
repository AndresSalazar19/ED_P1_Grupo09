/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.List;

/**
 *
 * @author Ayman El Salous Mnz
 */
public class accidentes {
    private String descripcion;
    private String parteafec;
    private String accifecha;
    private List<Procesos> lista;
    
    
   public accidentes(String descripcion, String parteafec,String accifecha, List<Procesos> lista){
       this.descripcion= descripcion;
       this.parteafec = parteafec;
       this.accifecha = accifecha;
       this.lista =lista;
       
       
   
   
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

    public List<Procesos> getLista() {
        return lista;
    }

    public void setLista(List<Procesos> lista) {
        this.lista = lista;
    }
   
   
   
}
