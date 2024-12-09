    /*
     * Clase del juego Conecta4
     * @author Fernanda Muñoz
     * @version 1.0
     */
import java.util.Scanner;
import jugador.Jugador;
    /*
     * Atributos de Conecta4
     */
public class Conecta4 {
    private final int filas=6;
    private final int columnas=7;
    private final char [][] tablero;
    /*
     * Constructor
     */
    public Conecta4(){
        tablero = new char[filas][columnas];
    }
    /*
     * Metodo para iniciar un tablero de Conecta 4
     */
    public void iniciarTablero(){
        for (int i=0, i<=filas, i++) {
            for (int j=0, j<8, j++) {
                tablero [i][j] = ' ';
            }
        }
    }
    /*
     * Metodo para colocar una ficha si está disponible
     * @param int col
     * @param Jugador j 
     */
    public boolean ponerFicha(int col, Jugador j) {
        for (int fila = filas - 1; fila >= 0; fila--) {
            if (tablero[fila][col] == ' ') {
                tablero[fila][col] = j;
                return true;
            }
        }
        return false;
    }
    /*
     * Metodo que indica si ya hay un ganador
     * @param Jugador j
     */
    public boolean hayGanador (Jugador j) {
        // Revisión horizontal
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col <= columnas - 4; col++) {
                if (tablero[fila][col] == j && 
                    tablero[fila][col+1] == j && 
                    tablero[fila][col+2] == j && 
                    tablero[fila][col+3] == j) {
                    return true;
                }
            }
        }
        // Revisión vertical
        for (int col = 0; col < columnas; col++) {
            for (int fila = 0; fila <= filas - 4; fila++) {
                if (tablero[fila][col] == j && 
                    tablero[fila+1][col] == j && 
                    tablero[fila+2][col] == j && 
                    tablero[fila+3][col] == j) {
                    return true;
                }
            }
        }
        // Revisión diagonal
        for (int fila = 0; fila <= filas - 4; fila++) {
            for (int col = 0; col <= columnas - 4; col++) {
                // Diagonal descendiente
                if (tablero[fila][col] == j && 
                    tablero[fila+1][col+1] == j && 
                    tablero[fila+2][col+2] == j && 
                    tablero[fila+3][col+3] == j) {
                    return true;
                }
                // Diagonal ascendiente
                if (tablero[fila+3][col] == j && 
                    tablero[fila+2][col+1] == j && 
                    tablero[fila+1][col+2] == j && 
                    tablero[fila][col+3] == j) {
                    return true;
                }
            }
        }
        return false;
    }
    /*
     * Metodo que indica si el tablero se ha llenado
     */
    public boolean tableroLleno() {
        for (int col = 0; col < columnas; col++) {
            if (tablero[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }
    /*
     * Metodo que muestra el tablero del juego
     */
    public void mostrarTablero() {
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                System.out.print("[" + tablero[fila][col] + "]");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }
    /*
     * Metodo que inicia el juego de Conecta4
     */
    public void iniciarConecta4() {
        Conecta4 con4 = new Conecta4();
        Scanner scanner = new Scanner(System.in);
        Jugador j1;
        Jugador j2;
        Jugador jugadorActual=j1;

        boolean gameOver = false;

        while (!gameOver) {
            con4.mostrarTablero();
            System.out.println("Jugador " + jugadorActual + ", elige una columna (0-6):");
            
            int col = scanner.nextInt();
            
            if (col < 0 || col >= columnas) {
                System.out.println("Columna inválida, intenta de nuevo");
                continue;
            }

            if (con4.ponerFicha(col, jugadorActual)) {
                if (con4.hayGanador(jugadorActual)) {
                    con4.mostrarTablero();
                    System.out.println("Jugador " + jugadorActual + " gana");
                    gameOver = true;
                } else if (con4.tableroLleno()) {
                    con4.mostrarTablero();
                    System.out.println("Empate");
                    gameOver = true;
                }

                // Cambiar jugador
                jugadorActual = (jugadorActual.equals(j1)) ? j2 : j1;
            } else {
                System.out.println("Columna llena, intenta otra");
            }
        }

        scanner.close();
    }


}