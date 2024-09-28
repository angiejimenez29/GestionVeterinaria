package gestionveterinaria;

public class Cita {
    private String fecha;
    private String mascotaNombre;

    public Cita(String fecha, String mascotaNombre) {
        this.fecha = fecha;
        this.mascotaNombre = mascotaNombre;
    }

    public void mostrarInfo() {
        System.out.println("Cita el " + fecha + " para la mascota: " + mascotaNombre);
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }
}

