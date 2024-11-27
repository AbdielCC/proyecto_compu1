package data;
/*
 * @autor: Ulises Abdiel Cabello Cardenas
 * @version 1
 */
import jugador.Jugador;

import java.io.*;
/*
 * Clase que maneja la lectura y escritura de datos
 */
public class ManejadorDeDatos {
    private static final String DIRECTORIO_DATA = "data";
    private static final String ARCHIVO_DATOS = DIRECTORIO_DATA + "/jugadores.dat";
    /*
     * Constructor privado para evitar crear instancias de la clase
     */
    private ManejadorDeDatos() {}
    /*
     * Metodo que almacena los jugadores en un archivo
     * @param Jugador[] jugadores
     * @param int numJugadores
     */
    public static void guardarJugadores(Jugador[] jugadores, int numJugadores) {
        // Crea el directorio si no existe
        File directorio = new File(DIRECTORIO_DATA);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeInt(numJugadores); // Guarda el número actual de jugadores
            for (int i = 0; i < numJugadores; i++) {
                oos.writeObject(jugadores[i]); // Serializa cada jugador
            }
            System.out.println("¡Jugadores guardados correctamente en " + ARCHIVO_DATOS + "!");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    /*
     * Metodo que carga los jugadores desde un archivo
     * @return Jugador[] arreglo de jugadores
     */
    public static Jugador[] cargarJugadores() {
        File archivo = new File(ARCHIVO_DATOS);
        if (!archivo.exists()) {
            System.out.println("No se encontraron datos previos. Iniciando con un nuevo registro.");
            return new Jugador[100]; // Tamaño inicial del arreglo
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            int numJugadores = ois.readInt(); // Número de jugadores en el archivo
            Jugador[] jugadores = new Jugador[100];
            for (int i = 0; i < numJugadores; i++) {
                jugadores[i] = (Jugador) ois.readObject();
            }
            return jugadores;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            return new Jugador[100]; // Tamaño inicial del arreglo
        }
    }
}
