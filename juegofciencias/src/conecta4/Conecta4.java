package conecta4;

import java.util.Scanner;
import jugador.Jugador;

/**
 * Clase del juego Conecta4
 * @author Fernanda Muñoz
 * @version 1.0
 */
public class Conecta4 {
    private final int filas = 6;
    private final int columnas = 7;
    private final char[][] tablero;

    /**
     * Constructor
     */
    public Conecta4() {
        tablero = new char[filas][columnas];
        iniciarTablero();
    }

    /**
     * Metodo para iniciar un tablero de Conecta 4
     */
    public void iniciarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    /**
     * Metodo para colocar una ficha si está disponible
     * @param col Columna donde se coloca la ficha
     * @param ficha Ficha del jugador ('X' o 'O')
     * @return True si se colocó correctamente, False si no
     */
    public boolean ponerFicha(int col, char ficha) {
        for (int fila = filas - 1; fila >= 0; fila--) {
            if (tablero[fila][col] == ' ') {
                tablero[fila][col] = ficha;
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que indica si ya hay un ganador
     * @param ficha Ficha del jugador ('X' o 'O')
     * @return True si hay ganador, False si no
     */
    public boolean hayGanador(char ficha) {
        // Revisión horizontal
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col <= columnas - 4; col++) {
                if (tablero[fila][col] == ficha &&
                    tablero[fila][col + 1] == ficha &&
                    tablero[fila][col + 2] == ficha &&
                    tablero[fila][col + 3] == ficha) {
                    return true;
                }
            }
        }
        // Revisión vertical
        for (int col = 0; col < columnas; col++) {
            for (int fila = 0; fila <= filas - 4; fila++) {
                if (tablero[fila][col] == ficha &&
                    tablero[fila + 1][col] == ficha &&
                    tablero[fila + 2][col] == ficha &&
                    tablero[fila + 3][col] == ficha) {
                    return true;
                }
            }
        }
        // Revisión diagonal descendiente
        for (int fila = 0; fila <= filas - 4; fila++) {
            for (int col = 0; col <= columnas - 4; col++) {
                if (tablero[fila][col] == ficha &&
                    tablero[fila + 1][col + 1] == ficha &&
                    tablero[fila + 2][col + 2] == ficha &&
                    tablero[fila + 3][col + 3] == ficha) {
                    return true;
                }
            }
        }
        // Revisión diagonal ascendiente
        for (int fila = 3; fila < filas; fila++) {
            for (int col = 0; col <= columnas - 4; col++) {
                if (tablero[fila][col] == ficha &&
                    tablero[fila - 1][col + 1] == ficha &&
                    tablero[fila - 2][col + 2] == ficha &&
                    tablero[fila - 3][col + 3] == ficha) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo que indica si el tablero se ha llenado
     * @return True si el tablero está lleno, False si no
     */
    public boolean tableroLleno() {
        for (int col = 0; col < columnas; col++) {
            if (tablero[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que muestra el tablero del juego
     */
    public void mostrarTablero() {
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                System.out.print("[" + tablero[fila][col] + "]");
            }
            System.out.println();
        }
        System.out.println(" 0  1  2  3  4  5  6");
    }

    /**
     * Metodo que inicia el juego de Conecta4
     */
    public void iniciarConecta4(Jugador j1, Jugador j2) {
        Scanner scanner = new Scanner(System.in);
        char fichaActual = 'X';
        Jugador jugadorActual = j1;

        boolean gameOver = false;

        while (!gameOver) {
            mostrarTablero();
            System.out.println("Turno del jugador " + jugadorActual.getNombre() + " (" + fichaActual + ")");
            System.out.print("Elige una columna (0-6): ");

            int col = scanner.nextInt();
            if (col < 0 || col >= columnas) {
                System.out.println("Columna inválida, intenta de nuevo.");
                continue;
            }

            if (ponerFicha(col, fichaActual)) {
                if (hayGanador(fichaActual)) {
                    mostrarTablero();
                    System.out.println("¡Felicidades, " + jugadorActual.getNombre() + "! Has ganado.");
                    jugadorActual.agregarPuntos(10);
                    gameOver = true;
                } else if (tableroLleno()) {
                    mostrarTablero();
                    System.out.println("El juego terminó en empate.");
                    j1.agregarPuntos(5);
                    j2.agregarPuntos(5);
                    gameOver = true;
                } else {
                    // Cambiar turno
                    fichaActual = (fichaActual == 'X') ? 'O' : 'X';
                    jugadorActual = (jugadorActual == j1) ? j2 : j1;
                }
            } else {
                System.out.println("Columna llena, intenta de nuevo.");
            }
        }
        scanner.close();
    }
}
