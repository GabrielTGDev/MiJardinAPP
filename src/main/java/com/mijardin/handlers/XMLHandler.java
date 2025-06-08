package com.mijardin.handlers;

import com.mijardin.entities.Planta;
import com.mijardin.services.PlantaService;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {
    private final PlantaService plantaService;

    public XMLHandler(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    public List<Planta> importarDesdeXML(String filePath) throws Exception {
        List<Planta> plantas = new ArrayList<>();
        Planta planta = null;
        String currentElement = "";

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try (FileReader fileReader = new FileReader(filePath)) {
            XMLEventReader eventReader = factory.createXMLEventReader(fileReader);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    currentElement = startElement.getName().getLocalPart();
                    if ("planta".equals(currentElement)) {
                        planta = new Planta();
                    }
                } else if (event.isCharacters() && planta != null) {
                    Characters characters = event.asCharacters();
                    switch (currentElement) {
                        case "nombre" -> planta.setNombre(characters.getData());
                        case "especie" -> planta.setEspecie(characters.getData());
                        case "frecuenciaRiego" -> planta.setFrecuenciaRiegoDias(Integer.parseInt(characters.getData()));
                        case "frecuenciaFertilizacion" ->
                                planta.setFrecuenciaFertilizacionDias(Integer.parseInt(characters.getData()));
                    }
                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if ("planta".equals(endElement.getName().getLocalPart())) {
                        plantas.add(planta);
                    }
                }
            }
        }
        return plantas;
    }

    public void exportarAXML(String filePath, List<Planta> plantas) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(fos);
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");

            eventWriter.add(eventFactory.createStartDocument());
            eventWriter.add(end);

            eventWriter.add(eventFactory.createStartElement("", "", "plantas"));
            eventWriter.add(end);

            for (Planta planta : plantas) {
                eventWriter.add(eventFactory.createStartElement("", "", "planta"));
                eventWriter.add(end);

                createNode(eventWriter, "nombre", planta.getNombre());
                createNode(eventWriter, "especie", planta.getEspecie());
                createNode(eventWriter, "frecuenciaRiego", String.valueOf(planta.getFrecuenciaRiegoDias()));
                createNode(eventWriter, "frecuenciaFertilizacion", String.valueOf(planta.getFrecuenciaFertilizacionDias()));

                eventWriter.add(eventFactory.createEndElement("", "", "planta"));
                eventWriter.add(end);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "plantas"));
            eventWriter.add(end);

            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
    }

    private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        eventWriter.add(tab);
        eventWriter.add(eventFactory.createStartElement("", "", name));
        eventWriter.add(eventFactory.createCharacters(value));
        eventWriter.add(eventFactory.createEndElement("", "", name));
        eventWriter.add(end);
    }
}