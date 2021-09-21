import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
import java.io.IOException;
import java.io.StringReader;

class TraceResult {

    private String traceResult = "<root>";

    void addToResult(String methodName, long traceResult, String className) {
        this.traceResult = this.traceResult + "<method name=\"" + methodName + "\" time=\"" + traceResult + "\" class=\"" + className + "\">" + "</method>";
    }

    String getTraceResult() {
        traceResult = traceResult + "</root>";
        makeXML();
        return traceResult;
    }

    private void makeXML() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            Document doc;

            docBuilder = builderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new InputSource(new StringReader(traceResult)));
            // Записываем XML в файл
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
