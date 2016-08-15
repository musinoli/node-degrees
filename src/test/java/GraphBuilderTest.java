import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraphBuilderTest {

    @Test
    public void graph1() throws IOException {
        Assert.assertEquals(
                expectedOutput("src/test/resources/graph-1-degrees"),
                GraphBuilder.build("src/test/resources/graph-1")
        );
    }

    @Test
    public void challenge() throws IOException {
        Assert.assertEquals(
                expectedOutput("src/test/resources/graph-challenge-degrees"),
                GraphBuilder.build("src/test/resources/graph-challenge")
        );
    }

    private List<String> expectedOutput(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
}