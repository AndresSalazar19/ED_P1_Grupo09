/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import modelo.*;
import tda.*;

/**
 * FXML Controller class
 *
 * @author asala
 */

public class PaginaPrincipalController implements Initializable {
    
    @FXML
    private Label correoLabel;
    
    public void setCorreo(){
      try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/loggedArchivos.csv"))) {
            String linea;
            linea = br.readLine();
            correoLabel.setText(linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getCorreo(){
     try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/loggedArchivos.csv"))) {
            String linea;
            linea = br.readLine();
            return linea;
        } catch (IOException e) {
            e.printStackTrace();
        }
     return "null";
    }
      
     @FXML
    private void añadirVehiculo() throws IOException {
        App.setRoot("añadirVehiculo");
    }
    
    @FXML
    private void cerrarSesion() throws IOException {
        App.setRoot("verDetallesVehiculo");
    }
    

    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Cargar las imágenes desde una carpeta
       
        System.out.println(getCorreo());
        setCorreo();
          

    }
    






}
