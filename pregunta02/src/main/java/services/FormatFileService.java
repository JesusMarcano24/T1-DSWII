package services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FormatFileService {

    //Profe aca le recomiendo que si desea que se generen los archivos en el proyecto al procesarse minimize y abra el proyecto,
    // ahi recien le va a aparecer los archivos en los directorios
    // Sino tambien puede poner una ruta en su computadora, ahi si va a poder el archivo en el directorio al momento
    // Tengo entendido que usted usa el disco F asi que aca le dejo algunas que puede usar:
    //private final String CONCURSANTES_DIR = "F:\\concursantes";
    //private final String DESCARTADOS_DIR = "F:\\descartados";
    private final String CONCURSANTES_DIR = "src/main/concursantes";
    private final String DESCARTADOS_DIR = "src/main/descartados";

    public FormatFileService() {
        crearDirectoriosSiNoExisten();
    }

    private void crearDirectoriosSiNoExisten() {
        new File(CONCURSANTES_DIR).mkdirs();
        new File(DESCARTADOS_DIR).mkdirs();
    }

    // Aca estamos creando/sobreescribiendo el archivo en la ruta que elegimos
    public void generarArchivos(JSONArray cuentas) {
        for (int i = 0; i < cuentas.length(); i++) {
            JSONObject cuenta = cuentas.getJSONObject(i);

            String banco = cuenta.getString("banco");
            long nroCuenta = cuenta.getLong("nro_cuenta");
            double saldo = cuenta.getDouble("saldo");

            boolean esConcursante = saldo > 5000.00;

            // Aca con un operador ternario validamos si es concursante o no para poder dirigir el archivo a una ruta u otra
            String carpetaDestino = esConcursante ? CONCURSANTES_DIR : DESCARTADOS_DIR;
            String nombreArchivo = carpetaDestino + "/cuenta_" + nroCuenta + ".txt";

            try (FileWriter writer = new FileWriter(nombreArchivo)) {
                writer.write("Banco de origen: " + banco + "\n");

                if (esConcursante) {
                    writer.write("La cuenta con el nro de cuenta: " + nroCuenta +
                            " tiene un saldo de ." + saldo + "\n");
                    writer.write("Usted es apto a participar en el  concurso de la SBS por 10000.00 soles.\n");
                    writer.write("Suerte!\n");
                } else {
                    writer.write("La cuenta con el nro de cuenta: " + nroCuenta +
                            " no tiene un saldo superior a 5000.00.\n");
                    writer.write("Lamentablemente no podrá acceder al concurso de la SBS por 10000.00 soles.\n");
                    writer.write("Gracias\n");
                }

            } catch (IOException e) {
                System.out.println("Error al escribir el archivo " + nombreArchivo + ": " + e.getMessage());
            }
        }
    }
}