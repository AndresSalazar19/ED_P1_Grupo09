/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import tda.*;
/**
 *
 * @author asala
 */
public class AccidenteManager {
    private static final String accidenteArchivos = "src/main/java/archivos/accidenteArchivos.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Accidente> cargarAccidentes() throws IOException {
        DoublyLinkedList<Accidente> accidentes = new DoublyLinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(accidenteArchivos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Accidente accidente = crearAccidenteDesdeLinea(linea.split(","));
                accidentes.addFirst(accidente);
            }
        }

        return accidentes;
    }

    public static void guardarAccidentes(List<Accidente> accidentes) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(accidenteArchivos))) {
            for (Accidente accidente : accidentes) {
                String linea = crearLineaDesdeAccidente(accidente);
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    private static Accidente crearAccidenteDesdeLinea(String[] linea) {
        int id = Integer.parseInt(linea[0]);
        String descripcion = linea[1];
        String parteAfectada = linea[2];
        LocalDate fecha = LocalDate.parse(linea[3], formatter);
        LinkedList<Mantenimiento> mantenimientos = cargarMantenimientos(linea[4]);

        return new Accidente(id, descripcion, parteAfectada, fecha, mantenimientos);
    }

    private static String crearLineaDesdeAccidente(Accidente accidente) {
        StringBuilder sb = new StringBuilder();
        sb.append(accidente.getId()).append(",");
        sb.append("\"").append(accidente.getDescripcion()).append("\",");
        sb.append("\"").append(accidente.getParteAfectada()).append("\",");
        sb.append(accidente.getFechaAccidente().format(formatter)).append(",");
        sb.append(crearLineaDesdeMantenimientos(accidente.getListaProcesos()));
        return sb.toString();
    }

    private static LinkedList<Mantenimiento> cargarMantenimientos(String mantenimientosStr) {
        LinkedList<Mantenimiento> lista = new LinkedList<>();
        if (!mantenimientosStr.equals("[]")) {
            String[] mantenimientosArray = mantenimientosStr.replace("[", "").replace("]", "").split(";");
            for (String mantenimientostr : mantenimientosArray) {
                String[] detalles = mantenimientostr.replace("(", "").replace(")", "").split("~");
                String descripcion = detalles[0];
                String tipo_mantenimiento = detalles[1];
                lista.addFirst(new Mantenimiento(descripcion, tipo_mantenimiento));
            }
        }
        return lista;
    }

    private static String crearLineaDesdeMantenimientos(LinkedList<Mantenimiento> mantenimientos) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Mantenimiento mantenimiento : mantenimientos) {
            if (sb.length() > 1) {
                sb.append(";");
            }
            sb.append("(")
              .append(mantenimiento.getDescripcion()).append("~")
              .append(mantenimiento.getTipoMantenimiento())
              .append(")");
        }
        sb.append("]");
        return sb.toString();
    }

    public static int obtenerNextId(List<Accidente> accidentes) {
        if (accidentes.isEmpty()) {
            return 1; // Si la lista está vacía, devolver 1
        }
        int maxId = 0;
        for (Accidente accidente : accidentes) {
            if (accidente.getId() > maxId) {
                maxId = accidente.getId();
            }
        }
        return maxId + 1;
    }
}