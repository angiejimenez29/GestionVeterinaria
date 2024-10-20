package gestionveterinaria;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cita {
    private String nombreCliente;
    private String apellidoCliente;
    private String nombreMascota;
    private String tipoCita;
    private String turno;
    private String especialista;
    private String hora;

    private static ArrayList<Cita> citasList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public Cita(String nombreCliente, String apellidoCliente, String nombreMascota, String tipoCita, String turno, String especialista, String hora) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.nombreMascota = nombreMascota;
        this.tipoCita = tipoCita;
        this.turno = turno;
        this.especialista = especialista;
        this.hora = hora;
    }

    public static void iniciarMenu(boolean isAdmin, String clienteNombre, String clienteApellido) {
        int opcion;
        do {
            System.out.println("\n------ Menu Citas ------");
            System.out.println("1. Registrar cita");
            System.out.println("2. Editar cita");
            System.out.println("3. Eliminar cita");
            System.out.println("4. Ver citas");
            System.out.println("5. Volver");
            System.out.println("---------------------------");
            System.out.print("\n-> ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agendarCita(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 2:
                    editarCita(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 3:
                    cancelarCita(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 4:
                    verHistorialCitas(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    public static void agendarCita(boolean isAdmin, String clienteNombre, String clienteApellido) {
        String nombreCliente = isAdmin ? solicitarDato("Nombre del cliente") : clienteNombre;
        String apellidoCliente = isAdmin ? solicitarDato("Apellido del cliente") : clienteApellido;

        System.out.print("Nombre de la mascota: ");
      
        String nombreMascota = scanner.nextLine();

        // Seleccionar tipo de cita
        System.out.println("Seleccione tipo de cita:");
        System.out.println("1. Estetica");
        System.out.println("2. Consulta Medica");
        int seleccionTipoCita = scanner.nextInt();
        scanner.nextLine();
        
        String tipoCita;
        switch (seleccionTipoCita) {
            case 1:
                tipoCita = "Estetica";
                break;
            case 2:
                tipoCita = "Consulta Medica";
                break;
            default:
                System.out.println("Opción inválida. Se registrará como 'Consulta Medica' por defecto.");
                tipoCita = "Consulta Medica";
                break;
        }

        // Seleccionar especialista
        String nombreEspecialista = seleccionarEspecialista(tipoCita);
        if (nombreEspecialista.isEmpty()) {
            System.out.println("No se pudo agendar la cita. No hay especialistas disponibles.");

            return;
        }

        // Mostrar horas disponibles del especialista seleccionado
        List<String> horasDisponibles = Personal.obtenerHorasDisponibles(nombreEspecialista);
        if (horasDisponibles.isEmpty()) {
            System.out.println("No hay horas disponibles para el especialista seleccionado.");
            return;
        }

        System.out.println("\nHorarios disponibles para " + nombreEspecialista + ":");
        for (int i = 0; i < horasDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + horasDisponibles.get(i));
        }

        System.out.print("Seleccione la hora disponible: ");
        int seleccionHora = scanner.nextInt();
        scanner.nextLine();
        
        if (seleccionHora < 1 || seleccionHora > horasDisponibles.size()) {
            System.out.println("Selección no válida. Intente de nuevo.");

            return;
        }

        String hora = horasDisponibles.get(seleccionHora - 1);
        // Eliminar la hora seleccionada de la lista del especialista
        for (Personal p : Personal.getPersonalList()) {
            if (p.getNombre().equals(nombreEspecialista)) {
                p.eliminarHoraDisponible(hora);
                break;
            }
        }

        Cita nuevaCita = new Cita(nombreCliente, apellidoCliente, nombreMascota, tipoCita, "", nombreEspecialista, hora);
        citasList.add(nuevaCita);
        System.out.println("Cita registrada exitosamente.");
    }

    public static void editarCita(boolean isAdmin, String clienteNombre, String clienteApellido) {
        verHistorialCitas(isAdmin, clienteNombre, clienteApellido);

        System.out.print("\nIngrese el numero de la cita que desea editar: ");
        int citaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (citaIndex < 0 || citaIndex >= citasList.size()) {
            System.out.println("Cita no valida.");
            return;
        }

        Cita cita = citasList.get(citaIndex);

        if (!isAdmin && (!cita.nombreCliente.equals(clienteNombre) || !cita.apellidoCliente.equals(clienteApellido))) {
            System.out.println("No tiene permiso para editar esta cita.");
            return;
        }

        System.out.print("Nuevo nombre de la mascota (Enter para mantener): ");
        String nuevoNombreMascota = scanner.nextLine();
        if (!nuevoNombreMascota.isEmpty()) {
            cita.nombreMascota = nuevoNombreMascota;
        }

        System.out.print("Nuevo tipo de cita (Estetica/Consulta Medica) (Enter para mantener): ");
        String nuevoTipoCita = scanner.nextLine();
        if (!nuevoTipoCita.isEmpty()) {
            cita.tipoCita = nuevoTipoCita;

        }

        System.out.print("Nuevo turno (Manana/Tarde) (Enter para mantener): ");
        String nuevoTurno = scanner.nextLine();
        if (!nuevoTurno.isEmpty()) {
            cita.turno = nuevoTurno;
        }

        System.out.println("Cita editada exitosamente.");
    }

    public static void cancelarCita(boolean isAdmin, String clienteNombre, String clienteApellido) {
        verHistorialCitas(isAdmin, clienteNombre, clienteApellido);

        System.out.print("\nIngrese el numero de la cita que desea eliminar: ");
        int citaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (citaIndex < 0 || citaIndex >= citasList.size()) {
            System.out.println("Cita no valida.");
            return;
        }

        Cita cita = citasList.get(citaIndex);

        if (!isAdmin && (!cita.nombreCliente.equals(clienteNombre) || !cita.apellidoCliente.equals(clienteApellido))) {
            System.out.println("No tiene permiso para eliminar esta cita.");
            return;
        }

        citasList.remove(citaIndex);
        System.out.println("Cita eliminada exitosamente.");
    }


    public static void verHistorialCitas(boolean isAdmin, String clienteNombre, String clienteApellido) {
        System.out.println("\n------ Citas ------");
        for (int i = 0; i < citasList.size(); i++) {
            Cita cita = citasList.get(i);
            if (isAdmin || (cita.nombreCliente.equals(clienteNombre) && cita.apellidoCliente.equals(clienteApellido))) {
                System.out.println((i + 1) + ". " +
                    "Cliente: " + cita.nombreCliente + " " + cita.apellidoCliente + ", " +
                    "Mascota: " + cita.nombreMascota + ", " +
                    "Tipo de Cita: " + cita.tipoCita + ", " +
                    "Especialista: " + cita.especialista + ", " +
                    "Hora: " + cita.hora);
            }
        }
    }

    private static String seleccionarEspecialista(String tipoCita) {
        ArrayList<Personal> especialistasDisponibles = new ArrayList<>();

        for (Personal p : Personal.getPersonalList()) {
            if ((tipoCita.equalsIgnoreCase("Estetica") && p.getPuesto().equals("Esteticista")) ||
                (tipoCita.equalsIgnoreCase("Consulta Medica") && p.getPuesto().equals("Medico Veterinario"))) {
                especialistasDisponibles.add(p);
            }

        }

        if (especialistasDisponibles.isEmpty()) {
            System.out.println("No hay especialistas disponibles para este tipo de cita.");
            return "";
        }

        System.out.println("\nEspecialistas disponibles para " + tipoCita + ":");
        for (int i = 0; i < especialistasDisponibles.size(); i++) {
            Personal especialista = especialistasDisponibles.get(i);
            System.out.println((i + 1) + ". " + especialista.getNombre() + " - Horarios disponibles: " + especialista.getHorasDisponibles());
        }

        System.out.print("\nSeleccione el especialista deseado: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion > 0 && seleccion <= especialistasDisponibles.size()) {
            return especialistasDisponibles.get(seleccion - 1).getNombre();
        } else {
            System.out.println("Selección no valida. Por favor, intente de nuevo.");
            return seleccionarEspecialista(tipoCita);
        }
    }

    private static String solicitarDato(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine();
    }
}
