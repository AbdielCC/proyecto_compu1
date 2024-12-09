    /*
     * Clase del juego Cuadrado Mágico
     * @author Fernanda Muñoz
     * @version 1.0
     */
import java.util.*;
class CuadradoMagico {
    /*
     * Atributos de clase
     */
    private int[][] tablero;
    private Set<Integer> numerosUsados;
    private List<Integer> posicionInicial;
    private static final int TAMANO = 4;
    private static final int CONSTANTE_MAGICA = 34;
    /*
     * Metodo que inicia tablero de 4x4
     */
    public CuadradoMagico() {
        tablero = new int[TAMANO][TAMANO];
        numerosUsados = new HashSet<>();
        posicionInicial = generarPosicionInicial();
        inicializarTablero();
    }
    /*
     * Metodo que elige aleatoriamente 4 números diferentes del 1-16
     */
    private List<Integer> generarPosicionInicial() {
        List<Integer> opciones = new ArrayList<>(Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
        ));
        Collections.shuffle(opciones);
        return opciones.subList(0, 4);
    }
    /*
     * Metodo inicia el rtablero de cuadrado mágico
     */
    private void inicializarTablero() {
        // Distribuir los números iniciales
        Random rand = new Random();
        int lineaInicial = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == lineaInicial) {
                for (int j = 0; j < 4; j++) {
                    tablero[i][j] = posicionInicial.get(j);
                    numerosUsados.add(posicionInicial.get(j));
                }
            }
        }
    }
    /*
     * Método para colocar número en el tablero
     */
    public boolean colocarNumero(int fila, int columna, int numero) {
        // Validar si la celda está vacía y el número no ha sido usado
        if (tablero[fila][columna] != 0 || numerosUsados.contains(numero)) {
            return false;
        }

        tablero[fila][columna] = numero;
        numerosUsados.add(numero);
        return true;
    }
    /*
     * Método que verifica si es posible formar un cuadrado mágico
     */
    public boolean verificar() {
        for (int[] fila : tablero) {
            if (Arrays.stream(fila).filter(x -> x != 0).distinct().count() != 
                Arrays.stream(fila).filter(x -> x != 0).count()) {
                return false;
            }
        }
        return true;
    }
    /*
     * Metodo que verifica si ya es un cuadrado mágico
     */
    public boolean esCuadradoMagico() {
        // checar si todos los números están usados
        if (numerosUsados.size() != 16) {
            return false;
        }
        // checar suma de filas
        for (int[] fila : tablero) {
            if (Arrays.stream(fila).sum() != CONSTANTE_MAGICA) {
                return false;
            }
        }
        // checar suma de columnas
        for (int col = 0; col < TAMANO; col++) {
            int sumaColumna = 0;
            for (int fila = 0; fila < TAMANO; fila++) {
                sumaColumna += tablero[fila][col];
            }
            if (sumaColumna != CONSTANTE_MAGICA) {
                return false;
            }
        }
        // checar diagonales
        int sumaDiagonal1 = tablero[0][0] + tablero[1][1] + tablero[2][2] + tablero[3][3];
        int sumaDiagonal2 = tablero[0][3] + tablero[1][2] + tablero[2][1] + tablero[3][0];

        return sumaDiagonal1 == CONSTANTE_MAGICA && sumaDiagonal2 == CONSTANTE_MAGICA;
    }
    /*
     * Metodo que muestra el tablero 
     */
    public void imprimirTablero() {
        for (int[] fila : tablero) {
            for (int valor : fila) {
                System.out.print(valor == 0 ? "- " : valor + " ");
            }
            System.out.println();
        }
    }

    public List<Integer> getPosicionInicial() {
        return posicionInicial;
    }
}
