package cuadmagico;

import java.util.Arrays;
import java.util.Random;

/**
 * Clase del juego Cuadrado Mágico
 * @author Fernanda Muñoz
 * @version 1.0
 */
public class CuadradoMagico {
    /*
     * Atributos de clase
     */
    private int[][] tablero;
    private int[] numerosUsados; 
    private int[] posicionInicial; 
    private static final int TAMANO = 4;
    private static final int CONSTANTE_MAGICA = 34;

    /**
     * Constructor
     */
    public CuadradoMagico() {
        tablero = new int[TAMANO][TAMANO];
        numerosUsados = new int[16]; 
        posicionInicial = new int[4]; 
        inicializarNumerosUsados();
        generarPosicionInicial();
        inicializarTablero();
    }

    /**
     * Inicializa el arreglo de números usados con ceros
     */
    private void inicializarNumerosUsados() {
        Arrays.fill(numerosUsados, 0); 
    }

    /**
     * Metodo que elige aleatoriamente 4 números diferentes del 1-16
     */
    private void generarPosicionInicial() {
        Random rand = new Random();
        int count = 0;
        while (count < 4) {
            int num = rand.nextInt(16) + 1; 
            if (numerosUsados[num - 1] == 0) {
                posicionInicial[count++] = num;
                numerosUsados[num - 1] = 1; 
            }
        }
    }

    /**
     * Metodo que inicia el tablero de Cuadrado Mágico
     */
    private void inicializarTablero() {
        Random rand = new Random();
        int lineaInicial = rand.nextInt(TAMANO); 
        for (int i = 0; i < TAMANO; i++) {
            Arrays.fill(tablero[i], 0);
        }
        for (int j = 0; j < TAMANO; j++) {
            tablero[lineaInicial][j] = posicionInicial[j];
        }
    }

    /**
     * Método para colocar un número en el tablero
     * @param fila Fila donde colocar el número
     * @param columna Columna donde colocar el número
     * @param numero Número a colocar
     * @return True si se colocó correctamente, False si no
     */
    public boolean colocarNumero(int fila, int columna, int numero) {
        if (fila < 0 || fila >= TAMANO || columna < 0 || columna >= TAMANO) {
            System.out.println("Posición fuera de rango.");
            return false;
        }
        if (tablero[fila][columna] != 0 || numerosUsados[numero - 1] == 1) {
            System.out.println("Celda ocupada o número ya usado.");
            return false;
        }
        tablero[fila][columna] = numero;
        numerosUsados[numero - 1] = 1;
        return true;
    }

    /**
     * Método que verifica si se cumplen las condiciones básicas del cuadrado mágico
     * @return True si es posible formar un cuadrado mágico, False si no
     */
    public boolean verificar() {
        for (int[] fila : tablero) {
            long count = Arrays.stream(fila).filter(x -> x != 0).count();
            if (count != Arrays.stream(fila).distinct().count()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que verifica si ya es un cuadrado mágico
     * @return True si el tablero es un cuadrado mágico, False si no
     */
    public boolean esCuadradoMagico() {
        if (Arrays.stream(numerosUsados).sum() != 16) {
            return false;
        }
        // Verificar suma de filas
        for (int[] fila : tablero) {
            if (Arrays.stream(fila).sum() != CONSTANTE_MAGICA) {
                return false;
            }
        }
        // Verificar suma de columnas
        for (int col = 0; col < TAMANO; col++) {
            int sumaColumna = 0;
            for (int fila = 0; fila < TAMANO; fila++) {
                sumaColumna += tablero[fila][col];
            }
            if (sumaColumna != CONSTANTE_MAGICA) {
                return false;
            }
        }
        // Verificar suma de diagonales
        int sumaDiagonal1 = 0;
        int sumaDiagonal2 = 0;
        for (int i = 0; i < TAMANO; i++) {
            sumaDiagonal1 += tablero[i][i];
            sumaDiagonal2 += tablero[i][TAMANO - 1 - i];
        }
        return sumaDiagonal1 == CONSTANTE_MAGICA && sumaDiagonal2 == CONSTANTE_MAGICA;
    }

    /**
     * Metodo que muestra el tablero 
     */
    public void imprimirTablero() {
        for (int[] fila : tablero) {
            for (int valor : fila) {
                System.out.print((valor == 0 ? "- " : valor + " "));
            }
            System.out.println();
        }
    }

    /**
     * Metodo que retorna la posición inicial generada
     * @return Arreglo de los números iniciales
     */
    public int[] getPosicionInicial() {
        return posicionInicial;
    }
}
