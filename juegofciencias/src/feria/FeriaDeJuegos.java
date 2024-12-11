package feria;

/*
 * @autor: Ulises Abdiel Cabello Cardenas
 * @version 1
 */
import data.ManejadorDeDatos;
import jugador.Jugador;
import hanoi.TorresDeHanoi;
import conecta4.Conecta4;
import cuadmagico.CuadradoMagico;
import salvado.Salvado;

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
            System.out.println("¡Hola " + nombre + "! Se te han asignado 100 créditos para empezar.");
        } else {
            System.out.println("¡Bienvenido de nuevo, " + nombre + "! Créditos disponibles: " + jugador.getCreditos());
        }

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
        
            try {
                if (scanner.hasNextLine()) {
                    String entrada = scanner.nextLine().trim(); 
        
                    if (entrada.matches("\\d+")) { 
                        int opcion = Integer.parseInt(entrada);
                        switch (opcion) {
                            case 1:
                                jugarTorresDeHanoi(jugador, scanner);
                                break;
                            case 2:
                                jugarConecta4(jugador, scanner);
                                break;
                            case 3:
                                jugarCuadradoMagico(jugador, scanner);
                                break;
                            case 4:
                                jugarSalvado(jugador); 
                                break;
                            case 5:
                                jugador.mostrarInformacion();
                                break;
                            case 6:
                                jugador.consultarPuntos();
                                break;
                            case 7:
                                mostrarPosiciones();
                                break;
                            case 8:
                                ManejadorDeDatos.guardarJugadores(jugadoresRegistrados, numJugadores);
                                continuar = false;
                                System.out.println("¡Datos guardados! Hasta pronto.");
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                        }
                    } else {
                        System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    }
                } else {
                    
                    System.out.println("No hay más datos de entrada. Saliendo del programa y guardando datos.");
                    ManejadorDeDatos.guardarJugadores(jugadoresRegistrados, numJugadores);
                    continuar = false;
                }
            } catch (Exception e) {
                System.out.println("Error inesperado. Intente nuevamente.");
                scanner.nextLine(); 
            }
        }
        
        

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Juegos ---");
        System.out.println("1. Jugar Torres de Hanoi");
        System.out.println("2. Jugar Conecta 4");
        System.out.println("3. Jugar Cuadrado Mágico");
        System.out.println("4. Jugar Salvado");
        System.out.println("5. Mostrar información del jugador");
        System.out.println("6. Consultar puntos acumulados");
        System.out.println("7. Ver posiciones de los jugadores");
        System.out.println("8. Guardar y salir");
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

    private static void jugarConecta4(Jugador jugador, Scanner scanner) {
        Conecta4 juego = new Conecta4();
        char ficha = 'X';
    
        System.out.println("\n--- Jugando Conecta 4 ---");
        boolean ganador = false;
    
        while (!ganador && !juego.tableroLleno()) {
            juego.mostrarTablero();
            System.out.print("Jugador " + jugador.getNombre() + " (" + ficha + "), elija una columna (0-6): ");
            if (scanner.hasNextInt()) {
                int columna = scanner.nextInt();
                scanner.nextLine(); 
    
                if (columna < 0 || columna > 6) {
                    System.out.println("Columna inválida. Intente nuevamente.");
                    continue;
                }
    
                if (!juego.ponerFicha(columna, ficha)) {
                    System.out.println("Columna llena. Intente nuevamente.");
                    continue;
                }
    
                if (juego.hayGanador(ficha)) {
                    juego.mostrarTablero();
                    System.out.println("¡Felicidades, " + jugador.getNombre() + "! Has ganado.");
                    jugador.agregarPuntos(10);
                    ganador = true;
                }
    
                ficha = (ficha == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine(); 
            }
        }
    
        if (!ganador && juego.tableroLleno()) {
            System.out.println("El juego terminó en empate.");
            jugador.agregarPuntos(5);
        }
    }
    

    private static void jugarCuadradoMagico(Jugador jugador, Scanner scanner) {
        CuadradoMagico juego = new CuadradoMagico();
        System.out.println("\n--- Jugando Cuadrado Mágico ---");
        boolean terminado = false;
    
        while (!terminado) {
            juego.imprimirTablero();
            System.out.print("Ingresa fila, columna y número (separados por espacios): ");
    
            if (scanner.hasNextInt()) {
                int fila = scanner.nextInt();
                int columna = scanner.nextInt();
                int numero = scanner.nextInt();
                scanner.nextLine(); 
    
                if (!juego.colocarNumero(fila, columna, numero)) {
                    System.out.println("Movimiento inválido. Intenta de nuevo.");
                    continue;
                }
    
                if (juego.esCuadradoMagico()) {
                    System.out.println("¡Felicidades, " + jugador.getNombre() + "! Has completado el cuadrado mágico.");
                    jugador.agregarPuntos(20);
                    terminado = true;
                }
            } else {
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine(); 
            }
        }
    }
    

    private static void jugarSalvado(Jugador jugador) {
        Salvado juego = new Salvado();
        juego.iniciarJuego(jugador);
    }

    private static void mostrarPosiciones() {
        System.out.println("\n--- Posiciones de los Jugadores ---");

 
        for (int i = 0; i < numJugadores - 1; i++) {
            for (int j = i + 1; j < numJugadores; j++) {
                if (jugadoresRegistrados[j].getPuntos() > jugadoresRegistrados[i].getPuntos()) {
                    Jugador temp = jugadoresRegistrados[i];
                    jugadoresRegistrados[i] = jugadoresRegistrados[j];
                    jugadoresRegistrados[j] = temp;
                }
            }
        }

        for (int i = 0; i < numJugadores; i++) {
            Jugador jugador = jugadoresRegistrados[i];
            System.out.printf("%d. %s - Puntos: %d, Créditos: %d\n", i + 1, jugador.getNombre(), jugador.getPuntos(),
                    jugador.getCreditos());
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
