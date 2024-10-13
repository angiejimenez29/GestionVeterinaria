package gestionveterinaria;

import java.util.Scanner;
import java.util.ArrayList;

public class Mascota {
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
    
    private static final String[] vacunasPerro = {"Sextuple (DHPPi+L)", "Quintuple (DHPPi)", "Triple (DHP)", "Antirrabica", "Tos de las Perreras"};
    private static final String[] vacunasGato = {"Cuadruple (V4)", "Triple (V3)", "Antirrabica", "Leucemia felina", "Clamidiosis"};
    private static final String[] vacunasAve = {"Viruela aviar", "Newcastle", "Bronquitis infecciosa", "Gumboro", "Marek"};
    private static final String[] vacunasConejo = {"Mixomatosis", "Enfermedad hemorrágica viral", "Pasteurelosis"};
    
    private static final String[] alergiasPerro = {"Polen de pasto", "Picaduras de pulgas", "Pollo", "Penicilina", "Frutos secos", "Lactosa"};
    private static final String[] alergiasGato = {"Polen de flores", "Picaduras de pulgas", "Pescado", "Lactosa"};
    private static final String[] alergiasAve = {"Polen de flores", "Plumas de otras aves", "Semillas"};
    private static final String[] alergiasConejo = {"Heno de alfalfa", "Polen de flores", "Frutos secos"};

    private static final String[] enfermedadesPerro = {"Moquillo", "Parvovirus", "Hepatitis infecciosa", "Leptospirosis", "Tos de las Perreras", "Coronavirus", "Rabia", "Dermatitis"};
    private static final String[] enfermedadesGato = {"Panleucopenia", "Rinotraqueitis", "Calicivirus", "Leucemia felina", "Clamidiosis", "Rabia", "Dermatitis"};
    private static final String[] enfermedadesAve = {"Viruela aviar", "Newcastle", "Bronquitis infecciosa", "Gumboro", "Marek"};
    private static final String[] enfermedadesConejo = {"Mixomatosis", "Enfermedad hemorrhagica viral", "Pasteurelosis", "Dermatitis"};

    public Mascota(String nombre, String especie, String raza, int edad, String sexo, String color, 
                   double peso, String fechaNacimiento, String fechaRegistro,
                   ArrayList<String> vacunas, ArrayList<String> alergias, ArrayList<String> enfermedades, String observaciones, boolean castrada, boolean gestante){
        this.nombreMascota = nombre;
        this.edad = edad;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.vacunas = new ArrayList<>(vacunas);
        this.alergias = new ArrayList<>(alergias);
        this.enfermedades = new ArrayList<>(enfermedades); 
        this.observaciones = observaciones;
        this.castrada = castrada;
        this.gestante = gestante;
    }
    
    public void gestionarVacunas(Scanner scanner){
         String[] vacunasDisponibles = vacunasEspecie();
         System.out.println("\n| Vacunas |");
        if (vacunasDisponibles.length > 0) {
            for (int i = 0; i < vacunasDisponibles.length; i++) {
                System.out.println((i + 1) + ". " + vacunasDisponibles[i]);
            }
            while (true) { 
                System.out.print("\nIngrese el numero de la vacuna: ");
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
    
    private String[] vacunasEspecie(){
        switch (especie) {
            case "Canino":
            case "canino":
            case "perro":
                return vacunasPerro;
            case "Felino":
            case "felino":
            case "gato":
                return vacunasGato;
            case "Ave":
            case "ave":
                return vacunasAve;
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
            System.out.print("\nIngrese el numero de la alergia: ");
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
    
    private String[] alergiasEspecie(){
        switch (especie) {
            case "Canino":
            case "canino":
            case "perro":
                return alergiasPerro;
            case "Felino":
            case "felino":
            case "gato":
                return alergiasGato;
            case "Ave":
            case "ave":
                return alergiasAve;
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
            System.out.print("\nIngrese la opcion: ");
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
    
     private String[] enfermedadesEspecie(){
        switch (especie) {
            case "Canino":
            case "canino":
            case "perro":
                return enfermedadesPerro;
            case "Felino":
            case "felino":
            case "gato":
                return enfermedadesGato;
            case "Ave":
            case "ave":
                return enfermedadesAve;
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

}