package retoAlmingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Partida {

    public static boolean comprobarLinea(int[][] m, int f) {
        for (int j = 0; j < m[f].length; j++) {
            if (m[f][j] > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean comprobarBingo(int[][] m, int f, int c) {
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hacerPregunta(Scanner sc) {
        try {
            File f1 = new File("sostenibilidad.txt");
            File f2 = new File("respuestas.txt");
            
            Scanner lp = new Scanner(f1);
            Scanner lr = new Scanner(f2);
            
            int totalLineas = 0;
            while (lp.hasNextLine() && lr.hasNextLine()) {
                totalLineas++;
                lp.nextLine();
                lr.nextLine();
            }
            lp.close();
            lr.close();
            
            if (totalLineas < 3) {
                return false;
            }
            
            String[] preguntas = new String[totalLineas];
            String[] respuestas = new String[totalLineas];
            
            Scanner lp2 = new Scanner(f1);
            Scanner lr2 = new Scanner(f2);
            int indexLinea = 0;
            while (lp2.hasNextLine() && lr2.hasNextLine()) {
                preguntas[indexLinea] = lp2.nextLine();
                respuestas[indexLinea] = lr2.nextLine();
                indexLinea++;
            }
            lp2.close();
            lr2.close();
            
            Random r = new Random();
            int idx = r.nextInt(totalLineas);
            
            String p = preguntas[idx];
            String rc = respuestas[idx];
            
            int i1, i2;
            do {
                i1 = r.nextInt(totalLineas);
            } while (i1 == idx);
            
            do {
                i2 = r.nextInt(totalLineas);
            } while (i2 == idx || i2 == i1);
            
            String ri1 = respuestas[i1];
            String ri2 = respuestas[i2];
            
            String[] opciones = new String[3];
            opciones[0] = rc;
            opciones[1] = ri1;
            opciones[2] = ri2;
            
            for (int i = 0; i < opciones.length; i++) {
                int rand = r.nextInt(opciones.length);
                String temp = opciones[i];
                opciones[i] = opciones[rand];
                opciones[rand] = temp;
            }
            
            char correcta = ' ';
            String[] letras = {"a", "b", "c"};
            
            System.out.println(p);
            for (int i = 0; i < 3; i++) {
                if (opciones[i].equals(rc)) {
                    correcta = letras[i].charAt(0);
                }
                System.out.println(letras[i] + ") " + opciones[i]);
            }
            
            System.out.print("Respuesta (a, b, c): ");
            char eleccion = Character.toLowerCase(sc.next().charAt(0));
            sc.nextLine();
            
            return eleccion == correcta;
            
        } catch (FileNotFoundException e) {
            System.out.println("Faltan archivos de texto.");
            return false;
        }
    }

    public static void mostrarCarton(int[][] m, int filas, int col) {
        for (int i = 0; i < filas; i++) {
            System.out.println();
            for (int j = 0; j < col; j++) {
                if (m[i][j] == 0) {
                    System.out.print("-\t");
                } else if (m[i][j] == -1) {
                    System.out.print("/\t"); 
                } else {
                    System.out.print(m[i][j] + "\t"); 
                }
            }
        }
        System.out.println();
    }
}