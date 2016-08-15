import java.util.HashSet;
import java.util.Set;

public class Node {
    private final int id;
    private Set<Integer> connectedTo = new HashSet<>();

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int degree() {
        return connectedTo.size();
    }

    public void connect(Node other) {
        this.connectedTo.add(other.getId());
        other.connectedTo.add(this.getId());
    }
}
