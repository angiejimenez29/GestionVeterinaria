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

    public static void agendarCita(Scanner scanner) {
        System.out.print("Fecha de la cita (d/m): ");
        String fecha = scanner.nextLine();
        System.out.print("Nombre de la mascota: ");
        String mascotaNombre = scanner.nextLine();
        if (!Administrador.isMascotaRegistrada(mascotaNombre)) {
            System.out.println("La mascota no se encuentra registrada.");
            return;
        }
        System.out.print("Tipo de cita (baño, consulta médica): ");
        String tipoCita = scanner.nextLine();

        System.out.println("Seleccione un especialista:");

        System.out.print("Ingrese el nombre del especialista: ");
        String especialista = scanner.nextLine();
        
        
        if (!Personal.isEspecialistaRegistrado(especialista) || !Personal.especialistaDisponible(especialista)) {
            System.out.println("El especialista no está registrado o no se encuentra disponible.");
            return;
        }

        Cita cita = new Cita(fecha, mascotaNombre, tipoCita, especialista);
        citas.add(cita);
        Personal.marcarEspecialistaNoDisponible(especialista); // Especialista no disponible ahora
        System.out.println("Cita agendada con éxito.");
    }

    public static void modificarCita(Scanner scanner) {
        System.out.println("\n--- Modificar Cita ---");
        System.out.print("Ingrese el número de la cita a modificar: ");
        int i=scanner.nextInt()-1;
        scanner.nextLine();

        if (i<0 || i>=citas.size()) {
            System.out.println("Cita no válida.");
            return;
        }
        
        Cita cita = citas.get(i);

        System.out.print("Nueva fecha (actual: "+cita.fecha+"): ");
        String nuevaFecha=scanner.nextLine();
        System.out.print("Nuevo especialista (actual: "+cita.especialista+"): ");
        String nuevoEspecialista=scanner.nextLine();

        //Verificación
        if (!Personal.isEspecialistaRegistrado(nuevoEspecialista)) {
            System.out.println("Error: El nuevo especialista no está registrado.");
            return;
        }

        if (!nuevoEspecialista.equals(cita.especialista) || !Personal.especialistaDisponible(nuevoEspecialista)) {
            System.out.println("Eligió el mismo especialista o el nuevo especialista no está disponible.");
            return;
        }

        // Si se cambia el especialista, actualizar la disponibilidad
        if (!nuevoEspecialista.equals(cita.especialista)) {
            Personal.marcarEspecialistaDisponible(cita.especialista);
            Personal.marcarEspecialistaNoDisponible(nuevoEspecialista);
            cita.especialista = nuevoEspecialista;
        }
        cita.fecha = nuevaFecha;
        System.out.println("Cita modificada con éxito.");
    }

    public static void cancelarCita(Scanner scanner) {
        System.out.println("\n--- Cancelar Cita ---");
        System.out.print("Ingrese el número de la cita a cancelar: ");
        int i=scanner.nextInt()-1;
        scanner.nextLine();

        if (i<0 || i>=citas.size()) {
            System.out.println("Cita no válida.");
            return;
        }

        Cita cita=citas.get(i);
        Personal.marcarEspecialistaDisponible(cita.especialista);
        citas.remove(i);
        System.out.println("Cita cancelada con éxito.");
    }

    public static void verHistorialCitas() {
        System.out.println("\n--- Historial de citas ---");
        for (int i = 0; i < citas.size(); i++) {
            System.out.print((i + 1) + ". ");
            citas.get(i).mostrarInfo();
        }
    }
}
