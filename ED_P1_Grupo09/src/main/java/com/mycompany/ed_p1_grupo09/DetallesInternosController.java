/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.TipoCombustible;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import modelo.DetallesVehiInt;
;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetallesInternosController implements Initializable {

    @FXML
    private TextField TraccionTF;
    @FXML
    private TextField TransmisionTF;
    @FXML
    private TextField PlacaTF;
    @FXML
    private ComboBox<TipoCombustible> TipoCombustibleCOMB;
    @FXML
    private CheckBox ClimatizadoCB;
    @FXML
    private Button guardarB;
    @FXML
    private Button volverB;
    @FXML
    private TextField TipoMotorTF;

    private AñadirVehiculoController mainController;
    private DetallesVehiInt detalles;

    public void setMainController(AñadirVehiculoController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar el ComboBox con los valores del enum TipoCombustible
        TipoCombustibleCOMB.getItems().addAll(TipoCombustible.values());
    }

    public void setDetalles(DetallesVehiInt detalles) {
        this.detalles = detalles;
        TraccionTF.setText(detalles.getTraccion());
        TransmisionTF.setText(detalles.getTransmision());
        PlacaTF.setText(detalles.getPlaca());
        TipoCombustibleCOMB.setValue(detalles.getCombustible());
        ClimatizadoCB.setSelected(detalles.isClimatizado());
        TipoMotorTF.setText(detalles.getTipoMotor());
    }

    @FXML
    private void guardarDetallesInt() {
        String traccion = TraccionTF.getText();
        String transmision = TransmisionTF.getText();
        String placa = PlacaTF.getText();
        TipoCombustible tipoCombustible = TipoCombustibleCOMB.getValue();
        boolean climatizado = ClimatizadoCB.isSelected();
        String tipoMotor = TipoMotorTF.getText();

        // Verificar que todos los campos estén llenos
        if (traccion.isEmpty() || transmision.isEmpty() || placa.isEmpty() || tipoCombustible == null || tipoMotor.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Todos los campos deben estar llenos.");
            alert.showAndWait();
            return;
        }

        // Mostrar diálogo de confirmación
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Guardado");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro que desea guardar estos detalles?");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            if (detalles == null) {
                detalles = new DetallesVehiInt(traccion, transmision, tipoCombustible, placa, climatizado, tipoMotor);
            } else {
                detalles.setTraccion(traccion);
                detalles.setTransmision(transmision);
                detalles.setCombustible(tipoCombustible);
                detalles.setPlaca(placa);
                detalles.setClimatizado(climatizado);
                detalles.setTipoMotor(tipoMotor);
            }

            if (mainController != null) {
                mainController.setDetallesInternos(detalles);
            }
            mainController.mostrarDetallesInt();
            Stage stage = (Stage) guardarB.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) volverB.getScene().getWindow();
        stage.close();
    }

    @FXML
    private String getTraccion() {
        return TraccionTF.getText();
    }

    @FXML
    private String getTransmision() {
        return TransmisionTF.getText();
    }

    @FXML
    private String getPlaca() {
        return PlacaTF.getText();
    }
    
    @FXML
    private String getTipoMotor() {
        return TipoMotorTF.getText();
    }

    @FXML
    private TipoCombustible selectTipoCombustible() {
        return TipoCombustibleCOMB.getValue();
    }

    @FXML
    private boolean isClimatizado() {
        return ClimatizadoCB.isSelected();
    }
}