package test_glucosa;

import java.util.ArrayList;
import java.util.Random;

public class ClasificadorGlucosa implements Runnable {
    private int[] datos; 
    private ArrayList<Integer> resultado; 

    public ClasificadorGlucosa(int[] datos, ArrayList<Integer> resultado) {
        this.datos = datos;              
        this.resultado = resultado;       
    }

    @Override
    public void run() {
        for (int valor : datos) {  
            int clasificacion = Clasificacion.clasificar(valor);  
            synchronized (resultado) {   
                resultado.add(clasificacion);
            }
        }
    }

    public static int[] generarArrayAleatorio() {
        Random rand = new Random();
        int[] valores = new int[10];
        for (int i = 0; i < 10; i++) {
            valores[i] =  rand.nextInt(151) + 50;
        }
        return valores;
    }
}