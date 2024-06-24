/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif");
    }
}
