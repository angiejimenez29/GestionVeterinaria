package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorCitas {
    private ArrayList<Cita> citas;

    public GestorCitas() {
        this.citas = new ArrayList<>();
    }

    public void agendarCita(Scanner scanner) {
        System.out.print("Fecha de la cita (d/m): ");
        String fecha = scanner.nextLine();
        System.out.print("Nombre de la mascota: ");
        String mascotaNombre = scanner.nextLine();
        Cita cita = new Cita(fecha, mascotaNombre);
        citas.add(cita);
        System.out.println("Cita agendada");
    }

    public void modificarCita(Scanner scanner) {
        if (citas.isEmpty()) {
            System.out.println("No hay citas para modificar.");
            return;
        }

        verHistorialCitas();
        System.out.print("Numero de la cita que desea modificar: ");
        int index = scanner.nextInt()- 1;
        scanner.nextLine(); // Limpiar el buffer

        if (index >= 0 && index < citas.size()) {
            Cita cita = citas.get(index);
            System.out.println("Modificando la cita: ");
            cita.mostrarInfo();
            System.out.print("Nueva fecha (d/m) ");
            String nuevaFecha = scanner.nextLine();

            if (!nuevaFecha.isEmpty()) {
                cita.setFecha(nuevaFecha);
            }

            System.out.println("Cita modificada ");
        } else {
            System.out.println("Indice no valido");
        }
    }

    public void cancelarCita(Scanner scanner) {
        if (citas.isEmpty()) {
            System.out.println("No hay citas para cancelar");
            return;
        }

        verHistorialCitas();
        System.out.print("Numero de la cita que desea cancelar: ");
        int index = scanner.nextInt()-1;
        scanner.nextLine();

        if (index >= 0 && index < citas.size()) {
            citas.remove(index);
            System.out.println("Cita cancelada");
        } else {
            System.out.println("Indice no valido");
        }
    }

    public void verHistorialCitas() {
        System.out.println("\n--- Historial de citas ---");
        for (int i = 0; i < citas.size(); i++) {
            System.out.print((i+1) + ". ");
            citas.get(i).mostrarInfo();
        }
    }
}
