import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class GraphBuilder {
    private GraphBuilder() {}

    public static void connect(String fileName) {
        List<Node> nodes = createNodes(numOfNodes(fileName));

        for (List<String> pair : connections(fileName)) {
            nodes.get(Integer.parseInt(pair.get(0)))
                    .connect(nodes.get(Integer.parseInt(pair.get(1))));
        }
    }

    private static List<Node> createNodes(int numOfNodes) {
        List<Node> nodes = new ArrayList<>();
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
}
