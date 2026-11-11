package retoAlmingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class jugador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int filas = carton.fila;
        int columnas = carton.colum;
        int[][] cartonMatriz = new int[filas][columnas];

        carton.llenar(cartonMatriz);
        
        System.out.println("Jugador conectado a la red.");
        Partida.mostrarCarton(cartonMatriz, filas, columnas);
        
        int ultimoNumero = -1;
        boolean[] filasCantadas = new boolean[filas];
        int premios = 0;
        
        while (true) {
            int numeroLeido = 0;
            boolean leido = false;
            
            try {
                File archivo = new File("\\\\DESKTOP-K5FUK6J\\retoAlmingo\\src\\retoAlmingo\\numeros.txt");
                Scanner lector = new Scanner(archivo);
                
                if (lector.hasNextInt()) {
                    numeroLeido = lector.nextInt();
                    leido = true;
                }
                lector.close();
                
            } catch (FileNotFoundException e) {
                System.out.println("Esperando a que el servidor tire una bola...");
                try { Thread.sleep(2000); } catch (InterruptedException ex) {}
                continue; 
            }
            
            if (leido) {
                if (numeroLeido == ultimoNumero) {
                    try { Thread.sleep(1000); } catch (InterruptedException ex) {}
                    continue;
                }
                
                ultimoNumero = numeroLeido;
                System.out.println("\n----------------------------------");
                System.out.println("TURNO NUEVO - Numero sacado por el servidor: " + numeroLeido);
                System.out.println("----------------------------------");
                
                boolean opcionValida = false;
                while (!opcionValida) {
                    System.out.println("\nElige una opcion:");
                    System.out.println("1. Si tengo el numero");
                    System.out.println("2. No tengo el numero");
                    System.out.println("3. Revisar historial");
                    System.out.println("4. LINEA!!!");
                    System.out.print("Opcion (1-4): ");
                    
                    if (!sc.hasNextInt()) {
                        System.out.println("Introduce un numero valido.");
                        sc.next();
                        continue;
                    }
                    
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    
                    if (opcion == 1) {
                        boolean encontrado = false;
                        for (int i = 0; i < filas; i++) {
                            for (int j = 0; j < columnas; j++) {
                                if (cartonMatriz[i][j] == numeroLeido) {
                                    cartonMatriz[i][j] = -1; 
                                    encontrado = true;
                                }
                            }
                        }
                        
                        if (encontrado) {
                            System.out.println("Correcto. El numero estaba en tu carton y ha sido tachado.");
                        } else {
                            System.out.println("Incorrecto. El numero actual no esta en tu carton.");
                        }
                        opcionValida = true;
                        
                    } else if (opcion == 2) {
                        System.out.println("Has indicado que no tienes el numero.");
                        opcionValida = true;
                        
                    } else if (opcion == 3) {
                        boolean tachadoAlgunOlvido = false;
                        try {
                            File fHistorial = new File("\\\\DESKTOP-K5FUK6J\\retoAlmingo\\src\\retoAlmingo\\historial.txt");
                            Scanner lectorH = new Scanner(fHistorial);
                            
                            while (lectorH.hasNextInt()) {
                                int numHistorial = lectorH.nextInt();
                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        if (cartonMatriz[i][j] == numHistorial) {
                                            cartonMatriz[i][j] = -1;
                                            System.out.println("Aviso: Se encontro el numero " + numHistorial + " que ya habia salido y no tenias tachado. Tachado ahora.");
                                            tachadoAlgunOlvido = true;
                                        }
                                    }
                                }
                            }
                            lectorH.close();
                            
                            if (!tachadoAlgunOlvido) {
                                System.out.println("Revision completada. No tenias ningun numero atrasado sin tachar.");
                            }
                            
                        } catch (FileNotFoundException e) {
                            System.out.println("No se encontro el archivo historial.txt");
                        }
                        opcionValida = true;
                        
                    } else if (opcion == 4) {
                        boolean hizoLinea = false;
                        for (int i = 0; i < filas; i++) {
                            if (!filasCantadas[i] && Partida.comprobarLinea(cartonMatriz, i)) {
                                filasCantadas[i] = true;
                                hizoLinea = true;
                                
                                System.out.println("\nHas completado la linea " + (i + 1) + "!");
                                System.out.println("Pregunta de sostenibilidad:");
                                
                                boolean acierta = Partida.hacerPregunta(sc);
                                
                                if (acierta) {
                                    premios++;
                                    System.out.println("Respuesta correcta. Premio sumado (" + premios + "/3).");
                                    if (premios == 3) {
                                        System.out.println("Has ganado el premio mayor.");
                                    }
                                } else {
                                    System.out.println("Respuesta incorrecta. Pierdes el premio de esta linea.");
                                }
                            }
                        }
                        
                        if (!hizoLinea) {
                            System.out.println("No tienes ninguna linea hecha. Sigue jugando.");
                        }
                        opcionValida = true;
                        
                    } else {
                        System.out.println("Opcion incorrecta. Elige un numero del 1 al 4.");
                    }
                }
                
                // Muestra siempre el cartón actualizado al terminar el turno
                Partida.mostrarCarton(cartonMatriz, filas, columnas);
                
                // LIBERAR TURNO: Borramos el archivo de bloqueo para que el servidor pueda continuar
                try {
                    File fTurno = new File("\\\\DESKTOP-K5FUK6J\\retoAlmingo\\src\\retoAlmingo\\turno.txt");
                    if (fTurno.exists()) {
                        fTurno.delete();
                    }
                } catch (Exception e) {}
                
                if (Partida.comprobarBingo(cartonMatriz, filas, columnas)) {
                    System.out.println("\nBINGO. Carton completo.");
                    break;
                }
            }
        }
        
        System.out.println("\nJuego terminado. Premios totales: " + premios + " de 3.");
        if (premios == 3) {
            System.out.println("Premio mayor conseguido.");
        }
        sc.close();
    }
}