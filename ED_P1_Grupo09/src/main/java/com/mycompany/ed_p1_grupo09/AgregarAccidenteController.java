package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayDeque;
import tda.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Vehiculo;
import modelo.Mantenimiento;
import modelo.Accidente;

public class AgregarAccidenteController implements Initializable {

    @FXML
    private DatePicker acciDP;

    @FXML
    private TextField descripcionAcciLabel;

    @FXML
    private TextField partesAfectadasLabel;

    @FXML
    private TextField mantenimientoTF;

    private Vehiculo vehiculo; // Vehículo actual en el cual se añadirán los mantenimientos
    private ArrayDeque<String> mantenimientoDeque = new ArrayDeque<>(); // Cola doble para mantener los mantenimientos
    
    
    @FXML
    public String getMantenimiento(){
        return mantenimientoTF.getText();
    }
    @FXML
    private void getAcciFecha() {
        LocalDate fechaAccidente = acciDP.getValue();
        System.out.println("Fecha del accidente: " + fechaAccidente);
    }

    @FXML
    private void getAcciDescripcion() {
        String descripcion = descripcionAcciLabel.getText();
        System.out.println("Descripción del accidente: " + descripcion);
    }

    @FXML
    private void getPartesAfectadas() {
        String partesAfectadas = partesAfectadasLabel.getText();
        System.out.println("Partes afectadas: " + partesAfectadas);
    }

    @FXML
    private void addMantenimiento() {
        String mantenimiento = mantenimientoTF.getText();
        if (mantenimiento != null && !mantenimiento.isEmpty()) {
            mantenimientoDeque.add(mantenimiento); // Añadir a la cola doble
            mantenimientoTF.clear(); // Limpiar el campo después de agregar
            System.out.println("Mantenimiento añadido: " + mantenimiento);
        } else {
            System.out.println("Error: Descripción de mantenimiento vacía.");
        }
    }

    @FXML
    private void guardarAccidente() {
        if (vehiculo != null) {
            LocalDate fechaAccidente = acciDP.getValue();
            String descripcionAccidente = descripcionAcciLabel.getText();
            String partesAfectadas = partesAfectadasLabel.getText();
            LinkedList<Mantenimiento> listaMantenimiento = new LinkedList<>();

            while (!mantenimientoDeque.isEmpty()) {
                String descripcionMantenimiento = mantenimientoDeque.poll();
                Mantenimiento mantenimiento = new Mantenimiento(descripcionMantenimiento, "Tipo de mantenimiento");
                listaMantenimiento.addFirst(mantenimiento);
            }

            if (fechaAccidente != null && descripcionAccidente != null && !descripcionAccidente.isEmpty() &&
                partesAfectadas != null && !partesAfectadas.isEmpty()) {

                Accidente accidente = new Accidente(fechaAccidente, descripcionAccidente, partesAfectadas, listaMantenimiento);
                vehiculo.getAccidentes().addFirst(accidente);

                System.out.println("Accidente añadido: " + accidente);
                System.out.println("Historial de Mantenimiento: " + listaMantenimiento);
            } else {
                System.out.println("Error: Datos del accidente incompletos.");
            }
        } else {
            System.out.println("Error: Vehículo no inicializado.");
        }
    }

    @FXML
    private void volverDAcci() throws IOException {
        App.setRoot("añadirVehiculo");
    }
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización del vehículo u otros componentes si es necesario
    }
}
