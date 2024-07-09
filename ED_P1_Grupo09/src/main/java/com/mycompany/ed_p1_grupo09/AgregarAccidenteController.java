package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Vehiculo;
import modelo.Mantenimiento;
import modelo.Accidente;
import tda.LinkedList;

public class AgregarAccidenteController implements Initializable {

    @FXML
    private DatePicker acciDP;

    @FXML
    private TextField descripcionAcciTF;

    @FXML
    private TextField partesAfectadasTF;

    @FXML
    private TextField mantenimientoTF;
    @FXML
    private Button volverDAcci;

    private Vehiculo vehiculo; // Vehículo actual en el cual se añadirán los mantenimientos
    private LinkedList<String> mantenimientoList = new LinkedList<>(); // Lista para ir almacenando los mantenimientos

    @FXML
    public String getMantenimiento() {
        return mantenimientoTF.getText();
    }

    @FXML
    private void getAcciFecha() {
        LocalDate fechaAccidente = acciDP.getValue();
        System.out.println("Fecha del accidente: " + fechaAccidente);
    }

    @FXML
    private void getAcciDescripcion() {
        String descripcion = descripcionAcciTF.getText();
        System.out.println("Descripción del accidente: " + descripcion);
    }

    @FXML
    private void getPartesAfectadas() {
        String partesAfectadas = partesAfectadasTF.getText();
        System.out.println("Partes afectadas: " + partesAfectadas);
    }

    @FXML
    private void addMantenimiento() {
        String mantenimiento = mantenimientoTF.getText();
        if (mantenimiento != null && !mantenimiento.isEmpty()) {
            mantenimientoList.addFirst(mantenimiento); // Añadir al inicio para mantener LIFO
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
            String descripcionAccidente = descripcionAcciTF.getText();
            String partesAfectadas = partesAfectadasTF.getText();
            LinkedList<Mantenimiento> listaMantenimiento = new LinkedList<>();

            while (!mantenimientoList.isEmpty()) {
                String descripcionMantenimiento = mantenimientoList.removeFirst(); // Obtener y eliminar del inicio
                Mantenimiento mantenimiento = new Mantenimiento(descripcionMantenimiento, "Tipo de mantenimiento");
                listaMantenimiento.addLast(mantenimiento); // Añadir al final para mantener el orden LIFO original
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
    private void volverDAcci() {
        Stage stage = (Stage) volverDAcci.getScene().getWindow();
        stage.close();
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización del vehículo u otros componentes si es necesario
    }
}
