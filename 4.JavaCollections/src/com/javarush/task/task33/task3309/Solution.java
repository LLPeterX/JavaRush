package com.javarush.task.task33.task3309;

import org.w3c.dom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* 
Комментарий внутри xml
// ---- НЕ РАБОТАЕТ НИКАК ! ----
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {

        // Создаю документ DOM из объекта в аргументе:
        Document document = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.newDocument();

            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, document);
            document.getDocumentElement().normalize();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // достаю root узел
        Node root = document.getDocumentElement();

        NodeList nodeList = ((Element) root).getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            n.getParentNode().insertBefore(document.createComment(comment), n);
        }

        addCDATA(document, root);


        // еще один метод для конвертации измененного документа DOM в строку
        String result = null;
        try {
            result = convertDOMtoXML(document);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public static void addCDATA(Document doc, Node startNode) {

        for (Node node = startNode.getFirstChild(); node != null;) {
            Node nextNode = node.getNextSibling();

            if (node.getNodeValue() == null) {

                if (hasCDATA(node.getTextContent())) {

                    String stringToConvert = node.getTextContent();

                    CDATASection cdata = doc.createCDATASection(stringToConvert);
                    node.setTextContent("");
                    node.appendChild(cdata);

                }
            }

            if (node.hasChildNodes()) {
                addCDATA(doc, node);
            }

            node = nextNode;
        }
    }


    // Вспомогательный метод для проверки наличия запрещенных символов " ' < > &
    public static boolean hasCDATA(String check) {

        Pattern p = Pattern.compile("[\"\'<>&]");
        Matcher m = p.matcher(check);
        if (m.find()) {
            return true;
        }
        return false;
    }


    // Вспомогательный метод для конвертации документа в строку:
    public static String convertDOMtoXML(Document DOMDocument) throws Exception {

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        DOMSource source = new DOMSource(DOMDocument);
        transformer.transform(source, result);
        return writer.toString();
    }



    public static void main(String[] args) {
/*
        // Проверочный код
        Dog dog = new Dog();
        dog.age = 5;
        dog.dogName = "Sharik";
        dog.owner = "Arnold";
        dog.owner2 = "Kuzma";
        dog.needCDATA = "need CDATA cause of > ---";
        String x = toXmlWithComment(dog, "dogOwnerXML", "comment Ha-ha-ha-ha!");
        System.out.println(x);

 */
    }
}