package gestionveterinaria;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Mascota {
    private Scanner scanner = new Scanner(System.in);
    private String nombreMascota;
    private int edad;
    private String especie;
    private String raza;
    private String sexo;
    private String color;
    private double peso;
    private String fechaNacimiento;
    private String fechaRegistro;
    private ArrayList<String> vacunas;
    private ArrayList<String> alergias;
    private ArrayList<String> enfermedades;
    private String observaciones;
    private boolean castrada;
    private boolean gestante;
    private Cliente cliente;
    private ArrayList<String> historialMedico;
    private static ArrayList<String> mascotasRegistradas = new ArrayList<>();
    private Map<String, Cliente> mascotaClienteMap = new HashMap<>();
    private ArrayList<Mascota> mascotas = new ArrayList<>();
    
    
    private static final String[] vacunasPerro = {"Sextuple (DHPPi+L)", "Quintuple (DHPPi)", "Triple (DHP)", "Antirrabica", "Tos de las Perreras"};
    private static final String[] vacunasGato = {"Cuadruple (V4)", "Triple (V3)", "Antirrabica", "Leucemia felina", "Clamidiosis"};
    private static final String[] vacunasConejo = {"Mixomatosis", "Enfermedad hemorrágica viral", "Pasteurelosis"};

    private static final String[] alergiasPerro = {"Polen de pasto", "Picaduras de pulgas", "Pollo", "Penicilina", "Frutos secos", "Lactosa"};
    private static final String[] alergiasGato = {"Polen de flores", "Picaduras de pulgas", "Pescado", "Lactosa"};
    private static final String[] alergiasConejo = {"Heno de alfalfa", "Polen de flores", "Frutos secos"};

    private static final String[] enfermedadesPerro = {"Moquillo", "Parvovirus", "Hepatitis infecciosa", "Leptospirosis", "Tos de las Perreras", "Coronavirus", "Rabia", "Dermatitis"};
    private static final String[] enfermedadesGato = {"Panleucopenia", "Rinotraqueitis", "Calicivirus", "Leucemia felina", "Clamidiosis", "Rabia", "Dermatitis"};
    private static final String[] enfermedadesConejo = {"Mixomatosis", "Enfermedad hemorragica viral", "Pasteurelosis", "Dermatitis"};

    public Mascota(String nombre, String especie, String raza, int edad, String sexo, String color, 
                   double peso, String fechaNacimiento, String fechaRegistro,
                   ArrayList<String> vacunas, ArrayList<String> alergias,
                   ArrayList<String> enfermedades, String observaciones, 
                   boolean castrada, boolean gestante, Cliente cliente){
        this.nombreMascota = nombre;
        this.edad = edad;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.peso = peso;
        this.observaciones = observaciones;
        this.castrada = castrada;
        this.gestante = gestante;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.vacunas = new ArrayList<>(vacunas);
        this.alergias = new ArrayList<>(alergias);
        this.enfermedades = new ArrayList<>(enfermedades); 
        this.historialMedico = new ArrayList<>();  
        this.mascotas = new ArrayList<>();
    }
    
    public Mascota(){
        this.mascotas = new ArrayList<>();
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public void registroMascota(){
        ArrayList<Cliente> clientes = Cliente.getClientes();
        Cliente.mostrarClientes(); 
        System.out.print("\n");        
        System.out.print("\n-> ");
        int opcionCliente = scanner.nextInt();
        scanner.nextLine();
        if (opcionCliente < 1 || opcionCliente > clientes.size()) {
            System.out.println("Opcion"
                    + "opcion invalida. Intenta nuevamente.");
            return;
        }
        Cliente clienteSeleccionado = clientes.get(opcionCliente - 1);
        System.out.print("\n");
        while (true) {
        System.out.print("\n");
        System.out.println("\n| Mascota |");
        System.out.print("Nombre: ");
        String nombreMascota = scanner.nextLine();
        System.out.print("Especie: ");
        String especie = scanner.nextLine();
        System.out.print("Raza: ");
        String raza = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Sexo: ");
        String sexo = "";
        while (true) {
            System.out.print("| (1) Macho  | (2) Hembra  |");
            System.out.print("-> ");
            int opcionSexo = scanner.nextInt();
            scanner.nextLine();
            if (opcionSexo == 1) {
                sexo = "Macho";
                break;
            } else if (opcionSexo == 2) {
                sexo = "Hembra";
                break;
            } else {
                System.out.println("Opcion no valida");
            }
        }
        
        boolean esCastrada = false;
        while (true) {
            System.out.print("Castrado: ");
            System.out.print("| (1) Si  | (2) No  |");
            System.out.print("-> ");
            int opcionCastracion = scanner.nextInt();
            scanner.nextLine();
            if (opcionCastracion == 1) {
                esCastrada = true;
                break;
            } else if (opcionCastracion == 2) {
                esCastrada = false;
                break;
            } else {
                System.out.println("Opcion no valida");
            }
        }

        boolean esGestante = false;
        if (!esCastrada && sexo.equals("Hembra")) {
            while (true) {
                System.out.print("Gestante: ");
                System.out.print("| (1) Si  | (2) No  |");
                System.out.print("-> ");
                int opcionGestante = scanner.nextInt();
                scanner.nextLine();
                if (opcionGestante == 1) {
                    esGestante = true;
                    break;
                } else if (opcionGestante == 2) {
                    esGestante = false;
                    break;
                } else {
                    System.out.println("Opcion no valida");
                }
            }
        }
        
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Peso(en kg): ");
        double peso = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Fecha de nacimiento: ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Observaciones: ");
        String observaciones = scanner.nextLine();
        
        // FECHA ACTUAL
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String fechaRegistro = fechaActual.format(formatter);

        // Crear mascota y asignar cliente
        Mascota mascota = new Mascota(nombreMascota, especie, raza, edad, sexo, 
                color, peso, fechaNacimiento, fechaRegistro, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), observaciones, esCastrada, esGestante, clienteSeleccionado);
        
        mascota.gestionarVacunas(scanner);
        mascota.gestionarAlergias(scanner);
        mascota.gestionarEnfermedades(scanner);
        clienteSeleccionado.agregarMascota(mascota);
        this.mascotas.add(mascota);
        mascotaClienteMap.put(mascota.getNombreMascota(), clienteSeleccionado);
        System.out.print("\n");
        System.out.println("Cliente seleccionado: " + clienteSeleccionado.getNombre());
        System.out.println("Mascota registrada: " + mascota.getNombreMascota());
        int opcionAgregar = 0;
        while (true) {
            System.out.print("Agregar otra mascota: ");
            System.out.print("| (1) Si  | (2) No  |");
            System.out.print("-> ");
            opcionAgregar = scanner.nextInt();
            scanner.nextLine(); 
            if (opcionAgregar == 1) {
                break; 
            } else if (opcionAgregar == 2) {
                return; 
            } else {
                System.out.println("Opcion invalida, intente nuevamente.");
            }
        }
    }
    }
    
    public static boolean isMascotaRegistrada(String nombreMascota) {
        return mascotasRegistradas.contains(nombreMascota);
    }
    
    public void agregarHistorialMedico(String registro) {
        historialMedico.add(registro);
    }

    public ArrayList<String> getHistorialMedico() {
        return historialMedico;
    }
    
    public void gestionarVacunas(Scanner scanner){
         String[] vacunasDisponibles = vacunasEspecie();
         System.out.println("\n| Vacunas |");
        if (vacunasDisponibles.length > 0) {
            for (int i = 0; i < vacunasDisponibles.length; i++) {
                System.out.println((i + 1) + ". " + vacunasDisponibles[i]);
            }
            while (true) { 
                System.out.print("\n-> ");
                int seleccion = scanner.nextInt();
                scanner.nextLine();

                if (seleccion == 0) {
                    break;
                }

                if (seleccion > 0 && seleccion <= vacunasDisponibles.length) {
                    String vacunaSeleccionada = vacunasDisponibles[seleccion - 1];
                    System.out.print("Fecha de la vacuna: " + vacunaSeleccionada + ": ");
                    String fechaVacuna = scanner.nextLine().trim();
                    vacunas.add(vacunaSeleccionada + " - " + fechaVacuna);
                } else {
                    System.out.println("\nSeleccion no valida.");
                }
            }
        } else {
            System.out.println("\nNo hay vacunas para " + especie + ".");
        }
    }
    
    public String[] vacunasEspecie(){
        switch (especie) {
            case "Canino":
            case "canino":
            case "perro":
                return vacunasPerro;
            case "Felino":
            case "felino":
            case "gato":
                return vacunasGato;
            case "Conejo":
            case "conejo":
                return vacunasConejo;
            default:
                System.out.println("\nEspecie no reconocida o sin vacunas especificas.");
                return new String[0];
        }
    }
    
    public void gestionarAlergias(Scanner scanner) {
    String[] alergiasDisponibles = alergiasEspecie();
    System.out.println("\n| Alergias |");
    if (alergiasDisponibles.length > 0) {
            for (int i = 0; i < alergiasDisponibles.length; i++) {
                System.out.println((i + 1) + ". " + alergiasDisponibles[i]);
            }
            int opcionOtros = alergiasDisponibles.length + 1;
            System.out.println(opcionOtros + ". Otros");
        while (true) {
            System.out.print("\n-> ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();
            if (seleccion == 0) {
                break; 
            }
            
            if (seleccion > 0 && seleccion <= alergiasDisponibles.length) {
                String alergiaSeleccionada = alergiasDisponibles[seleccion - 1];
                alergias.add(alergiaSeleccionada);
            } else if (seleccion == opcionOtros) {
                System.out.print("Nombre de la alergia: ");
                String alergiaPersonalizada = scanner.nextLine().trim();
                if (!alergiaPersonalizada.isEmpty()) {
                    alergias.add(alergiaPersonalizada);
                }
            } else {
                System.out.println("\nSeleccion no valida.");
            }
        }

    } else {
        while (true) {
            System.out.print("Nombre de la alergia: ");
            String alergiaPersonalizada = scanner.nextLine().trim();

            if (alergiaPersonalizada.equals("0")) {
                break;
            }
        }
    }
    }
    
    public String[] alergiasEspecie(){
        switch (especie) {
            case "Canino":
            case "canino":
            case "perro":
                return alergiasPerro;
            case "Felino":
            case "felino":
            case "gato":
                return alergiasGato;
            case "Conejo":
            case "conejo":
                return alergiasConejo;
            default:
                System.out.println("\nEspecie no reconocida.");
                return new String[0];
        }
    }
    
    public void gestionarEnfermedades(Scanner scanner) {
    String[] enfermedadesDisponibles = enfermedadesEspecie();
    System.out.println("\n| Enfermedades |");
    
    if (enfermedadesDisponibles.length > 0) {
            for (int i = 0; i < enfermedadesDisponibles.length; i++) {
                System.out.println((i + 1) + ". " + enfermedadesDisponibles[i]);
            }
            int opcionOtros = enfermedadesDisponibles.length + 1;
            System.out.println(opcionOtros +". Otros");
        while (true) {
            System.out.print("\n-> ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();
            if (seleccion == 0) {
                break; 
            }
            if (seleccion > 0 && seleccion <= enfermedadesDisponibles.length) {
                String enfermedadSeleccionada = enfermedadesDisponibles[seleccion - 1];
                enfermedades.add(enfermedadSeleccionada);
            } else if (seleccion == opcionOtros) {
                System.out.print("Nombre de la enfermedad: ");
                String enfermedadPersonalizada = scanner.nextLine().trim();
                if (!enfermedadPersonalizada.isEmpty()) {
                    enfermedades.add(enfermedadPersonalizada);
                }
            } else {
                System.out.println("\nSeleccion no valida.");
            }
        }

    } else {
        while (true) {
            System.out.print("Nombre de la enfermedad: ");
            String enfermedadPersonalizada = scanner.nextLine().trim();
            if (enfermedadPersonalizada.equals("0")) {
                break;
            }
        }
    }
    }
    
     public String[] enfermedadesEspecie(){
        switch (especie) {
            case "Canino":
            case "canino":
            case "perro":
                return enfermedadesPerro;
            case "Felino":
            case "felino":
            case "gato":
                return enfermedadesGato;
            case "Conejo":
            case "conejo":
                return enfermedadesConejo;
            default:
                System.out.println("\nEspecie no reconocida.");
                return new String[0];
        }
    }
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getColor() {
        return color;
    }

    public double getPeso() {
        return peso;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public ArrayList<String> getVacunas() {
        return vacunas;
    }

    public ArrayList<String> getAlergias() {
        return alergias;
    }

    public ArrayList<String> getEnfermedades() {
        return enfermedades;
    }

    public boolean isCastrada() {
        return castrada;
    }

    public boolean isGestante() {
        return gestante;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setVacunas(ArrayList<String> vacunas) {
        this.vacunas = vacunas;
    }

    public void setAlergias(ArrayList<String> alergias) {
        this.alergias = alergias;
    }

    public void setEnfermedades(ArrayList<String> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public void setCastrada(boolean castrada) {
        this.castrada = castrada;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }
    
}