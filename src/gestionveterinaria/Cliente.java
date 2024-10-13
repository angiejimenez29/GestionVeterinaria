package gestionveterinaria;

public class Cliente {
    private String nombre;
    private String apellido;
    private String telefono;

    public Cliente(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public void mostrarInfo() {
        System.out.println("Cliente: " + nombre + ", Telefono: " + telefono);
    }
}
