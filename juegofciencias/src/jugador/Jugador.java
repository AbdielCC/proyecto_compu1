/*
 * @author: Ulises Abdiel Cabello Cardenas
 * @version 1
 */
package jugador;

import java.io.Serializable;

/*
 * Clase que representa el jugador
 */
public class Jugador implements Serializable {
    private String nombre;
    private int creditos;
    private int puntos;

    /*
     * Constructor de la clase jugador
     * 
     * @param String nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.creditos = 100;
        this.puntos = 0;
    }

    /*
     * Metodo que regresa el nombre del jugador
     * 
     * @return String nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /*
     * Metodo que regresa los creditos del jugador
     * 
     * @return int creditos del jugador
     */
    public int getCreditos() {
        return creditos;
    }

    /*
     * Metodo que regresa los puntos del jugador
     * 
     * @return int puntos del jugador
     */
    public int getPuntos() {
        return puntos;
    }

    /*
     * Metodo que agrega creditos al jugador
     * 
     * @param int creditos a agregar
     */
    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    /*
     * Metodo que quita creditos al jugador
     * 
     * @param int creditos a agregar
     */
    public void restarCreditos(int costo) {
        this.creditos -= costo;
    }

    /*
     * Metodo que regresa si el jugador tiene creditos suficientes
     * 
     * @param int costo de la operacion
     * 
     * @return boolean si tiene creditos suficientes
     */
    public boolean tieneCreditos(int costo) {
        return this.creditos >= costo;
    }

    /*
     * Metodo que muestra la informacion del jugador
     */
    public void mostrarInformacion() {
        System.out.println("\n--- Información del Jugador ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Créditos: " + creditos);
        System.out.println("Puntos: " + puntos);
    }

    /*
     * Metodo que muestra los puntos del jugador
     */
    public void consultarPuntos() {
        System.out.println("\n--- Puntos Acumulados ---");
        System.out.println("Puntos actuales: " + puntos);
    }

    /*
     * Metodo que regresa si el juego está completo
     */
    public static Jugador buscarJugador(String nombre, Jugador[] jugadores, int numJugadores) {
        for (int i = 0; i < numJugadores; i++) {
            if (jugadores[i] != null && jugadores[i].getNombre().equalsIgnoreCase(nombre)) {
                return jugadores[i];
            }
        }
        return null;
    }

}
