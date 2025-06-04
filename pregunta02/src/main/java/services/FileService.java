package services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;

public class FileService {

    public JSONArray readAndFilterJsonFile(String filePath) {
        JSONArray cuentasActivas = new JSONArray();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            // Usamos el JSONTokener para parsear el fis y luego el JSONArray para convertir el json en un Array
            JSONTokener tokener = new JSONTokener(fis);
            JSONArray cuentas = new JSONArray(tokener);

            for (int i = 0; i < cuentas.length(); i++) {
                JSONObject cuenta = cuentas.getJSONObject(i);
                //Aca filtramos las cuentas que cuentan con el atributo estado y tambien  que sea true, y las sumamos al Array
                if (cuenta.has("estado") && cuenta.getBoolean("estado")) {
                    cuentasActivas.put(cuenta);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        return cuentasActivas;
    }
}