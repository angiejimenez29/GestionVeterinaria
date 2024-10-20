package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;

public class Personal {
    private String nombre;
    private String puesto;
    private String telefono;
    private String horario;
    private boolean disponible;
    private static ArrayList<Personal> personalList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public Personal() {
        this.nombre = "";
        this.puesto = "";
        this.telefono = "";
        this.horario = "";
        this.disponible = true;
    }

    public Personal(String nombre, String puesto, String correo, String horario, Boolean disponible) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.telefono = correo;
        this.horario = horario;
        this.disponible = disponible;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public String getCorreo() { return telefono; }
    public void setCorreo(String correo) { this.telefono = correo; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public Boolean getDisponibilidad() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = true; }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Puesto: " + puesto + ", Correo: " + telefono + ", Horario: " + horario + ", Disponibilidad: " + disponible;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public static boolean isEspecialistaRegistrado(String nombreEspecialista) {
        for (Personal p : personalList) {
            if (p.getNombre().equals(nombreEspecialista)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean especialistaDisponible(String nombreEspecialista) {
        for (Personal p : personalList) {
            if (p.getNombre().equals(nombreEspecialista) && p.isDisponible()) {
                return true;
            }
        }
        return false;
    }
    
    public static void marcarEspecialistaNoDisponible(String nombreEspecialista) {
        for (Personal p : personalList) {
            if (p.getNombre().equals(nombreEspecialista)) {
                p.setDisponible(false);
                break;
            }
        }
    }

    public static void marcarEspecialistaDisponible(String nombreEspecialista) {
        for (Personal p : personalList) {
            if (p.getNombre().equals(nombreEspecialista)) {
                p.setDisponible(true);
                break;
            }
        }
    }

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n------ Menu Personal ------");
            System.out.println("1. Registrar nuevo personal");
            System.out.println("2. Editar personal");
            System.out.println("3. Horarios del personal");
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
                    verHorariosPersonal();
                    break;
                case 4:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("\nOpcion invalida.");
            }
        } while (opcion != 4);
    }

    private static void registrarPersonal() {
        System.out.println("\n");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        String puesto = seleccionarPuesto();
        
        System.out.print("\nTelefono: ");
        String telefono = scanner.nextLine();

        String horario = seleccionarHorario(puesto);
        
        personalList.add(new Personal(nombre, puesto, telefono, horario, true));
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
            if (opcion == 2) return "Medico/a";

            System.out.println("Opción no valida. Por favor, seleccione nuevamente.");
        }
    }

    private static String seleccionarHorario(String puesto) {
        System.out.println("\nSeleccione el turno: ");
        System.out.println("1. Manana");
        System.out.println("2. Tarde");
        System.out.print("-> ");
        int turno = scanner.nextInt();
        scanner.nextLine();
        
        if (turno == 1) { 
            if (puesto.equals("Medico Veterinario")) {
                return "8AM, 9:30AM, 11AM"; 
            } else {
                return "8AM, 10AM";
            }
        } else { // Tarde
            if (puesto.equals("Medico Veterinario")) {
                return "1:30PM, 3PM, 4:30PM";
            } else {
                return "1PM, 3PM";
            }
        }
    }

    private static void editarPersonal() {
        verHorariosPersonal();
        System.out.print("\nIngrese el ID del empleado que desea editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id > 0 && id <= personalList.size()) {
            Personal empleado = personalList.get(id - 1);
            System.out.println("Editando a: " + empleado + "\n");
            System.out.print("Nuevo nombre (presione Enter para no cambiar): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                empleado.setNombre(nuevoNombre);
            }
            String nuevoPuesto = seleccionarPuesto();
            empleado.setPuesto(nuevoPuesto);
            String nuevoHorario = seleccionarHorario(nuevoPuesto);
            empleado.setHorario(nuevoHorario);
            System.out.println("Empleado editado exitosamente");
            scanner.nextLine();
        } else {
            System.out.println("ID no valido.");
        }
    }

    private static void verHorariosPersonal() {
        System.out.println("\n\t\t\t-------- LISTA PERSONAL --------");
        System.out.println("\tID\tNombre\t\tPuesto\t\t\tHorario");
        System.out.println("\t--------------------------------------------------------------------------------");
        for (int i = 0; i < personalList.size(); i++) {
            Personal empleado = personalList.get(i);
            String disponibilidad = empleado.isDisponible() ? "Disponible" : "No disponible";
            System.out.println("\t" + (i + 1) + "\t" + empleado.getNombre() + "\t\t" + empleado.getPuesto() + "\t\t" + empleado.getHorario() + "\t\t" + disponibilidad);
        System.out.println("\tEmpleado editado exitosamente");
        scanner.nextLine();
        }
    }
}


