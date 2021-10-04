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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class TraceResult {

    private String traceResult = "<root>";
    private static Tree tree;

    TraceResult(Tree tree) {
        TraceResult.tree = tree;
    }

    private void traverseTree(Node current) {
        for (int i = 0; i < current.children.size(); i++) {
            Type type = getTypeOfNode(current);
            if (type != Type.ROOT) {
                open(current);
            }
            traverseTree(current.children.get(i));
            if (type != Type.ROOT) {
                close(current);
            }
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

    private void open(Node current) {
        Type type = getTypeOfNode(current);
        if (type == Type.METHOD) {
            traceResult = traceResult + "<" + type.toString().toLowerCase() + " name=\"" + getMethodName(current) + "\"" + " class=\""+ getClass(current)+"\" time=\"" + current.time + "\"" + ">";
        } else {
            traceResult = traceResult + "<" + type.toString().toLowerCase() + " name=\"" + current.name.substring(1).replaceAll("[<>]", "") + "\"" + " time=\"" + current.time + "\"" + ">";
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

    private String getClass(Node current) {
        int i=0;
        while (current.name.charAt(i)!='.'){
            i++;
        }
        return current.name.substring(1,i);
    }

    private String getMethodName(Node current) {
        int i=0;
        while (current.name.charAt(i)!='.'){
            i++;
        }
        i++;
        return current.name.substring(i).replaceAll("[<>]", "");
    }

    public String getTraceResult() {
        traverseTree(tree.getRoot());
        finishString();
        System.out.println(traceResult);
        makeXML();
        makeJSON();
        return traceResult;
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
                FileOutputStream fos = new FileOutputStream("other.xml");
                StreamResult result = new StreamResult(fos);
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                trans.transform(source, result);
            } catch (TransformerException | IOException e) {
                e.printStackTrace(System.out);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void makeJSON() {
        JSONObject object = XML.toJSONObject(traceResult);
        String jsonFilePath = System.getProperty("user.dir") + "\\file.json";
        try {
            FileWriter fileWriter = new FileWriter(jsonFilePath);
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
