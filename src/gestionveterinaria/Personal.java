package gestionveterinaria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Personal {
    private String nombre;
    private String puesto;
    private String telefono;
    private List<String> horasDisponibles;
    private static ArrayList<Personal> personalList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public Personal(String nombre, String puesto, String telefono, List<String> horasDisponibles) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.telefono = telefono;
        this.horasDisponibles = horasDisponibles;
    }
    
    public static List<Personal> getPersonalList() {
    return personalList; // Asegúrate de que devuelva la lista de objetos Personal
}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public List<String> getHorasDisponibles() { return horasDisponibles; }

    public void eliminarHoraDisponible(String hora) {
        horasDisponibles.remove(hora);
    }

    public static List<String> obtenerHorasDisponibles(String nombreEspecialista) {
        for (Personal p : personalList) {
            if (p.getNombre().equals(nombreEspecialista)) {
                return p.getHorasDisponibles();
            }
        }
        return new ArrayList<>();
    }

    public static void registrarPersonal() {
        System.out.println("\n");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("\nTelefono: ");
        String telefono = scanner.nextLine();
        
        String puesto = seleccionarPuesto();

        List<String> horasDisponibles = seleccionarHorario(puesto);
        personalList.add(new Personal(nombre, puesto, telefono, horasDisponibles));

        System.out.println("\nPersonal registrado exitosamente");
        System.out.println("\n");
    }

    private static String seleccionarPuesto() {
        int opcion;
        while (true) {
            System.out.println("\nSeleccione el puesto: ");
            System.out.println("1. Esteticista");
            System.out.println("2. Medico Veterinario");
            System.out.print("-> ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) return "Esteticista";
            if (opcion == 2) return "Medico Veterinario";

            System.out.println("Opción no valida. Por favor, seleccione nuevamente.");
        }
    }

    private static List<String> seleccionarHorario(String puesto) {
        System.out.println("\nSeleccione el turno: ");
        System.out.println("1. Manana");
        System.out.println("2. Tarde");
        System.out.print("-> ");
        int turno = scanner.nextInt();
        scanner.nextLine();

        List<String> horarios = new ArrayList<>();
        if (turno == 1) {
            horarios = puesto.equals("Medico Veterinario") ? 
                List.of("8AM", "9:30AM", "11AM") : 
                List.of("8AM", "10AM");
        } else {
            horarios = puesto.equals("Medico Veterinario") ? 
                List.of("1:30PM", "3PM", "4:30PM") : 
                List.of("1PM", "3PM");
        }
        return new ArrayList<>(horarios); // Se hace una copia para evitar problemas de referencia.
    }
    
    public void agregarHoraDisponible(String hora) {
    if (!horasDisponibles.contains(hora)) {
        horasDisponibles.add(hora);
    }
}

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n------ Menu Personal ------");
            System.out.println("1. Registrar nuevo personal");
            System.out.println("2. Editar personal");
            System.out.println("3. Ver lista del personal");
            System.out.println("4. Volver");
            System.out.println("---------------------------");
            System.out.print("\n-> ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarPersonal();
                    break;
                case 2:
                    editarPersonal();
                    break;
                case 3:
                    verListaPersonal();
                    break;
                case 4:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("\nOpción invalida.");
            }
        } while (opcion != 4);
    }

    private static void editarPersonal() {
        verListaPersonal();
        System.out.print("\nIngrese el ID del empleado que desea editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id > 0 && id <= personalList.size()) {
            Personal empleado = personalList.get(id - 1);
            System.out.println("Editando a: " + empleado.getNombre() + "\n");

            System.out.print("Nuevo nombre (presione Enter para no cambiar): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                empleado.setNombre(nuevoNombre);
            }

            String nuevoPuesto = seleccionarPuesto();
            empleado.setPuesto(nuevoPuesto);
            List<String> nuevoHorario = seleccionarHorario(nuevoPuesto);
            empleado.horasDisponibles = nuevoHorario;

            System.out.println("\nEmpleado editado exitosamente.");
        } else {
            System.out.println("ID no valido.");
        }
    }

    private static void verListaPersonal() {
        System.out.println("\n\t\t\t-------- LISTA DE PERSONAL --------");
        System.out.println("\tID\tNombre\t\tPuesto\t\tTelefono\tHoras Disponibles");
        System.out.println("\t--------------------------------------------------------------------------------");
        for (int i = 0; i < personalList.size(); i++) {
            Personal empleado = personalList.get(i);

            String horas = String.join(", ", empleado.getHorasDisponibles());
            System.out.println("\t" + (i + 1) + "\t" + empleado.getNombre() + "\t\t" + empleado.getPuesto() + "\t\t" + empleado.getTelefono() + "\t\t" + horas);
    }
}

}

