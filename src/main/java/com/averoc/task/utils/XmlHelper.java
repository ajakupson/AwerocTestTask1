package com.averoc.task.utils;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

/**
 * Created by de1mos on 7.08.16.
 */
public class XmlHelper {
    private static Logger LOG = Logger.getLogger(XmlHelper.class);

    public static Document readXmlFile(String fileName) {
        LOG.debug("Reading XML file. FileName: " + fileName);

        File xmlFile = new File(fileName);
        DocumentBuilder documentBuilder = null;
        Document xmlDocument = null;

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException parserConfigurationException) {
            LOG.error("ParserConfigurationException: ", parserConfigurationException);
        }

        try {
            xmlDocument = documentBuilder.parse(xmlFile);
            xmlDocument.getDocumentElement().normalize();
            LOG.debug("Root element :" + xmlDocument.getDocumentElement().getNodeName());
        } catch (Exception exception) {
            LOG.error("Error parsing XML file: ", exception);
        }

        return xmlDocument;
    }

}
