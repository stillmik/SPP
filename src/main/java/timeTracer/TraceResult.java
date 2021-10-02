package timeTracer;

import org.w3c.dom.Document;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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

    void addToResult(String methodName, long traceResult, String className) {
        this.traceResult = this.traceResult + "<method name=\"" + methodName + "\" time=\"" + traceResult + "\" class=\"" + className + "\">" + "</method>";
    }

    public String getTraceResult() {
        finishString();
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

    private void makeJSON(){
        JSONObject object = XML.toJSONObject(traceResult);
        String jsonFilePath = System.getProperty("user.dir")+"\\file.json";
        try {
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            fileWriter.write(object.toString(4));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void finishString(){
        traceResult = traceResult + "</root>";
    }
}
