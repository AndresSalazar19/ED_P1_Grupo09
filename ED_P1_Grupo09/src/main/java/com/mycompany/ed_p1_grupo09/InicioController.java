/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import modelo.ImageLoader;
import tda.CircularDoublyLinkedList;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class InicioController  {
    
     @FXML
    private void iniciarSesion() throws IOException {
        App.setRoot("iniciarSesion");
    }
    
     @FXML
    private void registrarse() throws IOException {
        App.setRoot("registrarse");
    }
    
 
    
   public void initialize() {
        // Cargar las imágenes desde una carpeta
 
    }
    

       
}
