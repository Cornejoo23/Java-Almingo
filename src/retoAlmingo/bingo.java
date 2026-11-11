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
        
        // Limpiar historial al arrancar
        try {
            FileWriter limpiarHistorial = new FileWriter("\\\\DESKTOP-K5FUK6J\\retoAlmingo\\src\\retoAlmingo\\historial.txt", false);
            limpiarHistorial.write("");
            limpiarHistorial.close();
        } catch (IOException e) {
            System.out.println("Error al inicializar el historial.");
        }
        
        // Limpiar archivo de turno previo
        File fTurno = new File("\\\\DESKTOP-K5FUK6J\\retoAlmingo\\src\\retoAlmingo\\turno.txt");
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
                // Escribir el número actual
                FileWriter fw = new FileWriter("\\\\DESKTOP-K5FUK6J\\retoAlmingo\\src\\retoAlmingo\\numeros.txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.println(bola);
                pw.close();
                
                // Añadir al historial
                FileWriter fwH = new FileWriter("\\\\DESKTOP-K5FUK6J\\retoAlmingo\\src\\retoAlmingo\\historial.txt", true);
                PrintWriter pwH = new PrintWriter(fwH);
                pwH.println(bola);
                pwH.close();
                
                // Crear el archivo de turno pendiente para bloquear al servidor
                FileWriter fwT = new FileWriter(fTurno);
                fwT.write("ESPERANDO_JUGADOR");
                fwT.close();
                
            } catch (IOException e) {
                System.out.println("Error al escribir los archivos.");
            }
            
            System.out.println("Esperando a que el jugador elija una opcion en su turno...");
            
            // El servidor se queda bloqueado aquí hasta que el jugador borre el archivo de turno
            while (fTurno.exists()) {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
            
            System.out.println("\nTurno completado por el jugador. Presiona ENTER para la siguiente bola...");
            sc.nextLine();
        }
        
        System.out.println("\nFin del juego. No quedan mas bolas en el bombo.");
        sc.close();
    }
}