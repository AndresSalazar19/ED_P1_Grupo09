/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import tda.CircularDoublyLinkedList;

/**
 *
 * @author asala
 */


public class ImageLoader {
    public static CircularDoublyLinkedList<Image> loadImagesFromFolder(String folderPath) {
        CircularDoublyLinkedList<Image> imageList = new CircularDoublyLinkedList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isImageFile(file)) {
                    try {
                        Image image = new Image(new FileInputStream(file));
                        imageList.addLast(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return imageList;
    }
    

    public static void crearCarpeta(int id, CircularDoublyLinkedList<Image> imagenes) {
       String folderPath = "src/main/resources/imagenes/vehiculos/" + id;
       File folder = new File(folderPath);
       System.out.println("creando carpeta");

       // Crear la carpeta si no existe
       if (!folder.exists()) {
           folder.mkdirs();
       }

       // Limpiar la carpeta existente
       if (folder.exists() && folder.isDirectory()) {
           System.out.println("carpeta existe");
           for (File file : folder.listFiles()) {
               file.delete();
           }
       }

       // Guardar las imágenes en la carpeta
       int imageIndex = 1;
       for (Image image : imagenes) {
           String format = "png"; // default format

           // Verificar si la URL de la imagen no es nula
           if (image.getUrl() != null) {
               String url = image.getUrl().toLowerCase();
               if (url.endsWith(".jpg") || url.endsWith(".jpeg")) {
                   format = "jpg";
               } else if (url.endsWith(".png")) {
                   format = "png";
               }

               File outputFile = new File(folder, "imagen" + imageIndex + "." + format);
               try (InputStream inputStream = new URL(image.getUrl()).openStream();
                    FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                   // Guardar la imagen en el formato correspondiente
                   byte[] buffer = new byte[4096];
                   int bytesRead;
                   while ((bytesRead = inputStream.read(buffer)) != -1) {
                       outputStream.write(buffer, 0, bytesRead);
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
               imageIndex++;
           } else {
               System.err.println("URL de la imagen es nula para la imagen " + imageIndex);
           }
       }
   }




    
    private static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }
}
