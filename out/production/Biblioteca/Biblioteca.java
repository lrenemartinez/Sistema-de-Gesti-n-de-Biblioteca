import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class Biblioteca {
    private HashMap<Integer, Libro> libros;
    private HashMap<String, Usuario> usuarios;
    private int siguienteIdLibro;

    public Biblioteca() {
        this.libros = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.siguienteIdLibro = 1;
    }

    public int registrarLibro(String titulo, String autor, int anio, String genero) {
        int id = siguienteIdLibro++;
        Libro l = new Libro(id, titulo, autor, anio, genero);
        libros.put(id, l);
        return id;
    }

    public boolean registrarUsuario(String id, String nombre) {
        if (usuarios.containsKey(id)) return false;
        usuarios.put(id, new Usuario(id, nombre));
        return true;
    }

    public boolean prestarLibro(int libroId, String usuarioId) {
        Libro l = libros.get(libroId);
        Usuario u = usuarios.get(usuarioId);
        if (l == null || u == null) return false;
        if (!l.isDisponible()) return false;
        l.setDisponible(false);
        l.setPrestadoA(usuarioId);
        u.prestarLibro(libroId);
        return true;
    }

    public boolean devolverLibro(int libroId) {
        Libro l = libros.get(libroId);
        if (l == null) return false;
        if (l.isDisponible()) return false;
        String usuarioId = l.getPrestadoA();
        Usuario u = usuarios.get(usuarioId);
        if (u != null) u.devolverLibro(libroId);
        l.setDisponible(true);
        l.setPrestadoA("");
        return true;
    }

    public ArrayList<Libro> listarPorGenero(String genero) {
        ArrayList<Libro> r = new ArrayList<>();
        for (Libro l : libros.values()) {
            if (l.getGenero().equalsIgnoreCase(genero)) r.add(l);
        }
        return r;
    }

    public ArrayList<Libro> listarPorAutor(String autor) {
        ArrayList<Libro> r = new ArrayList<>();
        for (Libro l : libros.values()) {
            if (l.getAutor().equalsIgnoreCase(autor)) r.add(l);
        }
        return r;
    }

    public ArrayList<Libro> listarPorDisponibilidad(boolean disponible) {
        ArrayList<Libro> r = new ArrayList<>();
        for (Libro l : libros.values()) {
            if (l.isDisponible() == disponible) r.add(l);
        }
        return r;
    }

    public Collection<Libro> getLibros() {
        return libros.values();
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios.values();
    }

    public Libro getLibro(int id) {
        return libros.get(id);
    }

    public Usuario getUsuario(String id) {
        return usuarios.get(id);
    }

    public void agregarLibro(Libro l) {
        libros.put(l.getId(), l);
        if (l.getId() >= siguienteIdLibro) siguienteIdLibro = l.getId() + 1;
    }

    public void agregarUsuario(Usuario u) {
        usuarios.put(u.getId(), u);
    }

    public void setSiguienteIdLibro(int n) {
        this.siguienteIdLibro = n;
    }
}