package retoAlmingo;

import java.util.Random;

public class bombo {
    private int[] bolasBombo;   // bolas que siguen dentro
    private int[] bolasSacadas; // bolas que ya han salido, en orden
    private int quedan;			// cuántas hay dentro
    private int sacadas;		// cuántas han salido
    private Random r = new Random();

    public bombo() {
        bolasBombo = new int[90];
        bolasSacadas = new int[90];

        for (int i = 0; i < bolasBombo.length; i++) {
            bolasBombo[i] = i + 1;
        }

        quedan = bolasBombo.length;
        sacadas = 0;
    }

    public boolean quedanBolas() {
        return quedan > 0;
    }

    public int sacarBola() {
        int pos = r.nextInt(quedan);
        int bola = bolasBombo[pos];

        bolasSacadas[sacadas] = bola;
        sacadas++;

        bolasBombo[pos] = bolasBombo[quedan - 1];
        quedan--;

        return bola;
    }

    public boolean haSalido(int numero) {
        for (int i = 0; i < sacadas; i++) {
            if (bolasSacadas[i] == numero) {
                return true;
            }
        }
        return false;
    }

    public int getQuedan() {
        return quedan;
    }
}