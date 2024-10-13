package gestionveterinaria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cita {
    private String fecha;
    private String mascotaNombre;
    private String tipoCita;
    private String especialista;
    private static ArrayList<Cita> citas = new ArrayList<>();

    public Cita(String fecha, String mascotaNombre, String tipoCita, String especialista) {
        this.fecha = fecha;
        this.mascotaNombre = mascotaNombre;
        this.tipoCita = tipoCita;
        this.especialista = especialista;
    }

    public void mostrarInfo() {
        System.out.println("Cita el " + fecha + " para la mascota: " + mascotaNombre + " con el especialista " + especialista + " para " + tipoCita);
    }

    public static void agendarCita(Scanner scanner, List<Personal> personalList) {
        System.out.print("Fecha de la cita (d/m): ");
        String fecha = scanner.nextLine();
        System.out.print("Nombre de la mascota: ");
        String mascotaNombre = scanner.nextLine();
        System.out.print("Tipo de cita (baño, consulta médica): ");
        String tipoCita = scanner.nextLine();

        System.out.println("Seleccione un especialista:");

        System.out.print("Ingrese el nombre del especialista: ");
        String especialista = scanner.nextLine();

        Cita cita = new Cita(fecha, mascotaNombre, tipoCita, especialista);
        citas.add(cita);
        System.out.println("Cita agendada con éxito.");
    }

    public static void modificarCita(Scanner scanner) {
        // Lógica de modificación de citas, similar a la anterior
    }

    public static void cancelarCita(Scanner scanner) {
        // Lógica para cancelar citas, similar a la anterior
    }

    public static void verHistorialCitas() {
        System.out.println("\n--- Historial de citas ---");
        for (int i = 0; i < citas.size(); i++) {
            System.out.print((i + 1) + ". ");
            citas.get(i).mostrarInfo();
        }
    }
}
