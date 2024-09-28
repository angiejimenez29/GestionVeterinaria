package gestionveterinaria;

public class Personal {
    private String nombre;
    private String puesto;

    public Personal(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + ", Puesto: " + puesto);
    }
}
