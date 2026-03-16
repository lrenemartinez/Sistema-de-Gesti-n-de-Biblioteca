import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Listar libros");
            System.out.println("6. Guardar datos");
            System.out.println("7. Cargar datos");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            String op = sc.nextLine();
            switch (op) {
                case "1":
                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Anio: ");
                    int anio;
                    try {
                        anio = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Anio invalido");
                        break;
                    }
                    System.out.print("Genero: ");
                    String genero = sc.nextLine();
                    int id = biblioteca.registrarLibro(titulo, autor, anio, genero);
                    System.out.println("Libro registrado con id " + id);
                    break;
                case "2":
                    System.out.print("ID del usuario: ");
                    String uid = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    if (biblioteca.registrarUsuario(uid, nombre)) {
                        System.out.println("Usuario registrado");
                    } else {
                        System.out.println("Usuario ya existe");
                    }
                    break;
                case "3":
                    System.out.print("ID libro: ");
                    int idPrestamo;
                    try {
                        idPrestamo = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("ID invalido");
                        break;
                    }
                    System.out.print("ID usuario: ");
                    String usuarioPrestamo = sc.nextLine();
                    if (biblioteca.prestarLibro(idPrestamo, usuarioPrestamo)) {
                        System.out.println("Prestamo realizado");
                    } else {
                        System.out.println("No se pudo realizar el prestamo");
                    }
                    break;
                case "4":
                    System.out.print("ID libro: ");
                    int idDev;
                    try {
                        idDev = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("ID invalido");
                        break;
                    }
                    if (biblioteca.devolverLibro(idDev)) {
                        System.out.println("Devolucion realizada");
                    } else {
                        System.out.println("No se pudo realizar la devolucion");
                    }
                    break;
                case "5":
                    System.out.println("1. Por genero");
                    System.out.println("2. Por autor");
                    System.out.println("3. Por disponibilidad");
                    System.out.println("4. Todos");
                    System.out.print("Opcion: ");
                    String opList = sc.nextLine();
                    ArrayList<Libro> lista = new ArrayList<>();
                    if (opList.equals("1")) {
                        System.out.print("Genero: ");
                        String g = sc.nextLine();
                        lista = biblioteca.listarPorGenero(g);
                    } else if (opList.equals("2")) {
                        System.out.print("Autor: ");
                        String a = sc.nextLine();
                        lista = biblioteca.listarPorAutor(a);
                    } else if (opList.equals("3")) {
                        System.out.print("Disponibles? (s/n): ");
                        String d = sc.nextLine();
                        boolean disp = d.equalsIgnoreCase("s");
                        lista = biblioteca.listarPorDisponibilidad(disp);
                    } else if (opList.equals("4")) {
                        lista = new ArrayList<>(biblioteca.getLibros());
                    } else {
                        System.out.println("Opcion invalida");
                        break;
                    }
                    if (lista.isEmpty()) {
                        System.out.println("Sin resultados");
                    } else {
                        for (Libro l : lista) System.out.println(l);
                    }
                    break;
                case "6":
                    System.out.print("Ruta archivo (ej: datos.txt): ");
                    String rutaG = sc.nextLine();
                    if (Persistencia.guardar(biblioteca, rutaG)) {
                        System.out.println("Datos guardados");
                    } else {
                        System.out.println("Error al guardar");
                    }
                    break;
                case "7":
                    System.out.print("Ruta archivo (ej: datos.txt): ");
                    String rutaC = sc.nextLine();
                    Biblioteca cargada = Persistencia.cargar(rutaC);
                    if (cargada != null) {
                        biblioteca = cargada;
                        System.out.println("Datos cargados");
                    } else {
                        System.out.println("Error al cargar");
                    }
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
            System.out.println();
        }
        sc.close();
    }
}