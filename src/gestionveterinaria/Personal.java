package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;

public class Personal {
    private String nombre;
    private String puesto;
    private static ArrayList<Personal> personalList = new ArrayList<>();

    public Personal(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + ", Puesto: " + puesto);
    }
    
    public static void agregarPersonal(Scanner scanner) {
        System.out.print("Nombre del nuevo personal: ");
        String nombre = scanner.nextLine();
        System.out.print("Puesto: ");
        String puesto = scanner.nextLine();
        Personal nuevoPersonal = new Personal(nombre, puesto);
        personalList.add(nuevoPersonal);
        System.out.println("Personal agregado");
    }
    
    public static void verPersonal() {
        System.out.println("\n--- Personal registrado ---");
        for (Personal personal : personalList) {
            personal.mostrarInfo();
        }    
    }
}
