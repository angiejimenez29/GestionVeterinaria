package gestionveterinaria;

public class Cliente {
    private String nombre;
    private String telefono;

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public void mostrarInfo() {
        System.out.println("Cliente: " + nombre + ", Telefono: " + telefono);
    }
}
