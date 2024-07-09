package modelo;

import java.time.LocalDate;
import tda.*;

public class Accidente {
    private String descripcion;
    private String parteafec;
    private LocalDate accifecha; 
    private LinkedList<Mantenimiento> mantenimiento;

    public Accidente(LocalDate accifecha, String descripcion, String parteafec, LinkedList<Mantenimiento> mantenimiento) {
        this.descripcion = descripcion;
        this.parteafec = parteafec;
        this.accifecha = accifecha;
        this.mantenimiento = new LinkedList<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParteafec() {
        return parteafec;
    }

    public void setParteafec(String parteafec) {
        this.parteafec = parteafec;
    }

    public LocalDate getAccifecha() {
        return accifecha;
    }

    public void setAccifecha(LocalDate accifecha) {
        this.accifecha = accifecha;
    }

    public LinkedList<Mantenimiento> getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(LinkedList<Mantenimiento> mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
    
     public void addMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimiento.addFirst(mantenimiento);
    }   
}
