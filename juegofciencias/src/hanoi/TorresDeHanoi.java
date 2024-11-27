package hanoi;
/*
 * @autor: Ulises Abdiel Cabello Cardenas
 * @version 1
 */
import java.util.Scanner;

import jugador.Jugador;
/*
 * Clase que representa el juego de las torres de hanoi
 */
public class TorresDeHanoi {
    private int[][] postes;
    private int discos;
    private int movimientos;
    private Jugador jugador;
    /*
     * Constructor de la clase TorresDeHanoi
     * @param int discos
     * @param Jugador jugador
     */
    public TorresDeHanoi(int discos, Jugador jugador) {
        this.discos = discos;
        this.movimientos = 0;
        this.jugador = jugador;
        this.postes = new int[3][discos];
        inicializarPostes();
    }
    /*
     * Metodo que inicializa los postes con los discos correspondientes
     */
    private void inicializarPostes() {
        for (int i = 0; i < discos; i++) {
            postes[0][i] = discos - i; 
        }
    }
    /*
     * Metodo que muestra los postes
     */
    public void mostrarPostes() {
        System.out.println("Estado actual de los postes:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Poste " + (i + 1) + ": ");
            for (int j = 0; j < discos; j++) {
                if (postes[i][j] != 0) {
                    System.out.print(postes[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    /*
     * Metodo que mueve un disco de un poste a otro
     * @param int origen
     * @param int destino
     * @return boolean si se pudo mover el disco
     */
    public boolean moverDisco(int origen, int destino) {
        origen--;
        destino--;

        int discoOrigen = obtenerDiscoSuperior(origen);
        if (discoOrigen == -1) {
            System.out.println("El poste de origen está vacío.");
            return false;
        }

        int discoDestino = obtenerDiscoSuperior(destino);

        if (discoDestino != -1 && discoDestino < discoOrigen) {
            System.out.println("No se puede colocar un disco grande sobre uno más pequeño.");
            return false;
        }

        quitarDisco(origen, discoOrigen);
        agregarDisco(destino, discoOrigen);
        movimientos++;
        return true;
    }
    /*
     * Metodo que regresa el disco superior de un poste
     * @param int poste
     * @return int disco superior
     */
    private int obtenerDiscoSuperior(int poste) {
        for (int i = 0; i < discos; i++) {
            if (postes[poste][i] != 0) {
                return postes[poste][i];
            }
        }
        return -1; // Poste vacío.
    }
    /*
     * Metodo que quita un disco de un poste
     * @param int poste
     * @param int disco
     */
    private void quitarDisco(int poste, int disco) {
        for (int i = 0; i < discos; i++) {
            if (postes[poste][i] == disco) {
                postes[poste][i] = 0;
                break;
            }
        }
    }
    /*
     * Metodo que agrega un disco a un poste
     * @param int poste
     * @param int disco
     */
    private void agregarDisco(int poste, int disco) {
        for (int i = discos - 1; i >= 0; i--) {
            if (postes[poste][i] == 0) {
                postes[poste][i] = disco;
                break;
            }
        }
    }
    /*
     * Metodo que regresa si el juego está completo
     * @return boolean si el juego está completo
     */
    public boolean estaCompleto() {
        for (int i = 0; i < discos; i++) {
            if (postes[2][i] == 0) {
                return false;
            }
        }
        return true;
    }
    /*
     * Metodo que regresa los movimientos
     * @return int movimientos
     */
    public int getMovimientos() {
        return movimientos;
    }
    /*
     * Metodo que inicia el juego
     */
    public void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al juego de Torres de Hanoi, " + jugador.getNombre() + "!");
        
        while (true) {
            mostrarPostes();
            System.out.print("Ingrese el poste de origen (1-3): ");
            int origen = scanner.nextInt();
            System.out.print("Ingrese el poste de destino (1-3): ");
            int destino = scanner.nextInt();

            if (!moverDisco(origen, destino)) {
                continue;
            }

            if (estaCompleto()) {
                System.out.println("Felicidades! Has completado el juego. y te has ganado un cheto");
                int movimientos = getMovimientos();

                if (movimientos == 63) {
                    jugador.agregarPuntos(10);
                } else if (movimientos <= 73) {
                    jugador.agregarPuntos(5);
                } else {
                    jugador.agregarPuntos(2);
                }

                System.out.println("Puntos obtenidos: " + jugador.getPuntos());
                break;
            }
        }
        scanner.close();
    }
}
