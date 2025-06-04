package test_glucosa;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> resultados = new ArrayList<>();

        int[] arr1 = ClasificadorGlucosa.generarArrayAleatorio();
        int[] arr2 = ClasificadorGlucosa.generarArrayAleatorio();
        int[] arr3 = ClasificadorGlucosa.generarArrayAleatorio();

        ClasificadorGlucosa hilo1 = new ClasificadorGlucosa(arr1, resultados);
        ClasificadorGlucosa hilo2 = new ClasificadorGlucosa(arr2, resultados);
        ClasificadorGlucosa hilo3 = new ClasificadorGlucosa(arr3, resultados);

        Thread thread1 = new Thread(hilo1);
        Thread thread2 = new Thread(hilo2);
        Thread thread3 = new Thread(hilo3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        long normales = resultados.stream().filter(n -> n == 0).count();
        long prediabetes = resultados.stream().filter(n -> n == 1).count();
        long diabetes = resultados.stream().filter(n -> n == 2).count();

        System.out.println("Clasificación de resultados:");
        System.out.printf("Normal: %.1f%%\n", (normales / 30.0) * 100);
        System.out.printf("Prediabetes: %.1f%%\n", (prediabetes / 30.0) * 100);
        System.out.printf("Diabetes: %.1f%%\n", (diabetes / 30.0) * 100);
    }
}