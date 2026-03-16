public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int anio;
    private String genero;
    private boolean disponible;
    private String prestadoA;

    public Libro(int id, String titulo, String autor, int anio, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.genero = genero;
        this.disponible = true;
        this.prestadoA = "";
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getPrestadoA() {
        return prestadoA;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setPrestadoA(String prestadoA) {
        this.prestadoA = prestadoA;
    }

    public String toString() {
        String estado = disponible ? "Disponible" : "Prestado a " + prestadoA;
        return id + " | " + titulo + " | " + autor + " | " + anio + " | " + genero + " | " + estado;
    }
}