package gestionveterinaria;

import java.util.Scanner;
import java.util.ArrayList;

public class HistorialMedico {
    private Mascota mascota;
    private String nombreCliente;

    public HistorialMedico(Mascota mascota) {
        this.mascota = mascota;
    }

    public void mostrarHistorial() {
        System.out.println("\n--- Historial Medico ---");
        System.out.println("Nombre de la mascota: " + mascota.getNombreMascota());
        System.out.println("Especie: " + mascota.getEspecie());
        System.out.println("Raza: " + mascota.getRaza());
        System.out.println("Edad: " + mascota.getEdad());
        System.out.println("Sexo: " + mascota.getSexo());
        System.out.println("Castrado: " + (mascota.isCastrada() ? "Si" : "No"));
        if (!mascota.isCastrada() && mascota.getSexo().equals("Hembra")) {
            System.out.println("Gestante: " + (mascota.isGestante() ? "Si" : "No"));
        }
        System.out.println("Color: " + mascota.getColor());
        System.out.println("Peso: " + mascota.getPeso() + " kg");
        System.out.println("Fecha de nacimiento: " + mascota.getFechaNacimiento());
        System.out.println("Fecha de registro: " + mascota.getFechaRegistro());
        
        System.out.println("\nVacunas:");
        if (mascota.getVacunas().isEmpty()) {
            System.out.println("");
        } else {
            for (String vacuna : mascota.getVacunas()) {
                System.out.println("- " + vacuna);
            }
        }

        System.out.println("\nAlergias:");
        if (mascota.getAlergias().isEmpty()) {
            System.out.println("");
        } else {
            for (String alergia : mascota.getAlergias()) {
                System.out.println("- " + alergia);
            }
        }

        System.out.println("\nEnfermedades:");
        if (mascota.getEnfermedades().isEmpty()) {
            System.out.println("");
        } else {
            for (String enfermedad : mascota.getEnfermedades()) {
                System.out.println("- " + enfermedad);
            }
        }

        System.out.println("\nObservaciones: " + mascota.getObservaciones());
    }
    public static void buscarHistorial(ArrayList<Mascota> mascotas, Scanner scanner) {
        System.out.print("Nombre de la mascota: ");
        String nombreMascota = scanner.nextLine();

        Mascota mascotaEncontrada = null;
        for (Mascota mascota : mascotas) {
            if (mascota.getNombreMascota().equalsIgnoreCase(nombreMascota)) {
                mascotaEncontrada = mascota;
                break;
            }
        }

        if (mascotaEncontrada != null) {
            HistorialMedico historial = new HistorialMedico(mascotaEncontrada);
            historial.mostrarHistorial(); 
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }
    //FALTA EDITAR HISTORIAL Y AGREGAR LAS CITAS DE CADA MASCOTA
}
