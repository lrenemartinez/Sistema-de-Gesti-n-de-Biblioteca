import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;

public class Persistencia {
    public static boolean guardar(Biblioteca b, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("[USUARIOS]");
            bw.newLine();
            for (Usuario u : b.getUsuarios()) {
                StringBuilder lista = new StringBuilder();
                ArrayList<Integer> lp = u.getLibrosPrestados();
                for (int i = 0; i < lp.size(); i++) {
                    lista.append(lp.get(i));
                    if (i < lp.size() - 1) lista.append(",");
                }
                bw.write(u.getId() + ";" + u.getNombre() + ";" + lista.toString());
                bw.newLine();
            }
            bw.write("[LIBROS]");
            bw.newLine();
            for (Libro l : b.getLibros()) {
                bw.write(l.getId() + ";" + l.getTitulo() + ";" + l.getAutor() + ";" + l.getAnio() + ";" + l.getGenero() + ";" + (l.isDisponible() ? "1" : "0") + ";" + l.getPrestadoA());
                bw.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Biblioteca cargar(String ruta) {
        Biblioteca b = new Biblioteca();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            String seccion = "";
            ArrayList<String> usuariosTemp = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                if (linea.equals("[USUARIOS]")) {
                    seccion = "U";
                    continue;
                }
                if (linea.equals("[LIBROS]")) {
                    seccion = "L";
                    continue;
                }
                if (seccion.equals("U")) {
                    if (linea.trim().isEmpty()) continue;
                    usuariosTemp.add(linea);
                } else if (seccion.equals("L")) {
                    if (linea.trim().isEmpty()) continue;
                    String[] p = linea.split(";", -1);
                    int id = Integer.parseInt(p[0]);
                    String titulo = p[1];
                    String autor = p[2];
                    int anio = Integer.parseInt(p[3]);
                    String genero = p[4];
                    boolean disp = p[5].equals("1");
                    String prestadoA = p.length > 6 ? p[6] : "";
                    Libro l = new Libro(id, titulo, autor, anio, genero);
                    l.setDisponible(disp);
                    l.setPrestadoA(prestadoA == null ? "" : prestadoA);
                    b.agregarLibro(l);
                }
            }
            for (String uline : usuariosTemp) {
                String[] p = uline.split(";", -1);
                String idu = p[0];
                String nombre = p[1];
                Usuario u = new Usuario(idu, nombre);
                if (p.length > 2 && !p[2].isEmpty()) {
                    String[] ids = p[2].split(",");
                    for (String s : ids) {
                        try {
                            int lid = Integer.parseInt(s);
                            u.prestarLibro(lid);
                            Libro l = b.getLibro(lid);
                            if (l != null) {
                                l.setDisponible(false);
                                l.setPrestadoA(idu);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                b.agregarUsuario(u);
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }
}