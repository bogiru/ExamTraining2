package main.java.sample.model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTaskByNumber(int number) {
        for (Task task : tasks) {
            if (task.getNumber() == number) {
                return task;
            }
        }

        return null;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Repository() {
        parseXml();
        System.out.print("");
    }

    private void parseXml() {
        try {
            File dir = new File(getClass().getClassLoader().getResource("xml").getFile());
            File[] files = dir.listFiles();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            for (File file : files) {

                Document document = db.parse(file);
                Element element = document.getDocumentElement();

                int numberTask = Integer.parseInt(element.getAttribute("number"));
                int numberPastVariant = Integer.parseInt(element.getAttribute("numberPastVariant"));
                List<Variant> tempVariants = new ArrayList<>();

                NodeList nodeVariants = element.getChildNodes();
                for (int i = 0; i < nodeVariants.getLength(); i++) {
                    if (!(nodeVariants.item(i) instanceof Element)) continue;

                    Node nodeVariant = nodeVariants.item(i);
                    int numberVariant = Integer.parseInt(nodeVariant.getAttributes().getNamedItem("number").getNodeValue());
                    NodeList nodeList = nodeVariant.getChildNodes();

                    String question = nodeList.item(1).getTextContent();
                    String answer = nodeList.item(3).getTextContent();

                    tempVariants.add(new Variant(numberVariant, question, answer));
                }

                tasks.add(new Task(numberTask, numberPastVariant, tempVariants));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }

    public void recordScore(int numberTask, int newValue) {
        try {
            File dir = new File(getClass().getClassLoader().getResource(String.format("xml/task%d.xml", numberTask)).getFile());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(dir);
            doc.normalize();

            Node nodeTask = doc.getElementsByTagName("variant").item(0);
            NamedNodeMap attributes = nodeTask.getAttributes();

            Node id = attributes.getNamedItem("number");
            id.setTextContent("2dfsfgert45");


//            element.getAttribute("task");
        //    element.setAttribute("number", "9");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}