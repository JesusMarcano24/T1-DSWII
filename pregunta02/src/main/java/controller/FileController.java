package controller;

import org.json.JSONArray;
import services.FileService;
import services.FormatFileService;

public class FileController {

    private final FileService fileService;
    private final FormatFileService formatFileService;

    public FileController() {
        this.fileService = new FileService();
        this.formatFileService = new FormatFileService();
    }

    public void procesarCuentas(String rutaArchivo) {
        JSONArray cuentas = fileService.readAndFilterJsonFile(rutaArchivo);
        formatFileService.generarArchivos(cuentas);
    }
}