package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.*;
import tda.*;

public class AgregarAccidenteController {

    @FXML
    private Label tituloLabel;
    @FXML
    private TextField descripcionAcciTF;
    @FXML
    private TextField partesAfectadasTF;
    @FXML
    private DatePicker acciDP;
    @FXML
    private VBox contenedorProcesos;
    @FXML
    private ScrollPane scrollPaneProcesos;
    
    private AñadirVehiculoController mainController;
    private Accidente accidenteActual;
    LinkedList<Mantenimiento> procesos = new LinkedList<>();
    
    @FXML
    private void guardarAccidente() {
        if (descripcionAcciTF.getText().isEmpty() || partesAfectadasTF.getText().isEmpty() || acciDP.getValue() == null) {
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
        alert.setContentText("¿Está seguro que desea guardar este accidente?");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            String descripcion = descripcionAcciTF.getText();
            String parteAfectada = partesAfectadasTF.getText();
            LocalDate fechaAccidente = acciDP.getValue();

            if (accidenteActual == null) {
                LinkedList<Mantenimiento> listaMantenimiento = new LinkedList<>();
                Accidente nuevoAccidente = new Accidente(1, descripcion, parteAfectada, fechaAccidente, listaMantenimiento);
                mainController.agregarAccidente(nuevoAccidente);
            } else {
                accidenteActual.setDescripcion(descripcion);
                accidenteActual.setParteAfectada(parteAfectada);
                accidenteActual.setFechaAccidente(fechaAccidente);
                mainController.actualizarAccidente();
            }

            Stage stage = (Stage) descripcionAcciTF.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) descripcionAcciTF.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void añadirProceso() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarMantenimiento.fxml"));
            Parent root = loader.load();

            AgregarMantenimientoController controller = loader.getController();
            controller.setController(mainController);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Proceso");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mostrarProcesos();
    }

    public void setController(AñadirVehiculoController controller) {
        this.mainController = controller;
    }

    public void setAccidente(Accidente accidente) {
        this.accidenteActual = accidente;
        tituloLabel.setText("Editar Accidente");
        descripcionAcciTF.setText(accidente.getDescripcion());
        partesAfectadasTF.setText(accidente.getParteAfectada());
        acciDP.setValue(accidente.getFechaAccidente());
    }
    
       public void mostrarProcesos() {
        contenedorProcesos.getChildren().clear();

        if (procesos.isEmpty()) {
            Label noMantenimientosLabel = new Label("No hay mantenimientos.");
            contenedorProcesos.getChildren().add(noMantenimientosLabel);
            return;
        }

        for (Mantenimiento proceso : procesos) {
            HBox mantenimientoHbox = new HBox();
            mantenimientoHbox.setSpacing(10);
            mantenimientoHbox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: gray;");
            mantenimientoHbox.setAlignment(Pos.CENTER_LEFT);

            VBox mantenimientoBox = new VBox();
            mantenimientoBox.setSpacing(5);

            Label mantenimientoDescripcion = new Label("Descripción: " + proceso.getDescripcion());
            Label mantenimientoTipoMantenimiento = new Label("Tipo de Mantenimiento: " + proceso.getTipoMantenimiento());

            mantenimientoBox.getChildren().addAll(mantenimientoDescripcion, mantenimientoTipoMantenimiento);

            Button editarMantenimientoBtn = new Button("Editar Mantenimiento");
            editarMantenimientoBtn.setOnAction(event -> {
                System.out.println("EDITARRRR");
            });

            Button eliminarMantenimientoBtn = new Button("X");
            eliminarMantenimientoBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            eliminarMantenimientoBtn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro que desea eliminar este mantenimiento?");

                ButtonType buttonTypeYes = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    procesos.remove(proceso);
                    mostrarProcesos();
                }
            });

            HBox btnContainer = new HBox(editarMantenimientoBtn, eliminarMantenimientoBtn);
            btnContainer.setAlignment(Pos.CENTER_RIGHT);
            btnContainer.setSpacing(10);
            btnContainer.setPrefWidth(200);
            btnContainer.setPrefHeight(80);

            HBox.setHgrow(btnContainer, Priority.ALWAYS);

            mantenimientoHbox.getChildren().addAll(mantenimientoBox, btnContainer);
            mantenimientoHbox.setAlignment(Pos.CENTER);
            contenedorProcesos.getChildren().add(mantenimientoHbox);
        }

        scrollPaneProcesos.setContent(contenedorProcesos);
        scrollPaneProcesos.setFitToWidth(true);
        scrollPaneProcesos.setFitToHeight(true);
    }
       
}