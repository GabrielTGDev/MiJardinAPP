package com.mijardin.services;

import com.mijardin.entities.Planta;
import com.mijardin.handlers.CSVHandler;
import com.mijardin.handlers.XMLHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataIOService {
    private final CSVHandler csvHandler;
    private final XMLHandler xmlHandler;

    public DataIOService(CSVHandler csvHandler, XMLHandler xmlHandler) {
        this.csvHandler = csvHandler;
        this.xmlHandler = xmlHandler;
    }

    public List<Planta> importarDesdeCSV(File archivo) throws IOException {
        return csvHandler.importarDesdeCSV(archivo.getAbsolutePath());
    }

    public List<Planta> importarDesdeXML(File archivo) throws Exception {
        return xmlHandler.importarDesdeXML(archivo.getAbsolutePath());
    }

    public void exportarACSV(File archivo, List<Planta> plantas) throws IOException {
        csvHandler.exportarACSV(archivo.getAbsolutePath(), plantas);
    }

    public void exportarAXML(File archivo, List<Planta> plantas) throws Exception {
        xmlHandler.exportarAXML(archivo.getAbsolutePath(), plantas);
    }
}