import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

public class Main {

    static int add(int a,int b){
        return a+b;
    }

    public static void main(String[] args) {
        System.out.println(Main.add(5,3));
        System.out.println(Main.add(4,8));

        /*try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc =docBuilder.newDocument();

            Element rootElement = doc.createElement( "root");
            doc.appendChild(rootElement);

            Node root = doc.getDocumentElement();

            Element book = doc.createElement("Book");
            Element title = doc.createElement("Title");
            Element author = doc.createElement("Author");
            Element date = doc.createElement("Date");

            book.appendChild(title);
            book.appendChild(author);
            book.appendChild(date);
            // Добавляем книгу в корневой элемент
            root.appendChild(book);

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
        }*/
        new MathOperations();
    }
}
