package retoAlmingo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class bingo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        bombo bomboObj = new bombo();
        
        String rutaBase = "\\\\DESKTOP-K5FUK6J\\retoAlmingo-20261112T072149Z-1-001\\retoAlmingo\\src\\retoAlmingo\\";
        
        try {
            FileWriter limpiarHistorial = new FileWriter(rutaBase + "historial.txt", false);
            limpiarHistorial.write("");
            limpiarHistorial.close();
        } catch (IOException e) {
            System.out.println("Error al inicializar el historial.");
        }
        
        File fTurno = new File(rutaBase + "turno.txt");
        if (fTurno.exists()) {
            fTurno.delete();
        }
        
        System.out.println("Bingo iniciado.");
        System.out.println("\nPulsa ENTER para sacar el primer numero...");
        sc.nextLine();
        
        while (bomboObj.quedanBolas()) {
            int bola = bomboObj.sacarBola();
            System.out.println("\nNumero sacado: " + bola);
            
            try {
                FileWriter fw = new FileWriter(rutaBase + "numeros.txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.println(bola);
                pw.close();
                
                FileWriter fwH = new FileWriter(rutaBase + "historial.txt", true);
                PrintWriter pwH = new PrintWriter(fwH);
                pwH.println(bola);
                pwH.close();
                
                FileWriter fwT = new FileWriter(fTurno);
                fwT.write("ESPERANDO_JUGADOR");
                fwT.close();
                
            } catch (IOException e) {
                System.out.println("Error al escribir los archivos.");
            }
            
            System.out.println("Esperando a la respuesta del jugador (o autoliberación en 5 segundos)...");
            
            // Guardamos el momento en que empieza el turno
            long tiempoInicio = System.currentTimeMillis();
            
            // Bucle de espera: comprueba si el jugador borra el turno, O si pasan 5 segundos (5000 ms)
            while (fTurno.exists()) {
                long tiempoActual = System.currentTimeMillis();
                
                // Si pasan más de 5 segundos y el archivo sigue ahí, lo borramos a la fuerza
                if ((tiempoActual - tiempoInicio) > 5000) {
                    System.out.println("[AVISO] Tiempo de espera agotado. Liberando turno automáticamente...");
                    fTurno.delete();
                    break;
                }
            }
            
            System.out.println("\nTurno completado. Presiona ENTER para la siguiente bola...");
            sc.nextLine();
        }
        
        System.out.println("\nFin del juego. No quedan mas bolas en el bombo.");
        sc.close();
    }
}