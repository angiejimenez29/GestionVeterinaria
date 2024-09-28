package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorPersonal {
    private ArrayList<Personal> personalList;

    public GestorPersonal() {
        this.personalList = new ArrayList<>();
    }

    public void agregarPersonal(Scanner scanner) {
        System.out.print("Nombre del nuevo personal: ");
        String nombre = scanner.nextLine();
        System.out.print("Puesto: ");
        String puesto = scanner.nextLine();
        Personal nuevoPersonal = new Personal(nombre, puesto);
        personalList.add(nuevoPersonal);
        System.out.println("Personal agregado");
    }

    public void verPersonal() {
        System.out.println("\n--- Personal registrado ---");
        for (Personal personal : personalList) {
            personal.mostrarInfo();
        }
    }
}
