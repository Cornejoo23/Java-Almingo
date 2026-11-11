package retoAlmingo;
	
import java.util.Random;
	
public class carton {
	
	public static final int fila=3;
	public static final int colum=9;	
	//carton
	static int[][] llenar(int[][] carton) {
	   Random r = new Random();
	   int cont;
	   cont =0;
	   for (int j = 0; j < colum; j++) {
	       int min, max;
	       if (j == 0) {
	           min = 1; 
	           max = 9;
	       } else if (j == 8) {
	           min = 80; 
	           max = 90;
	       } else {
	           min = j * 10;
	           max = (j * 10) + 9;
	       }
	        
	       for (int i = 0; i < (fila); i++) {
	            carton[i][j] = r.nextInt(min, max + 1);
	       }
	    } 
	    for (int i = 0; i < (fila); i++) {
	        cont = 0;
	        while (cont < 4) {
	            int azar = r.nextInt(colum);
	            if (carton[i][azar] != 0) {
	                carton[i][azar] = 0;
	                cont++;
	                
	            }
	        }
	    }
	    return carton;
	}
	
	static void mostrar(int[][] carton) {
	    for (int i = 0; i < (fila); i++) {
	        System.out.println();
	        if (i%3 == 0) {
	        	System.out.println();
	        }
	        for (int j = 0; j < colum; j++) {
	            if (carton[i][j] == 0) {
	                System.out.print("- ");
	            } else {
	                System.out.print(carton[i][j] + " ");
	            }
	        }
	    }
	}
}
	//carton