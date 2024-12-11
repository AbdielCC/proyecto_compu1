package salvado;
/*
 * @autor: Ulises Abdiel Cabello Cardenas
 * @version 1
 */
import java.util.Random;
import java.util.Scanner;
import jugador.Jugador;

/**
 * Clase que representa el juego Salvado.
 */
public class Salvado {

    /**
     * Inicia el juego de Salvado.
     * @param jugador El jugador que participa en el juego.
     */
    public void iniciarJuego(Jugador jugador) {
        System.out.println("\n--- Jugando Salvado ---");
        Random random = new Random();
        int i = random.nextInt(100) + 1; // Número aleatorio entre 1 y 100
        int[] circulo = new int[100];

        for (int j = 0; j < 100; j++) {
            circulo[j] = j + 1;
        }

        int eliminados = 0;
        int posicionActual = 0;

        while (eliminados < 99) {
            int pasos = i % (100 - eliminados);
            posicionActual = (posicionActual + pasos) % (100 - eliminados);

            // Eliminar al jugador
            for (int j = posicionActual; j < 99 - eliminados; j++) {
                circulo[j] = circulo[j + 1];
            }

            eliminados++;
        }

        int sillaSalvada = circulo[0];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Intenta adivinar cuál es la silla que salva: ");
        int prediccion = scanner.nextInt();

        if (prediccion == sillaSalvada) {
            System.out.println("¡Correcto! La silla que salva es la número " + sillaSalvada + ".");
            jugador.agregarPuntos(12);
        } else {
            System.out.println("Incorrecto. La silla que salva es la número " + sillaSalvada + ".");
            jugador.agregarPuntos(2);
        }
        System.out.println("Puntos actuales: " + jugador.getPuntos());
        scanner.close();
    }
}
