package com;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.stream.*;

import java.io.*;

import com.Obfuscator;

public class ObfuscatorProcessor {
    private final SAXParser parser;
    private final ObfuscatorHandler handler;

    public ObfuscatorProcessor(String resultFilename) throws Exception {
        parser = SAXParserFactory.newInstance().newSAXParser();
        handler = new ObfuscatorHandler(resultFilename);
    }

    public void setDeobfuscation(boolean val) {
        handler.setDeobfuscation(val);
    }

    public boolean isDeobfuscation() {
        return handler.isDeobfuscation();
    }

    public void process(String filename) throws Exception {

        parser.parse(filename, handler);
    }

    private static class ObfuscatorHandler extends DefaultHandler {
        private final XMLStreamWriter streamWriter;
        private boolean deobfuscation = false;

        public ObfuscatorHandler(String outputFile) throws IOException, XMLStreamException {
            super();
            FileWriter writer = new FileWriter(outputFile);
            streamWriter = XMLOutputFactory.newFactory().createXMLStreamWriter(writer);
        }

        public void setDeobfuscation(boolean val) {
            this.deobfuscation = val;
        }

        public boolean isDeobfuscation() {
            return deobfuscation;
        }

        @Override
        public void startDocument() throws SAXException{
            super.startDocument();
            System.out.println("Start to process file...");
            try {
                streamWriter.writeStartDocument();
                streamWriter.writeCharacters("\n");
            } catch (XMLStreamException exc) {
                System.err.println("Error while writing in document");
                exc.printStackTrace();
            }
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("End to process file...");
            try {
                streamWriter.writeEndDocument();
                streamWriter.flush();
                streamWriter.close();
            } catch (XMLStreamException exc) {
                System.err.println("Error while writing in document");
                exc.printStackTrace();
            }
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            try {
                streamWriter.writeStartElement(qName);
                if (attributes.getLength() > 0) {
                    for (int attIndex = 0; attIndex < attributes.getLength(); attIndex++) {
                        streamWriter.writeAttribute(attributes.getQName(attIndex), attributes.getValue(attIndex));
                    }
                }
            } catch (XMLStreamException exc) {
                System.err.println("Error while writing entry for element " + localName);
                exc.printStackTrace();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            try {
                streamWriter.writeEndElement();
            } catch (XMLStreamException exc) {
                System.err.println("Error while writing exit for element " + localName);
                exc.printStackTrace();
            }
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            try {
                String result = Obfuscator.process(ch, start, length, deobfuscation);
                streamWriter.writeCharacters(result.toCharArray(), start, length);
            } catch (XMLStreamException exc) {
                System.err.println("Error while writing value: " + new String(ch, start, length));
                exc.printStackTrace();
            }
        }
    }
}

