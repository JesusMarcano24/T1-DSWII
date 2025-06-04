package main;

import controller.FileController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileController fileController = new FileController();

        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Procesar archivo JSON de cuentas");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido.");
                scanner.next();
                continue;
            }

            switch (opcion) {
                case 1:
                    // Aca estoy inyectando directamente la ruta del json a leer
                    String ruta = "./src/main/resources/cuentas.json";
                    fileController.procesarCuentas(ruta);
                    System.out.println("Procesamiento completado.");
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
