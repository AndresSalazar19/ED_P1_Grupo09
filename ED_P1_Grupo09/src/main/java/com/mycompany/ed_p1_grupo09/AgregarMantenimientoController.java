/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import modelo.*;
import tda.*;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AgregarMantenimientoController implements Initializable {

    @FXML
    private Label tituloLabel;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private TextField tipoMantenimientoTextField;

    private AñadirVehiculoController mainController;
    private AgregarAccidenteController accidenteController;
    private Mantenimiento mantenimientoActual;
    private Mantenimiento procesoActual;

    @FXML
    private void guardar() {
        if (accidenteController == null) {
            if (descripcionTextField.getText().isEmpty() || tipoMantenimientoTextField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Todos los campos deben estar llenos.");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Guardado");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro que desea guardar este mantenimiento?");

            ButtonType buttonTypeYes = new ButtonType("Sí");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                String descripcion = descripcionTextField.getText();
                String tipoMantenimiento = tipoMantenimientoTextField.getText();

                if (mantenimientoActual == null) {
                    Mantenimiento nuevoMantenimiento = new Mantenimiento(descripcion, tipoMantenimiento);
                    mainController.agregarMantenimiento(nuevoMantenimiento);
                } else {
                    mantenimientoActual.setDescripcion(descripcion);
                    mantenimientoActual.setTipoMantenimiento(tipoMantenimiento);
                    mainController.actualizarMantenimiento();
                }

                Stage stage = (Stage) descripcionTextField.getScene().getWindow();
                stage.close();
            }
        } else {
            if (descripcionTextField.getText().isEmpty() || tipoMantenimientoTextField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Todos los campos deben estar llenos.");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Guardado");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro que desea guardar este proceso?");

            ButtonType buttonTypeYes = new ButtonType("Sí");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                String descripcion = descripcionTextField.getText();
                String tipoMantenimiento = tipoMantenimientoTextField.getText();

                if (procesoActual == null) {
                    Mantenimiento nuevoProceso = new Mantenimiento(descripcion, tipoMantenimiento);
                    accidenteController.agregarProceso(nuevoProceso);
                } else {
                    procesoActual.setDescripcion(descripcion);
                    procesoActual.setTipoMantenimiento(tipoMantenimiento);
                    accidenteController.actualizarProceso();
                }

                Stage stage = (Stage) descripcionTextField.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) descripcionTextField.getScene().getWindow();
        stage.close();
    }

    public void setMainController(AñadirVehiculoController controller) {
        this.mainController = controller;
    }

    public void setAccidenteController(AgregarAccidenteController controller) {
        this.accidenteController = controller;
    }

    public void setMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimientoActual = mantenimiento;
        tituloLabel.setText("Editar Mantenimiento");
        descripcionTextField.setText(mantenimiento.getDescripcion());
        tipoMantenimientoTextField.setText(mantenimiento.getTipoMantenimiento());
    }

    public void setProceso(Mantenimiento proceso) {
        this.procesoActual = proceso;
        tituloLabel.setText("Editar Proceso");
        descripcionTextField.setText(proceso.getDescripcion());
        tipoMantenimientoTextField.setText(proceso.getTipoMantenimiento());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
    }
}
