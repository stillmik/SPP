package timeTracer;
import tree.Tree;
import java.io.*;

public class TraceResult implements XMLAndJSONResult {

    private String PATH_TO_XML = "C:\\Users\\User\\IdeaProjects\\dark\\XML.xml";
    private String PATH_TO_TEMP_XML = "C:\\Users\\User\\IdeaProjects\\dark\\src\\main\\java\\timeTracer\\tempXML.xml";
    private String PATH_TO_TEMP_JSON = "C:\\Users\\User\\IdeaProjects\\dark\\src\\main\\java\\timeTracer\\tempJSON.json";
    private String PATH_TO_JSON = "C:\\Users\\User\\IdeaProjects\\dark\\JSON.json";

    private String traceResult;
    private Tree tree;

    TraceResult(Tree tree) {
        this.tree = tree;
    }

    @Override
    public void getXML() {
        traceResult =TreeTraverser.makeTraceResultString(tree);
        FileMaker.makeXML(PATH_TO_XML,traceResult);
    }

    @Override
    public void getXMLandJSON() {
        traceResult =TreeTraverser.makeTraceResultString(tree);
        FileMaker.makeXML(PATH_TO_XML,traceResult);
        FileMaker.makeJSON(PATH_TO_JSON,traceResult);
    }

    @Override
    public void getJSON() {
        traceResult =TreeTraverser.makeTraceResultString(tree);
        FileMaker.makeJSON(PATH_TO_JSON,traceResult);
    }

    @Override
    public void getXMLinConsole() {
        traceResult =TreeTraverser.makeTraceResultString(tree);
        FileMaker.makeXML(PATH_TO_TEMP_XML,traceResult);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fin = new FileInputStream(PATH_TO_TEMP_XML);
            int i;
            while ((i = fin.read()) != -1) {
                stringBuilder.append((char) i);
            }
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        File file = new File(PATH_TO_TEMP_XML);
        file.delete();
        System.out.println(stringBuilder);
    }

    @Override
    public void getJSONinConsole() {
        traceResult =TreeTraverser.makeTraceResultString(tree);
        FileMaker.makeJSON(PATH_TO_TEMP_JSON,traceResult);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fin = new FileInputStream(PATH_TO_TEMP_JSON);
            int i;
            while ((i = fin.read()) != -1) {
                stringBuilder.append((char) i);
            }
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        File file = new File(PATH_TO_TEMP_JSON);
        file.delete();
        System.out.println(stringBuilder);
    }

    @Override
    public void getXMLandJSONinConsole() {
        getXMLinConsole();
        System.out.println("\n\n");
        getJSONinConsole();
    }

    public Tree getTree() {
        return tree;
    }
}
