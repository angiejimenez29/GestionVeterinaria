package gestionveterinaria;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String apellido;
    private String telefono;
    private ArrayList<Mascota> mascotas;

    public Cliente(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mascotas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return apellido;
    }

    public void agregarMascota(Mascota mascota){
        this.mascotas.add(mascota);
    }
    
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
}
