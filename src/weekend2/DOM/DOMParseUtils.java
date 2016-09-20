package weekend2.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class DOMParseUtils {

    public static void parseXML(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        //xml document
        Document document = builder.parse(new File(path));

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.hashCode(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //TODO:...
                Element el = (Element) node;
                System.out.println(el.getNodeName());
            }
        }
    }

    private static int deepIndex = -1;

    public static String convertToJSON(Object object) throws IllegalAccessException {

        String json = "";

        Class cl = object.getClass();

        json += "{\n";
        json += cl.getName() + ":\n{";

        Field[] fields = cl.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            json +=  "\"" + fields[i].getName() + "\":";

            if (fields[i].getType().equals(String.class))
            {
                json += "\"" + fields[i].get(object) + "\"";
            }
            else
            {
                json += fields[i].get(object);
            }

            if (i < fields.length - 1)
            {
                json += ",\n";
            }
            else
            {
                json += "\n}";
            }
        }

        json += "\n}";

        return json;
    }

    public static String convertToXML(Object object) throws IllegalAccessException {

        String xml = "";

        Class cl = object.getClass();

        xml += "<" + cl.getName() + ">\n";

        Field[] fields = cl.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            xml +=  "\t<" + fields[i].getName() + ">";
            xml += fields[i].get(object);
            xml +=  "</" + fields[i].getName() + ">\n";
        }

        xml += "</" + cl.getName() + ">";

        return xml;
    }

    public static void main(String[] args) {
        try {
            System.out.println(convertToXML(new User(1, "Bogdan", "Kyiv", 25)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
