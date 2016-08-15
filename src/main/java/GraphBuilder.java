import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://dzone.com/articles/java-code-challenge-node-degrees
 */
public final class GraphBuilder {
    private static final String NODE_DEGREE_OUTPUT = "Node %d has a degree of %d";

    private GraphBuilder() {}

    public static List<String> build(String fileName) {
        List<Node> nodes = createNodes(numOfNodes(fileName));

        for (List<String> pair : connections(fileName)) {
            findNodeById(nodes, pair.get(0))
                    .connect(
                            findNodeById(nodes, pair.get(1)));
        }

        return degreeOutput(nodes);
    }

    private static List<String> degreeOutput(List<Node> nodes) {
        return nodes.stream()
                .map(node -> String.format(NODE_DEGREE_OUTPUT, node.getId(), node.degree()))
                .collect(Collectors.toList());
    }

    private static Node findNodeById(List<Node> nodes, String id) {
        return nodes.stream().filter(node -> node.getId() == Integer.parseInt(id)).findFirst().get();
    }

    private static List<Node> createNodes(int numOfNodes) {
        List<Node> nodes = new LinkedList<>();
        for (int i = 1; i <= numOfNodes; i++) {
            nodes.add(new Node(i));
        }
        return nodes;
    }

    private static List<List<String>> connections(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))){
            return reader.lines()
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(" ")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static int numOfNodes(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            return Integer.parseInt(reader.lines().findFirst().get());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
