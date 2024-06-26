/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class IniciarSesionController implements Initializable {

    @FXML
    private TextField correoTextField;
    
    @FXML
    private TextField contrasenaTextField;
     
    private SistemaLogin sistemaLogin;
    private Usuario usuarioLogueado;
    
    @FXML
    private void registrarse() throws IOException {
        App.setRoot("registrarse");
    }
    
 @FXML
    private void iniciarSesion() throws IOException {
        String correo = correoTextField.getText();
        String contrasena = contrasenaTextField.getText();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }
        
        // Lógica para validar el inicio de sesión
        boolean esValido = sistemaLogin.iniciarSesion(correo, contrasena);
        if (esValido) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso.");
            
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/archivos/loggedArchivos.csv"))) {
            bw.write(correo);
        } catch (IOException e) {
            e.printStackTrace();
        }
            App.setRoot("paginaPrincipal");

        } else {
            mostrarAlerta("Error", "Correo o contraseña incorrectos.");
        }
    }
    
    
    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        DialogPane dialogo = alert.getDialogPane();
        dialogo.setBackground(new Background(new BackgroundFill(
                Color.LIGHTBLUE,
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));

        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sistemaLogin = new SistemaLogin(); // Inicializa el sistema de login
    }    
    
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
}
