package gestionveterinaria;

import java.util.ArrayList;

public class HistorialMedico {
    private ArrayList<String> registros;

    public HistorialMedico() {
        this.registros = new ArrayList<>();
    }

    public void agregarHistorial(String registro) {
        registros.add(registro);
    }

    public void mostrarHistorial() {
        if (registros.isEmpty()) {
            System.out.println("No hay registros en el historial medico.");
        } else {
            System.out.println("--- Historial Medico ---");
            for (String registro : registros) {
                System.out.println(registro);
            }
        }
    }
}
