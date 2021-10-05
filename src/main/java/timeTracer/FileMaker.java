package timeTracer;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
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

class FileMaker{

    static void makeXML(String pathToXML, String traceResult) {

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            Document doc;

            docBuilder = builderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new InputSource(new StringReader(traceResult)));

            try {
                Transformer trans = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(doc);
                FileOutputStream fos = new FileOutputStream(pathToXML);
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

    static void makeJSON(String pathToFile, String traceResult) {
        JSONObject object = XML.toJSONObject(traceResult);
        try {
            FileWriter fileWriter = new FileWriter(pathToFile);
            fileWriter.write(object.toString(4));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
