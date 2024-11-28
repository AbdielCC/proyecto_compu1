
import java.util.Scanner;
import jugador.Jugador;

public class Conecta4 {
    private final int filas=6;
    private final int columnas=7;
    private final char [][] tablero;

    public Conecta4(){
        tablero = new char[filas][columnas];
    }

    public void iniciarTablero(){
        for (int i=0, i<=filas, i++) {
            for (int j=0, j<8, j++) {
                tablero [i][j] = ' ';
            }
        }
    }
    public boolean ponerFicha(int col, Jugador j) {
        for (int fila = filas - 1; fila >= 0; fila--) {
            if (tablero[fila][col] == ' ') {
                tablero[fila][col] = j;
                return true;
            }
        }
        return false;
    }

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
    public boolean tableroLleno() {
        for (int col = 0; col < columnas; col++) {
            if (tablero[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }
    public void mostrarTablero() {
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                System.out.print("[" + tablero[fila][col] + "]");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }





    public static void main(String[] args) {
        Conecta4 con4 = new Conecta4();
        Scanner scanner = new Scanner(System.in);
        Jugador j1;
        Jugador j2;

        boolean gameOver = false;

        while (!gameOver) {
            con4.mostrarTablero();
            System.out.println("Jugador " + j1 + ", elige una columna (0-6):");
            
            int col = scanner.nextInt();
            
            if (col < 0 || col >= columnas) {
                System.out.println("Columna inválida, intenta de nuevo");
                continue;
            }

            if (con4.ponerFicha(col, j1)) {
                if (con4.hayGanador(j1)) {
                    con4.mostrarTablero();
                    System.out.println("Jugador " + j1 + " gana");
                    gameOver = true;
                } else if (con4.tableroLleno()) {
                    con4.mostrarTablero();
                    System.out.println("Empate");
                    gameOver = true;
                }

                // Cambiar jugador
                j1 = ( == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Columna llena, intenta otra");
            }
        }

        scanner.close();
    }


}