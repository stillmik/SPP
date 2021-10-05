package timeTracer;

import tree.Node;
import tree.Tree;

class TreeTraverser {

    private static String traceResult;

    static String makeTraceResultString(Tree tree) {
        traceResult="<root>";
        traverseTree(tree.getRoot());
        finishString();
        System.out.println(traceResult);
        return traceResult;
    }

    private static void traverseTree(Node current) {
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

    private static void open(Node current) {
        Type type = getTypeOfNode(current);
        if (type == Type.METHOD) {
            traceResult = traceResult + "<" + type.toString().toLowerCase() + " name=\"" + getMethodName(current) + "\"" + " class=\"" + getClass(current) + "\" time=\"" + current.time + "\"" + ">";
        } else {
            traceResult = traceResult + "<" + type.toString().toLowerCase() + " id=\"" + getThreadId(current) + "\"" + " time=\"" + getTimeOfThread(current) + "\"" + ">";
        }
    }

    private static void close(Node current) {
        Type type = getTypeOfNode(current);
        if (type == Type.METHOD) {
            traceResult = traceResult + "</" + type.toString().toLowerCase() + ">" + "";
        } else {
            traceResult = traceResult + "</" + type.toString().toLowerCase() + ">" + "";
        }
    }

    private static Type getTypeOfNode(Node node) {
        if (node.name.equals("root")) {
            return Type.ROOT;
        }
        if (node.name.endsWith("()")) {
            return Type.METHOD;
        } else {
            return Type.THREAD;
        }
    }

    private static String getClass(Node current) {
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

    private static String getThreadId(Node current) {
        return String.valueOf(current.id);
    }

    private static String getMethodName(Node current) {
        int begin = current.name.length() - 1;
        while (current.name.charAt(begin) != '.') {
            begin--;
        }
        begin++;
        return current.name.substring(begin).replaceAll("[<>]", "");
    }

    private static String getTimeOfThread(Node current) {
        Time time = new Time();
        time.time = 0;
        for (int i = 0; i < current.children.size(); i++) {
            time.time = time.time + Math.toIntExact(current.children.get(i).time);
            timeOfThreadRecursion(current.children.get(i), time);
        }
        return Integer.toString(time.time);
    }

    private static void timeOfThreadRecursion(Node current, Time time) {
        for (int i = 0; i < current.children.size(); i++) {
            time.time = time.time + Math.toIntExact(current.children.get(i).time);
            timeOfThreadRecursion(current.children.get(i), time);
        }
    }

    private static void finishString() {
        traceResult = traceResult + "</root>";
    }
}
