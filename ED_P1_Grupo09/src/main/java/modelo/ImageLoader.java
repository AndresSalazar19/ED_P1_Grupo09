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
import javafx.scene.image.Image;
import tda.*;

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
        System.out.println("Creando carpeta...");

        limpiarYCrearCarpeta(folder);

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

    public static void limpiarYCrearCarpeta(File folder) {
        if (folder.exists()) {
            borrarCarpeta(folder);
        }
        // Volver a crear la carpeta
        if (folder.mkdirs()) {
            System.out.println("Carpeta creada: " + folder.getPath());
        } else {
            System.out.println("No se pudo crear la carpeta: " + folder.getPath());
        }
    }

    private static void borrarCarpeta(File folder) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                borrarCarpeta(file); // Llamada recursiva para manejar subdirectorios
            }
        }
        if (folder.delete()) {
            System.out.println("Borrado: " + folder.getPath());
        } else {
            System.out.println("No se pudo borrar: " + folder.getPath());
        }
    }

    private static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }
}
