import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test
    public void connectNodes() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.connect(n2);
        Assert.assertEquals(1, n1.degree());
        Assert.assertEquals(1, n2.degree());
    }

    @Test
    public void onlyUniqueConnections() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.connect(n2);
        n1.connect(n2);
        Assert.assertEquals(1, n1.degree());

        n1.connect(n3);
        Assert.assertEquals(2, n1.degree());
        Assert.assertEquals(1, n2.degree());
        Assert.assertEquals(1, n3.degree());
    }
}