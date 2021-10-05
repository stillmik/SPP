package timeTracer;

import org.w3c.dom.Document;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import tree.Node;
import tree.Tree;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class TraceResult {

    private String PATH_TO_XML = "C:\\Users\\User\\IdeaProjects\\dark\\other.xml";
    private String PATH_TO_JSON = "C:\\Users\\User\\IdeaProjects\\dark\\file.json";

    private String traceResult = "<root>";
    private Tree tree;

    TraceResult(Tree tree) {
        this.tree = tree;
    }

    public void getXML(){
        traceResult="<root>";
        traverseTree(tree.getRoot());
        finishString();
        System.out.println(traceResult);
        makeXML();
    }

    public void getXMLandJSON(){
        traceResult="<root>";
        traverseTree(tree.getRoot());
        finishString();
        System.out.println(traceResult);
        makeXML();
        makeJSON();
    }

    public void getJSON(){
        traceResult="<root>";
        traverseTree(tree.getRoot());
        finishString();
        System.out.println(traceResult);
        makeXML();
        File file = new File(PATH_TO_XML);
        file.delete();
        makeJSON();
    }

    private void traverseTree(Node current) {
        Type type = getTypeOfNode(current);
        if (type != Type.ROOT) {
            open(current);
        }
        for (int i = 0; i < current.children.size(); i++) {
            traverseTree(current.children.get(i));
        }
        if (type != Type.ROOT) {
            close(current);
        }
    }

    private void open(Node current) {
        Type type = getTypeOfNode(current);
        if (type == Type.METHOD) {
            traceResult = traceResult + "<" + type.toString().toLowerCase() + " name=\"" + getMethodName(current) + "\"" + " class=\"" + getClass(current) + "\" time=\"" + current.time + "\"" + ">";
        } else {
            traceResult = traceResult + "<" + type.toString().toLowerCase() + " id=\"" + getThreadId(current) + "\"" + " time=\"" + getTimeOfThread(current) + "\"" + ">";
        }
    }

    private void close(Node current) {
        Type type = getTypeOfNode(current);
        if (type == Type.METHOD) {
            traceResult = traceResult + "</" + type.toString().toLowerCase() + ">" + "";
        } else {
            traceResult = traceResult + "</" + type.toString().toLowerCase() + ">" + "";
        }
    }

    private String getTimeOfThread(Node current) {
        Time time=new Time();
        time.time=0;
        for (int i = 0; i < current.children.size(); i++) {
            time.time = time.time + Math.toIntExact(current.children.get(i).time);
            timeOfThreadRecursion(current.children.get(i), time);
        }
        return Integer.toString(time.time);
    }

    private void timeOfThreadRecursion(Node current, Time time) {
        for (int i = 0; i < current.children.size(); i++) {
            time.time = time.time + Math.toIntExact(current.children.get(i).time);
            timeOfThreadRecursion(current.children.get(i), time);
        }
    }

    private Type getTypeOfNode(Node node) {
        if (node.name.equals("root")) {
            return Type.ROOT;
        }
        if (node.name.endsWith("()")) {
            return Type.METHOD;
        } else {
            return Type.THREAD;
        }
    }

    private String getClass(Node current) {
        int end = current.name.length() - 1;
        int begin;
        while (current.name.charAt(end) != '.') {
            end--;
        }
        begin = end;
        begin--;
        while (current.name.charAt(begin) != '~') {
            begin--;
        }
        begin++;
        return current.name.substring(begin, end);
    }

    private String getThreadId(Node current) {
        return String.valueOf(current.id);
    }

    private String getMethodName(Node current) {
        int begin = current.name.length() - 1;
        while (current.name.charAt(begin) != '.') {
            begin--;
        }
        begin++;
        return current.name.substring(begin).replaceAll("[<>]", "");
    }

    private void makeXML() {

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            Document doc;

            docBuilder = builderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new InputSource(new StringReader(traceResult)));

            try {
                Transformer trans = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(doc);
                FileOutputStream fos = new FileOutputStream(PATH_TO_XML);
                StreamResult result = new StreamResult(fos);
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
                trans.transform(source, result);
                fos.close();
            } catch (TransformerException | IOException e) {
                e.printStackTrace(System.out);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void makeJSON() {
        JSONObject object = XML.toJSONObject(traceResult);
        try {
            FileWriter fileWriter = new FileWriter(PATH_TO_JSON);
            fileWriter.write(object.toString(4));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void finishString() {
        traceResult = traceResult + "</root>";
    }
}
