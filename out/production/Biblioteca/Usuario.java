import java.util.ArrayList;

public class Usuario {
    private String id;
    private String nombre;
    private ArrayList<Integer> librosPrestados;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.librosPrestados = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Integer> getLibrosPrestados() {
        return librosPrestados;
    }

    public void prestarLibro(int libroId) {
        if (!librosPrestados.contains(libroId)) librosPrestados.add(libroId);
    }

    public void devolverLibro(int libroId) {
        librosPrestados.remove(Integer.valueOf(libroId));
    }

    public String toString() {
        return id + " | " + nombre + " | Prestados: " + librosPrestados.toString();
    }
}