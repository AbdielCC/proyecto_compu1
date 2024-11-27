package feria;
/*
 * @autor: Ulises Abdiel Cabello Cardenas
 * @version 1
 */
import data.ManejadorDeDatos;
import jugador.Jugador;
import hanoi.TorresDeHanoi;

import java.util.Scanner;
/*
 * Clase que representa la feria de juegos
 */
public class FeriaDeJuegos {
    private static Jugador[] jugadoresRegistrados;
    private static int numJugadores = 0;

    public static void main(String[] args) {
        jugadoresRegistrados = ManejadorDeDatos.cargarJugadores();
        numJugadores = contarJugadores(jugadoresRegistrados);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        Jugador jugador = Jugador.buscarJugador(nombre, jugadoresRegistrados, numJugadores);
        if (jugador == null) {
            jugador = new Jugador(nombre);
            jugadoresRegistrados[numJugadores++] = jugador;
            System.out.println("¡Hola " + nombre + "Se te han asignado 100 créditos para empezar.");
        } else {
            System.out.println("¡Bienvenido de nuevo, " + nombre + "Créditos disponibles: " + jugador.getCreditos());
        }

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            // Controlador de opciones
            switch (opcion) {
                case 1:
                    jugarTorresDeHanoi(jugador, scanner);
                    break;
                case 2:
                    jugador.mostrarInformacion();
                    break;
                case 3:
                    jugador.consultarPuntos();
                    break;
                case 4:
                    mostrarPosiciones();
                    break;
                case 5:
                    ManejadorDeDatos.guardarJugadores(jugadoresRegistrados, numJugadores);
                    continuar = false;
                    System.out.println("¡Datos guardados! Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Juegos ---");
        System.out.println("1. Jugar Torres de Hanoi");
        System.out.println("2. Mostrar información del jugador");
        System.out.println("3. Consultar puntos acumulados");
        System.out.println("4. Ver posiciones de los jugadores");
        System.out.println("5. Guardar y salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void jugarTorresDeHanoi(Jugador jugador, Scanner scanner) {
        System.out.println("\n--- Torres de Hanoi ---");
        if (jugador.tieneCreditos(15)) {
            jugador.restarCreditos(15);
            TorresDeHanoi juego = new TorresDeHanoi(6, jugador);
            juego.iniciarJuego();
        } else {
            System.out.println("No tienes suficientes créditos para jugar.");
        }
    }

    private static void mostrarPosiciones() {
        System.out.println("\n--- Posiciones de los Jugadores ---");

        // Ordenar jugadores por puntos
        for (int i = 0; i < numJugadores - 1; i++) {
            for (int j = i + 1; j < numJugadores; j++) {
                if (jugadoresRegistrados[j].getPuntos() > jugadoresRegistrados[i].getPuntos()) {
                    Jugador temp = jugadoresRegistrados[i];
                    jugadoresRegistrados[i] = jugadoresRegistrados[j];
                    jugadoresRegistrados[j] = temp;
                }
            }
        }

        // Imprimir posiciones
        for (int i = 0; i < numJugadores; i++) {
            Jugador jugador = jugadoresRegistrados[i];
            System.out.printf("%d. %s - Puntos: %d, Créditos: %d\n", i + 1, jugador.getNombre(), jugador.getPuntos(), jugador.getCreditos());
        }
    }

    private static int contarJugadores(Jugador[] jugadores) {
        int contador = 0;
        for (Jugador jugador : jugadores) {
            if (jugador != null) {
                contador++;
            }
        }
        return contador;
    }
}
