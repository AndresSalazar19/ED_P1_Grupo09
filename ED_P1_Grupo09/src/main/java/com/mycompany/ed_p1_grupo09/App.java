package com.mycompany.ed_p1_grupo09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;


import modelo.*;
import tda.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
        
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("inicio"), 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
 
    public static void main(String[] args) {
        CircularDoublyLinkedList<Image> imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes");
        System.out.println(imagenes.size());
        System.out.println("Hello Wolrd");
        launch();
    }

}